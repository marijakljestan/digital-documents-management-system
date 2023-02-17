package com.agency.backend.service;

import com.agency.backend.model.Address;
import com.agency.backend.service.interfaces.GeocodingService;
import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;


@Service
@NoArgsConstructor
public class GeocodingServiceImpl implements GeocodingService {

    @Value("${geocoding.apikey}")
    private String geocodingApiKey;

    @Override
    public GeoPoint getGeoPointFromAddress(Address address) {
        String query = new StringBuilder(address.getStreetNumber()).append(",")
                .append(address.getStreetName()).append(",").append(address.getCity()).append(",")
                .append(address.getCountry()).toString();

        return getGeoPoint(query);
    }

    @Override
    public GeoPoint getGeoPointOfCity(String city) {;
        return getGeoPoint(city);
    }

    private GeoPoint getGeoPoint(String query) {
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(geocodingApiKey);
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(query);
        JOpenCageResponse response = jOpenCageGeocoder.forward(request);

        Double latitude = response.getResults().get(0).getGeometry().getLat();
        Double longitude = response.getResults().get(0).getGeometry().getLng();
        return new GeoPoint(latitude, longitude);
    }
}
