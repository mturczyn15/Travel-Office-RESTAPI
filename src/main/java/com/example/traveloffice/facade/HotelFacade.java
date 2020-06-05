package com.example.traveloffice.facade;

import com.example.traveloffice.domain.Hotel;
import com.example.traveloffice.domain.HotelDto;
import com.example.traveloffice.mapper.HotelMapper;
import com.example.traveloffice.service.HotelService;
import com.example.traveloffice.validator.HotelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelFacade {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private HotelValidator hotelValidator;

    public HotelDto create(HotelDto hotelDto) {
        Hotel hotel = hotelMapper.map(hotelService.create(hotelDto));
        hotelValidator.validateHotel(hotel);
        return hotelMapper.mapToDto(hotel);
    }

    public HotelDto update(HotelDto hotelDto) {
        Hotel hotel = hotelMapper.map(hotelService.update(hotelDto));
        hotelValidator.validateHotel(hotel);
        return hotelMapper.mapToDto(hotel);
    }

    public List<HotelDto> getHotels() {
        return hotelService.getHotels();
    }

    public HotelDto getHotel(Long id) {
        return hotelService.getHotel(id);
    }

    public void deleteHotel(Long hotelId) {
        hotelService.deleteHotel(hotelId);
    }

}
