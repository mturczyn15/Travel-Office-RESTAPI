package com.example.traveloffice.service;

import com.example.traveloffice.domain.*;
import com.example.traveloffice.mapper.HotelMapper;
import com.example.traveloffice.repository.HotelOperationRepository;
import com.example.traveloffice.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Component
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private HotelOperationRepository hotelOperationRepository;

    public HotelDto create(final HotelDto hotelDto) {
        hotelDto.setId(null);
        Hotel hotel = hotelMapper.map(hotelDto);
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
        hotelOperationRepository.save(HotelOperation.builder()
                .hotel(hotel.orElse(null))
                .operation(Operation.GET)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build()
        );
        return hotelMapper.mapToDto(hotel.orElseThrow(() -> new EntityNotFoundException(Hotel.class, id)));
    }

    public void deleteHotel(final Long hotelId) {
        hotelRepository.findById(hotelId).orElseThrow(() -> new EntityNotFoundException(Hotel.class, hotelId));
        hotelRepository.deleteById(hotelId);
    }

    public List<HotelDto> getHotelsByName(String name) {
        List<Hotel> hotelsByNameContains = hotelRepository.findHotelsByNameContains(name);
        for (Hotel hotel : hotelsByNameContains) {
            hotelOperationRepository.save(HotelOperation.builder()
                    .hotel(hotel)
                    .operation(Operation.GET)
                    .date(LocalDate.now())
                    .time(LocalTime.now())
                    .build()
            );
        }
        return hotelMapper.mapToDtoList(hotelsByNameContains);
    }
}
