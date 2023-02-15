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
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@SuppressWarnings("deprecation")
public class ElasticsearchServiceImpl implements ElasticsearchService {

    private final String candidateIndex = CandidateIndexUnit.INDEX_NAME;
    private final RestHighLevelClient restHighLevelClient;

    @Override
    public List<SearchResult> searchByFields(List<SimpleQueryDto> queryDtos) throws IOException {
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

    private QueryBuilder buildSearchQuery(String query, String[] fields) {
        if (query == null || fields.length < 1)  return null;

        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
        if(fields.length == 1)
            return queryBuilder.must(QueryBuilders.matchQuery(fields[0], query));
        else
            return queryBuilder.must(QueryBuilders.multiMatchQuery(query, fields));
    }

    private List<SearchResult> executeSearch(QueryBuilder searchQuery) throws IOException {
        SearchRequest searchRequest = new SearchRequest(candidateIndex);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(searchQuery);
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = searchResponse.getHits().getHits();

        List<SearchResult> results = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            results.add(SearchResult.builder()
                            .firstName(sourceAsMap.get("firstName").toString())
                            .lastName(sourceAsMap.get("lastName").toString())
                            .degree(sourceAsMap.get("degree").toString())
                            .cvContent(sourceAsMap.get("cvContent").toString())
                            .coverLetterContent(sourceAsMap.get("coverLetter").toString())
                            .highlight("")
                    .build());
        }

        return results;
    }
}
