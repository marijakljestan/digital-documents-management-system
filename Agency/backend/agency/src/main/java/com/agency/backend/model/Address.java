package com.agency.backend.model;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Field(type = FieldType.Keyword)
    private String streetName;

    @Field(type = FieldType.Keyword)
    private String streetNumber;

    @Field(type = FieldType.Keyword)
    private String city;

    @Field(type = FieldType.Keyword)
    private String country;

    @GeoPointField
    private GeoPoint location;
}
