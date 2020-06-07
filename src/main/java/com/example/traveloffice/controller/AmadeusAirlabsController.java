package com.example.traveloffice.controller;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.AirTraffic;
import com.example.traveloffice.airlabs.client.AirlabsClient;
import com.example.traveloffice.amadeus.client.AmadeusClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/")

public class AmadeusAirlabsController {
    @Autowired
    private AmadeusClient amadeusClient;
    @Autowired
    private AirlabsClient airlabsClient;

    @RequestMapping(method = RequestMethod.GET, value = "getCities")
    public List<String> getAmadeusCities() throws ResponseException {

        List<AirTraffic> cities = amadeusClient.getCities();
        List<String> string = new ArrayList<>();
        string.add("The most travelled cities. Published by amadeus.com");
        for(AirTraffic traffic: cities) {
            string.add(airlabsClient.getCityName(traffic.getDestination()).get(0).getName() + " Flights: " + traffic.getAnalytics().getFlights().getScore() + " Travellers " + traffic.getAnalytics().getTravelers().getScore());
        }
        return string;
    }
}
