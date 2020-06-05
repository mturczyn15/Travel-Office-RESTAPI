package com.example.traveloffice.controller;

import com.example.traveloffice.domain.TravelAgencyDto;
import com.example.traveloffice.service.TravelAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/v1/")
public class TravelAgencyController {
    @Autowired
    private TravelAgencyService travelAgencyService;

    @RequestMapping(method = RequestMethod.GET, value = "travelAgencies")
    public List<TravelAgencyDto> getTravelAgencies() {
        return travelAgencyService.getTravelAgencies();
    }

    @RequestMapping(method = RequestMethod.GET, value = "travelAgencies/{travelAgencyId}")
    public TravelAgencyDto getTravelAgency(@PathVariable Long travelAgencyId) {
        return travelAgencyService.getTravelAgency(travelAgencyId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "travelAgencies/{travelAgencyId}")
    public void deleteTravelAgency(@PathVariable Long travelAgencyId) {
        travelAgencyService.deleteTravelAgency(travelAgencyId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "travelAgencies")
    public TravelAgencyDto updateTravelAgency(@RequestBody TravelAgencyDto travelAgencyDto) {
        return travelAgencyService.update(travelAgencyDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "travelAgencies", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTravelAgency(@RequestBody TravelAgencyDto travelAgencyDto) {
        travelAgencyService.create(travelAgencyDto);
    }
}
