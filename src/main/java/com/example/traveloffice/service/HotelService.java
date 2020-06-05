package com.example.traveloffice.service;

import com.example.traveloffice.config.AdminConfig;
import com.example.traveloffice.domain.EntityNotFoundException;
import com.example.traveloffice.domain.Hotel;
import com.example.traveloffice.domain.HotelDto;
import com.example.traveloffice.domain.Mail;
import com.example.traveloffice.mapper.HotelMapper;
import com.example.traveloffice.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private SimpleEmailService simpleEmailService;
    @Autowired
    private AdminConfig adminConfig;

    public HotelDto create(final HotelDto hotelDto) {
        Hotel hotel = hotelMapper.map(hotelDto);
        simpleEmailService.send(
                Mail.builder()
                        .mailTo(adminConfig.getAmdinMail())
                        .Subject("New hotel")
                        .message("New hotel: " + hotel.getName() + " has been created.")
                        .build()

        );
        return hotelMapper.mapToDto(hotelRepository.save(hotel));
    }

    public HotelDto update(final HotelDto hotelDto) {
        hotelRepository.findById(hotelDto.getId()).orElseThrow(() -> new EntityNotFoundException(Hotel.class, hotelDto.getId()));
        return hotelMapper.mapToDto(hotelRepository.save(hotelMapper.map(hotelDto)));
    }

    public List<HotelDto> getHotels() {
        return hotelMapper.mapToDtoList(hotelRepository.findAll());
    }

    public HotelDto getHotel(final Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        return hotelMapper.mapToDto(hotel.orElseThrow(() -> new EntityNotFoundException(Hotel.class, id)));
    }

    public void deleteHotel(final Long hotelId) {
        hotelRepository.findById(hotelId).orElseThrow(() -> new EntityNotFoundException(Hotel.class, hotelId));
        hotelRepository.deleteById(hotelId);
    }
}
