package com.agency.backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {

    private String streetName;
    private String streetNumber;
    private String city;
    private String country;
}
