package com.agency.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class BaseIndexUnit {

    @Id
    @Field(type = FieldType.Text, store = true)
    protected String id;
}
