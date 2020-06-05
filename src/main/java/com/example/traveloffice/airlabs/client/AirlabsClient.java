package com.example.traveloffice.airlabs.client;

import com.example.traveloffice.airlabs.config.AirlabsConfig;
import com.example.traveloffice.domain.AirlabsResponseDto;
import com.example.traveloffice.domain.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class AirlabsClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirlabsClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AirlabsConfig airlabsConfig;

    public List<ResponseDto> getCityName(String cityCode) {
        try {
            List<ResponseDto> airlabResponseDto = Objects.requireNonNull(restTemplate.getForObject(buildUri(cityCode), AirlabsResponseDto.class)).getResponse();
            Optional<List<ResponseDto>> names = Optional.ofNullable(airlabResponseDto);
            return names.orElseGet(ArrayList::new);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    private URI buildUri(String city) {

        return UriComponentsBuilder.fromHttpUrl(airlabsConfig.getAirlabsApiEndpoint() +  "/cities/")
                .queryParam("api_key", airlabsConfig.getAirlabsAppKey())
                .queryParam("code", city)
                .build().encode().toUri();
    }
}
