package com.example.traveloffice.service;

import com.example.traveloffice.domain.*;
import com.example.traveloffice.mapper.TravelAgencyMapper;
import com.example.traveloffice.repository.TravelAgencyOperationRepository;
import com.example.traveloffice.repository.TravelAgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Component
public class TravelAgencyService {
    @Autowired
    private TravelAgencyRepository travelAgencyRepository;
    @Autowired
    private TravelAgencyMapper travelAgencyMapper;
    @Autowired
    private TravelAgencyOperationRepository travelAgencyOperationRepository;

    public TravelAgencyDto create(final TravelAgencyDto travelAgencyDto) {
        travelAgencyDto.setId(null);
        TravelAgency travelAgency = travelAgencyMapper.map(travelAgencyDto);
        return travelAgencyMapper.mapToDto(travelAgencyRepository.save(travelAgency));
    }

    public TravelAgencyDto update(final TravelAgencyDto travelAgencyDto) {
        travelAgencyRepository.findById(travelAgencyDto.getId()).orElseThrow(() -> new EntityNotFoundException(TravelAgency.class, travelAgencyDto.getId()));
        return travelAgencyMapper.mapToDto(travelAgencyRepository.save(travelAgencyMapper.map(travelAgencyDto)));
    }

    public List<TravelAgencyDto> getTravelAgencies() {
        return travelAgencyMapper.mapToDtoList(travelAgencyRepository.findAll());
    }

    public TravelAgencyDto getTravelAgency(final Long id) {
        Optional<TravelAgency> travelAgency = travelAgencyRepository.findById(id);
        return travelAgencyMapper.mapToDto(travelAgency.orElseThrow(() -> new EntityNotFoundException(TravelAgency.class, id)));
    }

    public void deleteTravelAgency(final Long travelAgencyId) {
        travelAgencyRepository.findById(travelAgencyId).orElseThrow(() -> new EntityNotFoundException(TravelAgency.class, travelAgencyId));
        travelAgencyRepository.deleteById(travelAgencyId);
    }

    public List<TravelAgencyDto> getTravelAgenciesByName(String name) {
        List<TravelAgency> travelAgenciesByNameContains = travelAgencyRepository.findTravelAgenciesByNameContains(name);
        for (TravelAgency travelAgency : travelAgenciesByNameContains) {
            travelAgencyOperationRepository.save(TravelAgencyOperation.builder()
                    .travelAgency("Id: " + travelAgency.getId() + travelAgency.getName())
                    .operation(Operation.GET)
                    .date(LocalDate.now())
                    .time(LocalTime.now())
                    .build()
            );
        }
        return travelAgencyMapper.mapToDtoList(travelAgenciesByNameContains);
    }
}
