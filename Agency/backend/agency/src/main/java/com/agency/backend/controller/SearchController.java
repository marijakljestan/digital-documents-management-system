package com.agency.backend.controller;

import com.agency.backend.dto.SearchResult;
import com.agency.backend.dto.SimpleQueryDto;
import com.agency.backend.service.interfaces.ElasticsearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.agency.backend.AgencyApplication.LOGGER_INFO;

@RestController
@RequestMapping(value="/search", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class SearchController {

    private final ElasticsearchService elasticSearchService;

    @GetMapping(value = "/fields")
    public ResponseEntity<List<SearchResult>> searchByFields(@RequestBody List<SimpleQueryDto> queryDto) {
        List<SearchResult> searchResults = elasticSearchService.searchByFields(queryDto);
        return ResponseEntity.ok(searchResults);
    }

    @GetMapping(value = "/cv")
    public ResponseEntity<List<SearchResult>> searchByCvContent(@RequestParam(name = "cvContent") String cvContent) {
        LOGGER_INFO.info("SEARCH CONTROLLER: searchByCvContent - start.");
        List<SearchResult> searchResults = elasticSearchService.searchByCvContent(cvContent);
        LOGGER_INFO.info("SEARCH CONTROLLER: searchByCvContent - end.");
        return ResponseEntity.ok(searchResults);
    }

    @GetMapping(value = "/cover-letter")
    public ResponseEntity<List<SearchResult>> searchByCoverLetterContent(@RequestParam(name = "coverLetterContent") String coverLetterContent) {
        LOGGER_INFO.info("SEARCH CONTROLLER: searchByCoverLetterContent - start.");
        List<SearchResult> searchResults = elasticSearchService.searchByCoverLetterContent(coverLetterContent);
        LOGGER_INFO.info("SEARCH CONTROLLER: searchByCoverLetterContent - end.");
        return ResponseEntity.ok(searchResults);
    }

    @GetMapping(value = "/phrase")
    public ResponseEntity<List<SearchResult>> searchByPhrase(@RequestBody SimpleQueryDto simpleQueryDto) {
        LOGGER_INFO.info("SEARCH CONTROLLER: searchByPhrase - start.");
        List<SearchResult> searchResults = elasticSearchService.searchByPhrase(simpleQueryDto);
        LOGGER_INFO.info("SEARCH CONTROLLER: searchByPhrase - end.");
        return ResponseEntity.ok(searchResults);
    }
}
