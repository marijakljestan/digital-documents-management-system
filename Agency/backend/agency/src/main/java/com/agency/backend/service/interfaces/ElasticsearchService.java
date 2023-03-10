package com.agency.backend.service.interfaces;

import com.agency.backend.dto.AdvancedQueryDto;
import com.agency.backend.dto.GeospatialSearchDto;
import com.agency.backend.dto.SearchResult;
import com.agency.backend.dto.SimpleQueryDto;

import java.io.IOException;
import java.util.List;

public interface ElasticsearchService {

    List<SearchResult> searchByField(SimpleQueryDto queryDto);

    List<SearchResult> searchByFields(List<SimpleQueryDto> queryDto);

    List<SearchResult> searchByCvContent(String cvContent);

    List<SearchResult> searchByCoverLetterContent(String coverLetterContent);

    List<SearchResult> searchByPhrase(SimpleQueryDto simpleQueryDto);

    List<SearchResult> searchByBooleanQuery(AdvancedQueryDto advancedQueryDto);

    List<SearchResult> geospatialSearch(GeospatialSearchDto geospatialSearchDto);
}
