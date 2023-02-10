package com.agency.backend.controller;

import com.agency.backend.dto.RegisterCandidateDto;
import com.agency.backend.service.interfaces.CandidateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

import static com.agency.backend.AgencyApplication.LOGGER_INFO;

@RestController
@RequestMapping(value = "/candidate", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam("cv") MultipartFile cv,
                                           @RequestParam("coverLetter") MultipartFile coverLetter,
                                           @RequestParam Map<String, String> formData) throws IOException {
        LOGGER_INFO.info("CANDIDATE CONTROLLER: register - start.");
        RegisterCandidateDto candidateDto = getCandidateDtoFromFormData(formData);
        candidateDto.setCv(cv);
        candidateDto.setCoverLetter(coverLetter);
        String candidateId = candidateService.register(candidateDto);
        LOGGER_INFO.info("CANDIDATE CONTROLLER: registered candidate with id {} - end of method.", candidateId);
        return new ResponseEntity<>(candidateId, HttpStatus.CREATED);
    }

    private RegisterCandidateDto getCandidateDtoFromFormData(Map<String, String> formData) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(formData);
        Gson gson = new Gson();
        return gson.fromJson(json, RegisterCandidateDto.class);
    }
}
