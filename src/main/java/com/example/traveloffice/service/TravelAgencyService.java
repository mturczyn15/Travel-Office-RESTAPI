package com.example.traveloffice.service;

import com.example.traveloffice.domain.EntityNotFoundException;
import com.example.traveloffice.domain.TravelAgency;
import com.example.traveloffice.domain.TravelAgencyDto;
import com.example.traveloffice.mapper.TravelAgencyMapper;
import com.example.traveloffice.repository.TravelAgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TravelAgencyService {
    @Autowired
    private TravelAgencyRepository travelAgencyRepository;
    @Autowired
    private TravelAgencyMapper travelAgencyMapper;

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
}
