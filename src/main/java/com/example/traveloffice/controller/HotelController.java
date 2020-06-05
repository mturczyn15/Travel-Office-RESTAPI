package com.example.traveloffice.controller;

import com.example.traveloffice.domain.HotelDto;
import com.example.traveloffice.facade.HotelFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/v1/")
public class HotelController {
    @Autowired
    private HotelFacade hotelFacade;

    @RequestMapping(method = RequestMethod.GET, value = "hotels")
    public List<HotelDto> getHotels() {
        return hotelFacade.getHotels();
    }

    @RequestMapping(method = RequestMethod.GET, value = "hotels/{hotelId}")
    public HotelDto getHotel(@PathVariable Long hotelId) {
        return hotelFacade.getHotel(hotelId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "hotels/{hotelId}")
    public void deleteHotel(@PathVariable Long hotelId) {
        hotelFacade.deleteHotel(hotelId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "hotels", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HotelDto updateHotel(@RequestBody HotelDto hotelDto) {
        return hotelFacade.update(hotelDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "hotels", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createHotel(@RequestBody HotelDto hotelDto) {
        hotelFacade.create(hotelDto);
    }
}
