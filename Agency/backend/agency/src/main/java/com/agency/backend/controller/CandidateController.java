package com.agency.backend.controller;

import com.agency.backend.dto.RegisterCandidateDto;
import com.agency.backend.service.interfaces.CandidateService;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.agency.backend.AgencyApplication.LOGGER_INFO;

@RestController
@RequestMapping(value = "/candidate", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterCandidateDto candidateDto) {
        LOGGER_INFO.info("CANDIDATE CONTROLLER: register - start.");
        String candidateId = candidateService.register(candidateDto);
        LOGGER_INFO.info("CANDIDATE CONTROLLER: register - end.");
        return new ResponseEntity<>(candidateId, HttpStatus.CREATED);
    }
}
