package com.agency.backend.service;

import com.agency.backend.dto.IpApiResponseDto;
import com.agency.backend.model.Address;
import com.agency.backend.service.interfaces.GeocodingService;
import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.agency.backend.AgencyApplication.LOGGER_INFO;


@Service

public class GeocodingServiceImpl implements GeocodingService {

    @Value("${geocoding.apikey}")
    private String geocodingApiKey;

    @Autowired
    private  RestTemplate restTemplate;

    @Override
    public GeoPoint getGeoPointFromAddress(Address address) {
        LOGGER_INFO.info("GEOCODING SERVICE: getGeoPointFromAddress - start.");
        String query = new StringBuilder(address.getStreetNumber()).append(",")
                .append(address.getStreetName()).append(",").append(address.getCity()).append(",")
                .append(address.getCountry()).toString();
        LOGGER_INFO.info("GEOCODING SERVICE: getGeoPointFromAddress - end.");
        return getGeoPoint(query);
    }

    @Override
    public String getCityNameForIPAddress(String ipAddress) {
        ResponseEntity<IpApiResponseDto> response = restTemplate.getForEntity("http://ip-api.com/json/" + ipAddress, IpApiResponseDto.class);
        String city = "";
        System.out.println(ipAddress);
        if (response.getBody() != null) {
            System.out.println(response.getBody());
            city = response.getBody().getCity();
        }
        return city;
    }

    @Override
    public GeoPoint getGeoPointOfCity(String city) {;
        LOGGER_INFO.info("GEOCODING SERVICE: getGeoPointOfCity");
        return getGeoPoint(city);
    }

    private GeoPoint getGeoPoint(String query) {
        LOGGER_INFO.info("GEOCODING SERVICE: getGeoPoint - start.");
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(geocodingApiKey);
        LOGGER_INFO.info("GEOCODING SERVICE: getGeoPoint - sending request to OpenCage API...");
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(query);
        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        LOGGER_INFO.info("GEOCODING SERVICE: getGeoPoint - obtaining response data...");
        Double latitude = response.getResults().get(0).getGeometry().getLat();
        Double longitude = response.getResults().get(0).getGeometry().getLng();
        return new GeoPoint(latitude, longitude);
    }
}
