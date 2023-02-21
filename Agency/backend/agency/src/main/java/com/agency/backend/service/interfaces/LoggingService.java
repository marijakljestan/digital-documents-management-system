package com.agency.backend.service.interfaces;

import org.springframework.http.ResponseEntity;

public interface LoggingService {
    ResponseEntity<?> logNewEmploymentRequest(String ipAddress);

    ResponseEntity<?> logNewSuccessfulEmployment(String ipAddress);

    ResponseEntity<?> logNewEmployeeForCompany(String ipAddress);
}
