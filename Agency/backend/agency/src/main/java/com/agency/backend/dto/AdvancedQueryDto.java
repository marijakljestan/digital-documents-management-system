package com.agency.backend.dto;

import com.agency.backend.dto.enums.OperationType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvancedQueryDto {

    private String field1;
    private String value1;
    private String field2;
    private String value2;
    private OperationType operation;
}
