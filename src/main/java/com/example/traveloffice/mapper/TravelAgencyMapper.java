package com.example.traveloffice.mapper;

import com.example.traveloffice.domain.TravelAgency;
import com.example.traveloffice.domain.TravelAgencyDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TravelAgencyMapper {
    public TravelAgency map(final TravelAgencyDto travelAgencyDto) {
        return TravelAgency.builder()
                .id(travelAgencyDto.getId())
                .name(travelAgencyDto.getName())
                .city(travelAgencyDto.getCity())
                .phoneNumber(travelAgencyDto.getPhoneNumber())
                .build();
    }

    public TravelAgencyDto mapToDto(final TravelAgency travelAgency) {
        return new TravelAgencyDto(
                travelAgency.getId(),
                travelAgency.getName(),
                travelAgency.getCity(),
                travelAgency.getPhoneNumber()
        );
    }

    public List<TravelAgencyDto> mapToDtoList(final List<TravelAgency> travelAgencyList) {
        return travelAgencyList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
