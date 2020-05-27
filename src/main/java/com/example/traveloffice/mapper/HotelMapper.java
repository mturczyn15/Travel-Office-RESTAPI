package com.example.traveloffice.mapper;

import com.example.traveloffice.domain.Hotel;
import com.example.traveloffice.domain.HotelDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelMapper {
    public Hotel map(final HotelDto hotelDto) {
        return Hotel.builder()
                .id(hotelDto.getId())
                .name(hotelDto.getName())
                .city(hotelDto.getCity())
                .stars(hotelDto.getStars())
                .phoneNumber(hotelDto.getPhoneNumber())
                .build();
    }

    public HotelDto mapToDto(final Hotel hotel) {
        return new HotelDto.HotelDtoBuilder()
                .id(hotel.getId())
                .name(hotel.getName())
                .city(hotel.getCity())
                .stars(hotel.getStars())
                .phoneNumber(hotel.getPhoneNumber())
                .build();
    }

    public List<HotelDto> mapToDtoList(final List<Hotel> hotelList) {
        return hotelList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
