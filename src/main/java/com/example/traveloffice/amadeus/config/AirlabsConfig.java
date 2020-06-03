package com.example.traveloffice.amadeus.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Getter
@Component
public class AirlabsConfig {

    @Value("${airlabs.api.endpoint.prod}")
    private String airlabsApiEndpoint;
    @Value("${airlabs.app.key}")
    private String airlabsAppKey;

}
