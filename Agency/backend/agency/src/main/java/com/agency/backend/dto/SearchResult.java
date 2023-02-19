package com.agency.backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchResult {

    private String firstName;
    private String lastName;
    private String degree;
    private String cvContent;
    private String coverLetterContent;
    private String highlight;
    private String street;
    private String stretNumber;
    private String city;
}
