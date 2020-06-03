package com.example.traveloffice.amadeus.controller;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.AirTraffic;
import com.example.traveloffice.amadeus.client.AirlabsClient;
import com.example.traveloffice.amadeus.client.AmadeusClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/")

public class AmadeusAirlabsController {
    @Autowired
    private AmadeusClient amadeusClient;
    @Autowired
    private AirlabsClient airlabsClient;

    @RequestMapping(method = RequestMethod.GET, value = "getCities")
    public String getAmadeusCities() throws ResponseException {

        List<AirTraffic> cities = amadeusClient.getCities();
        return "hej";


        //cities.forEach(city -> city.getDestination() + " Flights: " + city.getAnalytics().getFlights().getScore() + " Travellers " + city.getAnalytics().getTravelers().getScore()      );
    }
}
