package com.agency.backend.service.interfaces;

import com.agency.backend.model.Address;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

public interface GeocodingService {

    GeoPoint getGeoPointFromAddress(Address address);

    GeoPoint getGeoPointOfCity(String city);
}
