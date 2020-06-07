package com.example.traveloffice.service;

import com.example.traveloffice.domain.EntityNotFoundException;
import com.example.traveloffice.domain.TravelAgency;
import com.example.traveloffice.domain.TravelAgencyDto;
import com.example.traveloffice.mapper.TravelAgencyMapper;
import com.example.traveloffice.repository.TravelAgencyOperationRepository;
import com.example.traveloffice.repository.TravelAgencyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TravelAgencyServiceTest {
    @InjectMocks
    private TravelAgencyService travelAgencyService;

    @Mock
    private TravelAgencyRepository travelAgencyRepository;

    @Mock
    private TravelAgencyMapper travelAgencyMapper;

    @Mock
    private TravelAgencyOperationRepository travelAgencyOperationRepository;

    @Test
    public void testGetTravelAgencys() {
        //Given
        List<TravelAgency> list = new ArrayList<>();
        list.add(new TravelAgency(1L, "task", "city", "3456"));
        List<TravelAgencyDto> listDto = new ArrayList<>();
        listDto.add(new TravelAgencyDto(1L, "task", "city", "3456"));
        when(travelAgencyMapper.mapToDtoList(list)).thenReturn(listDto);
        when(travelAgencyRepository.findAll()).thenReturn(list);
        //When
        List<TravelAgencyDto> retrievedList = travelAgencyService.getTravelAgencies();
        //Then
        assertEquals(1, retrievedList.size());
    }

    @Test
    public void testGetTravelAgency() {
        //Given
        Optional<TravelAgency> travelAgency = Optional.of(new TravelAgency(1L, "task", "city", "3456"));
        Long id = travelAgency.get().getId();
        TravelAgencyDto travelAgencyDto = new TravelAgencyDto(1L, "task", "city", "3456");
        when(travelAgencyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(travelAgency);
        when(travelAgencyMapper.mapToDto(travelAgency.orElseThrow(() -> new EntityNotFoundException(TravelAgency.class, id)))).thenReturn(travelAgencyDto);
        //When
        TravelAgencyDto retrievedTravelAgencyDto = travelAgencyService.getTravelAgency(id);
        //Then
        assertEquals(1, retrievedTravelAgencyDto.getId());
    }

    @Test
    public void testCreate() {
        //Given
        TravelAgency travelAgency = new TravelAgency(1L, "task", "city", "3456");
        TravelAgencyDto travelAgencyDto = new TravelAgencyDto(1L, "task", "city", "3456");
        when(travelAgencyMapper.map(travelAgencyDto)).thenReturn(travelAgency);
        when(travelAgencyRepository.save(travelAgency)).thenReturn(travelAgency);
        when(travelAgencyMapper.mapToDto(travelAgency)).thenReturn(travelAgencyDto);
        //When
        TravelAgencyDto createdTravelAgency = travelAgencyService.create(travelAgencyDto);
        //Then
        assertNull(createdTravelAgency.getId());
    }

    @Test
    public void testUpdate() {
        //Given
        Optional<TravelAgency> optTravelAgency = Optional.of(new TravelAgency(1L, "task", "city", "3456"));
        TravelAgency travelAgency = new TravelAgency(1L, "task", "city", "3456");
        TravelAgencyDto travelAgencyDto = new TravelAgencyDto(1L, "task", "city", "3456");
        when(travelAgencyMapper.map(travelAgencyDto)).thenReturn(travelAgency);
        when(travelAgencyRepository.save(travelAgency)).thenReturn(travelAgency);
        when(travelAgencyMapper.mapToDto(travelAgency)).thenReturn(travelAgencyDto);
        when(travelAgencyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(optTravelAgency);
        //When
        TravelAgencyDto updatedTravelAgency = travelAgencyService.update(travelAgencyDto);
        //Then
        assertEquals(1, updatedTravelAgency.getId());
    }

    @Test
    public void testDelete() {
        //Given
        Optional<TravelAgency> optTravelAgency = Optional.of(new TravelAgency(1L, "task", "city", "3456"));
        TravelAgency travelAgency = new TravelAgency(1L, "task", "city", "3456");
        when(travelAgencyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(optTravelAgency);
        //When
        travelAgencyService.deleteTravelAgency(travelAgency.getId());
        //Then
        verify(travelAgencyRepository, times(1)).deleteById(travelAgency.getId());
    }
}
