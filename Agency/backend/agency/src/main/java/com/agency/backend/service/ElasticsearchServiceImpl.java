package com.agency.backend.service;

import com.agency.backend.dto.AdvancedQueryDto;
import com.agency.backend.dto.GeospatialSearchDto;
import com.agency.backend.dto.SearchResult;
import com.agency.backend.dto.SimpleQueryDto;
import com.agency.backend.dto.enums.OperationType;
import com.agency.backend.mapper.SearchResultMapper;
import com.agency.backend.model.CandidateIndexUnit;
import com.agency.backend.service.interfaces.ElasticsearchService;
import com.agency.backend.service.interfaces.GeocodingService;
import lombok.AllArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchOperations;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.agency.backend.AgencyApplication.LOGGER_ERROR;
import static com.agency.backend.AgencyApplication.LOGGER_INFO;

@Service
@AllArgsConstructor
@SuppressWarnings("deprecation")
public class ElasticsearchServiceImpl implements ElasticsearchService {

    private final String candidateIndex = CandidateIndexUnit.INDEX_NAME;

    private final String analyzer = CandidateIndexUnit.ANALYZER;
    private final RestHighLevelClient restHighLevelClient;
    private final GeocodingService geocodingService;

    @Override
    public List<SearchResult> searchByField(SimpleQueryDto queryDto) {
        /** https://stackoverflow.com/questions/60867242/elasticsearch-match-vs-term-in-filter/60867368#60867368 **/
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery(queryDto.getField(), queryDto.getValue()).analyzer(analyzer);
        return executeSearch(queryBuilder, queryDto.getField());
    }

    @Override
    public List<SearchResult> searchByFields(List<SimpleQueryDto> queryDtos) {
        StringBuilder query = new StringBuilder();
        List<String> fields = new ArrayList<>();
        for(SimpleQueryDto dto: queryDtos){
            query.append(dto.getValue()).append(" ");
            fields.add(dto.getField());
        }

        String[] arr = new String[queryDtos.size()];
        MultiMatchQueryBuilder searchQuery = QueryBuilders.multiMatchQuery(query.toString().trim(), fields.toArray(arr)).analyzer(analyzer);
        return executeSearch(searchQuery, "");
    }

    @Override
    public List<SearchResult> searchByCvContent(String cvContent) {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("cvContent", cvContent).analyzer(analyzer);
        return executeSearch(queryBuilder, "cvContent");
    }

    @Override
    public List<SearchResult> searchByCoverLetterContent(String coverLetterContent) {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("coverLetterContent", coverLetterContent).analyzer(analyzer);
        return executeSearch(queryBuilder, "coverLetterContent");
    }

    @Override
    public List<SearchResult> searchByPhrase(SimpleQueryDto simpleQueryDto) {
        MatchPhraseQueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery(simpleQueryDto.getField(), simpleQueryDto.getValue()).analyzer(analyzer);
        return executeSearch(queryBuilder, simpleQueryDto.getField());
    }

    @Override
    public List<SearchResult> searchByBooleanQuery(AdvancedQueryDto advancedQueryDto) {
        QueryBuilder queryBuilder1 = QueryBuilders.matchQuery(advancedQueryDto.getField1(), advancedQueryDto.getValue1()).analyzer(analyzer);
        QueryBuilder queryBuilder2 = QueryBuilders.matchQuery(advancedQueryDto.getField2(), advancedQueryDto.getValue2()).analyzer(analyzer);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(advancedQueryDto.getOperation().equals(OperationType.AND)){
            boolQueryBuilder.must(queryBuilder1);
            boolQueryBuilder.must(queryBuilder2);
        } else if(advancedQueryDto.getOperation().equals(OperationType.OR)){
            boolQueryBuilder.should(queryBuilder1);
            boolQueryBuilder.should(queryBuilder2);
        } else if(advancedQueryDto.getOperation().equals(OperationType.NOT)){
            boolQueryBuilder.must(queryBuilder1);
            boolQueryBuilder.mustNot(queryBuilder2);
        }
        return executeSearch(boolQueryBuilder, advancedQueryDto.getField1());
    }

    @Override
    public List<SearchResult> geospatialSearch(GeospatialSearchDto geospatialSearchDto) {
        GeoPoint geoPoint = geocodingService.getGeoPointOfCity(geospatialSearchDto.getCity());
        GeoDistanceQueryBuilder queryBuilder = QueryBuilders.geoDistanceQuery("address.location")
                                .point(geoPoint.getLat(), geoPoint.getLon())
                                .distance(geospatialSearchDto.getRadius(), DistanceUnit.KILOMETERS);

        return executeSearch(queryBuilder, "address.location");
    }


    private List<SearchResult> executeSearch(QueryBuilder searchQuery, String highlightedField) {
        LOGGER_INFO.info("ES SERVICE: executeSearch - start.");
        SearchRequest searchRequest = buildSearchRequest(searchQuery, highlightedField);
        List<SearchResult> results = new ArrayList<>();
        try {
            LOGGER_INFO.info("ES SERVICE: executeSearch - sending search request...");
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            results = processSearchHits(searchResponse.getHits().getHits(), highlightedField);
        } catch (Exception e) {
            LOGGER_ERROR.error("ES SERVICE: executeSearch - ", e);
        }
        LOGGER_INFO.info("ES SERVICE: executeSearch - end.");
        return results;
    }

    private  List<SearchResult> processSearchHits(SearchHit[] searchHits, String highlightedField) {
        LOGGER_INFO.info("ES SERVICE: processSearchHits - start.");
        List<SearchResult> results = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            SearchResult result = SearchResultMapper.toSearchResult(hit);
            result.setHighlight(getHighlightContent(hit, highlightedField));
            results.add(result);
        }
        LOGGER_INFO.info("ES SERVICE: processSearchHits - end.");
        return results;
    }

    private String getHighlightContent(SearchHit hit, String highlightedFieldName) {
        LOGGER_INFO.info("ES SERVICE: getHighlightContent - start.");
        Map<String, HighlightField> highlightFields = hit.getHighlightFields();
        if(highlightFields.isEmpty()) return "";

        HighlightField highlightedField = highlightFields.get(highlightedFieldName);
        LOGGER_INFO.info("ES SERVICE: getHighlightContent - end.");
        return highlightedField.fragments()[0].string();
    }

    private SearchRequest buildSearchRequest(QueryBuilder searchQuery, String highlightedField) {
        LOGGER_INFO.info("ES SERVICE: buildSearchRequest - start.");
        HighlightBuilder highlightBuilder = getHighlightBuilder(highlightedField);

        SearchRequest searchRequest = new SearchRequest(candidateIndex);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(searchQuery);
        sourceBuilder.highlighter(highlightBuilder);
        searchRequest.source(sourceBuilder);
        LOGGER_INFO.info("ES SERVICE: buildSearchRequest - end.");
        return searchRequest;
    }

    private static HighlightBuilder getHighlightBuilder(String highlightedField) {
        LOGGER_INFO.info("ES SERVICE: getHighlightBuilder - start.");
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field(highlightedField);
        highlightBuilder.preTags("<em>");
        highlightBuilder.postTags("</em>");
        highlightBuilder.fragmentSize(150);
        LOGGER_INFO.info("ES SERVICE: getHighlightBuilder - end.");
        return highlightBuilder;
    }
}
