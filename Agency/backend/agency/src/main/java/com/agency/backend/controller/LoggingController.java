package com.agency.backend.controller;

import com.agency.backend.service.interfaces.LoggingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
@AllArgsConstructor
public class LoggingController {

    private LoggingService loggingService;

    @GetMapping(value = "/employment-request/{ipAddress}")
    public ResponseEntity<?> logEmploymentRequest(@PathVariable  String ipAddress) {
        return loggingService.logNewEmploymentRequest(ipAddress);
    }

    @GetMapping(value = "/new-employment/{username}")
    public ResponseEntity<?> logNewSuccessfulEmployment(@PathVariable  String username) {
        return loggingService.logNewSuccessfulEmployment(username);
    }

    @GetMapping(value = "/new-employee/{companyName}")
    public ResponseEntity<?> logNewEmployeeForCompany(@PathVariable  String companyName) {
        return loggingService.logNewEmployeeForCompany(companyName);
    }
}
