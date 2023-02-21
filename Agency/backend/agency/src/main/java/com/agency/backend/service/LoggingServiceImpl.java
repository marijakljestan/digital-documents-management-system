package com.agency.backend.service;

import com.agency.backend.service.interfaces.GeocodingService;
import com.agency.backend.service.interfaces.LoggingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.agency.backend.AgencyApplication.LOGGER_WARNING;

@Service
@AllArgsConstructor
public class LoggingServiceImpl implements LoggingService {

    private GeocodingService geocodingService;

    @Override
    public ResponseEntity<String> logNewEmploymentRequest(String ipAddress) {
        String city = geocodingService.getCityNameForIPAddress(ipAddress);
        if(city != null)
            LOGGER_WARNING.info("New premium employment request made from city: {}", city);

        return ResponseEntity.ok(city);
    }

    @Override
    public ResponseEntity<?> logNewSuccessfulEmployment(String username) {
        LOGGER_WARNING.info("Premium employment process finished successfully by employee: {}", username);
        return ResponseEntity.ok("");
    }

    @Override
    public ResponseEntity<?> logNewEmployeeForCompany(String companyName) {
        LOGGER_WARNING.info("Premium employment finished with company: {}", companyName);
        return ResponseEntity.ok("");
    }
}
