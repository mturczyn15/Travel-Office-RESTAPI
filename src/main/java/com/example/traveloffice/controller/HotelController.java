package com.example.traveloffice.controller;

import com.example.traveloffice.domain.HotelDto;
import com.example.traveloffice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/traveloffice/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @RequestMapping(method = RequestMethod.GET, value = "getHotels")
    public List<HotelDto> getHotels() {
        return hotelService.getHotels();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getHotel")
    public HotelDto getHotel(@RequestParam Long hotelId) {
        return hotelService.getHotel(hotelId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteHotel")
    public void deleteHotel(@RequestParam Long hotelId) {
        hotelService.deleteHotel(hotelId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateHotel")
    public HotelDto updateHotel(@RequestBody HotelDto hotelDto) {
        return hotelService.update(hotelDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createHotel", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createHotel(@RequestBody HotelDto hotelDto) {
        hotelService.create(hotelDto);
    }
}
