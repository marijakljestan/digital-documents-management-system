package com.agency.backend.service.interfaces;

import com.agency.backend.dto.SearchResult;
import com.agency.backend.dto.SimpleQueryDto;

import java.io.IOException;
import java.util.List;

public interface ElasticsearchService {

    List<SearchResult> searchByFields(List<SimpleQueryDto> queryDto) throws IOException;
}
