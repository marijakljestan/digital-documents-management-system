package com.agency.backend.mapper;

import com.agency.backend.dto.SearchResult;
import org.elasticsearch.search.SearchHit;

import java.util.Map;

public class SearchResultMapper {

    public static SearchResult toSearchResult(SearchHit hit) {
        Map<String, Object> sourceAsMap = hit.getSourceAsMap();
        return SearchResult.builder()
                .firstName(sourceAsMap.get("firstName").toString())
                .lastName(sourceAsMap.get("lastName").toString())
                .degree(sourceAsMap.get("degree").toString())
                .cvContent(sourceAsMap.get("cvContent").toString())
                .coverLetterContent(sourceAsMap.get("coverLetterContent").toString())
                .build();
    }
}
