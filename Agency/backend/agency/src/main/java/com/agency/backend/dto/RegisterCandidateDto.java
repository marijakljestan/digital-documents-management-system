package com.agency.backend.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterCandidateDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String degree;
    private String phoneNumber;
    private String streetName;
    private String streetNumber;
    private String city;
    private String country;
    private MultipartFile cv;
    private MultipartFile coverLetter;
}
