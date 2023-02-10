package com.agency.backend.dto;

import com.agency.backend.model.Address;
import lombok.*;
import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterCandidateDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private AddressDto address;
    private File cv;
    private File coverLetter;
}
