package com.example.traveloffice.amadeus.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AmadeusConfig {

    @Value("${amadeus.api.endpoint.prod}")
    private String amadeusApiEndpoint;
    @Value("${amadeus.app.key}")
    private String amadeusAppKey;
    @Value("${amadeus.app.token}")
    private String amadeusToken;
    @Value("${amadeus.app.secret}")
    private String amadeusSecret;
}
