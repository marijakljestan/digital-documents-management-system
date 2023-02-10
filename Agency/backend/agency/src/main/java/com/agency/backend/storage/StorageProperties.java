package com.agency.backend.storage;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ConfigurationProperties("storage")
public class StorageProperties {

    private String cvLocation = "cv";
    private String coverLettersLocation = "cover-letter";
    private String rootLocation = "docs";
}
