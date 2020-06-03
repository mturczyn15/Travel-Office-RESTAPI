package com.example.traveloffice.amadeus.client;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.AirTraffic;
import com.example.traveloffice.amadeus.config.AmadeusConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AmadeusClient {

    @Autowired
    private AmadeusConfig amadeusConfig;

    public List<AirTraffic> getCities() throws ResponseException {
        Amadeus amadeus = Amadeus
                .builder(amadeusConfig.getAmadeusAppKey(), amadeusConfig.getAmadeusSecret())
                .build();
        AirTraffic[] airTraffics = amadeus.travel.analytics.airTraffic.booked.get(Params
                .with("originCityCode", "MAD")
                .and("period", "2017-08"));

        if (airTraffics[0].getResponse().getStatusCode() != 200) {
            System.out.println("Wrong status code: " + airTraffics[0].getResponse().getStatusCode());
            System.exit(-1);
        }

        return Arrays.asList(airTraffics);

    }
}
