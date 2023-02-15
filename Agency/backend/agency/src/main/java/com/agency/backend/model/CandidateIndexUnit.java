package com.agency.backend.model;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = CandidateIndexUnit.INDEX_NAME, shards = 1, replicas = 0)
public class CandidateIndexUnit extends BaseIndexUnit {

    public static final String INDEX_NAME = "candidates";

    @Field(analyzer = "serbian", searchAnalyzer = "serbian", type = FieldType.Text, store = true)
    private String firstName;

    @Field(analyzer = "serbian", searchAnalyzer = "serbian", type = FieldType.Text, store = true)
    private String lastName;

    @Field(analyzer = "serbian", searchAnalyzer = "serbian", type = FieldType.Text, store = true)
    private String degree;

    @Field(analyzer = "serbian", searchAnalyzer = "serbian", type = FieldType.Text, store = true)
    private String cvContent;

    @Field(analyzer = "serbian", searchAnalyzer = "serbian", type = FieldType.Text, store = true)
    private String coverLetterContent;
}
