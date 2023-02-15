package com.agency.backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleQueryDto {

    private String field;
    private String value;
}
