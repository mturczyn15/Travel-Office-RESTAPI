package com.example.traveloffice.controller;

import com.example.traveloffice.domain.TravelAgencyDto;
import com.example.traveloffice.service.TravelAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/traveloffice/travelAgency")
public class TravelAgencyController {
    @Autowired
    private TravelAgencyService travelAgencyService;

    @RequestMapping(method = RequestMethod.GET, value = "getTravelAgencies")
    public List<TravelAgencyDto> getTravelAgencys() {
        return travelAgencyService.getTravelAgencys();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTravelAgency")
    public TravelAgencyDto getTravelAgency(@RequestParam Long travelAgencyId) {
        return travelAgencyService.getTravelAgency(travelAgencyId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTravelAgency")
    public void deleteTravelAgency(@RequestParam Long travelAgencyId) {
        travelAgencyService.deleteTravelAgency(travelAgencyId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTravelAgency")
    public TravelAgencyDto updateTravelAgency(@RequestBody TravelAgencyDto travelAgencyDto) {
        return travelAgencyService.update(travelAgencyDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTravelAgency", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTravelAgency(@RequestBody TravelAgencyDto travelAgencyDto) {
        travelAgencyService.create(travelAgencyDto);
    }
}
