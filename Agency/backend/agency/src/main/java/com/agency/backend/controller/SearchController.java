package com.agency.backend.controller;

import com.agency.backend.dto.AdvancedQueryDto;
import com.agency.backend.dto.GeospatialSearchDto;
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

    @PostMapping(value = "/field")
    public ResponseEntity<List<SearchResult>> searchByField(@RequestBody SimpleQueryDto queryDto) {
        LOGGER_INFO.info("SEARCH CONTROLLER: searchByField - start.");
        List<SearchResult> searchResults = elasticSearchService.searchByField(queryDto);
        LOGGER_INFO.info("SEARCH CONTROLLER: searchByField - end.");
        return ResponseEntity.ok(searchResults);
    }

    @PostMapping(value = "/fields")
    public ResponseEntity<List<SearchResult>> searchByMultipleFields(@RequestBody List<SimpleQueryDto> queryDto) {
        LOGGER_INFO.info("SEARCH CONTROLLER: searchByMultipleFields - start.");
        List<SearchResult> searchResults = elasticSearchService.searchByFields(queryDto);
        LOGGER_INFO.info("SEARCH CONTROLLER: searchByMultipleFields - end.");
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

    @PostMapping(value = "/phrase")
    public ResponseEntity<List<SearchResult>> searchByPhrase(@RequestBody SimpleQueryDto simpleQueryDto) {
        LOGGER_INFO.info("SEARCH CONTROLLER: searchByPhrase - start.");
        List<SearchResult> searchResults = elasticSearchService.searchByPhrase(simpleQueryDto);
        LOGGER_INFO.info("SEARCH CONTROLLER: searchByPhrase - end.");
        return ResponseEntity.ok(searchResults);
    }

    @PostMapping(value = "/boolean")
    public ResponseEntity<List<SearchResult>> searchByBooleanQuery(@RequestBody AdvancedQueryDto advancedQueryDto) {
        LOGGER_INFO.info("SEARCH CONTROLLER: searchByBooleanQuery - start.");
        List<SearchResult> searchResults = elasticSearchService.searchByBooleanQuery(advancedQueryDto);
        LOGGER_INFO.info("SEARCH CONTROLLER: searchByBooleanQuery - end.");
        return ResponseEntity.ok(searchResults);
    }

    @PostMapping(value = "/geospatial")
    public ResponseEntity<List<SearchResult>> geospatialSearch(@RequestBody GeospatialSearchDto geospatialSearchDto) {
        LOGGER_INFO.info("SEARCH CONTROLLER: geospatialSearch - start.");
        List<SearchResult> searchResults = elasticSearchService.geospatialSearch(geospatialSearchDto);
        LOGGER_INFO.info("SEARCH CONTROLLER: geospatialSearch - end.");
        return ResponseEntity.ok(searchResults);
    }
}
