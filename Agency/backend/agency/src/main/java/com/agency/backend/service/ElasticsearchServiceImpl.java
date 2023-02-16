package com.agency.backend.service;

import com.agency.backend.dto.SearchResult;
import com.agency.backend.dto.SimpleQueryDto;
import com.agency.backend.model.CandidateIndexUnit;
import com.agency.backend.service.interfaces.ElasticsearchService;
import lombok.AllArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    private final RestHighLevelClient restHighLevelClient;

    @Override
    public List<SearchResult> searchByFields(List<SimpleQueryDto> queryDtos) {
        StringBuilder query = new StringBuilder();
        List<String> fields = new ArrayList<>();
        for(SimpleQueryDto dto: queryDtos){
            query.append(dto.getValue()).append(" ");
            fields.add(dto.getField());
        }

        String[] arr = new String[fields.size()];
        arr = fields.toArray(arr);
        QueryBuilder searchQuery = buildSearchQuery(query.toString().trim(), arr);
        return executeSearch(searchQuery);
    }

    @Override
    public List<SearchResult> searchByCvContent(String cvContent) {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("cvContent", cvContent).analyzer("serbian");
        return executeSearch(queryBuilder);
    }

    @Override
    public List<SearchResult> searchByCoverLetterContent(String coverLetterContent) {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("coverLetterContent", coverLetterContent).analyzer("serbian");
        return executeSearch(queryBuilder);
    }

    @Override
    public List<SearchResult> searchByPhrase(SimpleQueryDto simpleQueryDto) {
        MatchPhraseQueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery(simpleQueryDto.getField(), simpleQueryDto.getValue()).analyzer("serbian");
        return executeSearch(queryBuilder);
    }

    private QueryBuilder buildSearchQuery(String query, String[] fields) {
        if (query == null || fields.length < 1)  return null;

        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
        if(fields.length == 1)
            return queryBuilder.must(QueryBuilders.matchQuery(fields[0], query).analyzer("serbian"));
        else
            return queryBuilder.must(QueryBuilders.multiMatchQuery(query, fields).analyzer("serbian"));
    }

    private List<SearchResult> executeSearch(QueryBuilder searchQuery) {

//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        HighlightBuilder.Field cvContentHighlight = new HighlightBuilder.Field("cvContent");
//        highlightBuilder.field(cvContentHighlight);
//        highlightBuilder.preTags("<em>");
//        highlightBuilder.postTags("</em>");
//        highlightBuilder.fragmentSize(150);

        SearchRequest searchRequest = new SearchRequest(candidateIndex);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(searchQuery);
        searchRequest.source(sourceBuilder);

        List<SearchResult> results = new ArrayList<>();
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] searchHits = searchResponse.getHits().getHits();

            for (SearchHit hit : searchHits) {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                HighlightField cvContentHighlightField = hit.getHighlightFields().get("cvContent");
                results.add(SearchResult.builder()
                        .firstName(sourceAsMap.get("firstName").toString())
                        .lastName(sourceAsMap.get("lastName").toString())
                        .degree(sourceAsMap.get("degree").toString())
                        .cvContent(sourceAsMap.get("cvContent").toString())
                        .coverLetterContent(sourceAsMap.get("coverLetterContent").toString())
                        //.highlight(cvContentHighlightField.fragments()[0].string())
                                .highlight("")
                        .build());
            }
        } catch (IOException e) {
            LOGGER_ERROR.error("ES SERVICE: executeSearch - ", e);
            e.printStackTrace();
        }

        return results;
    }
}
