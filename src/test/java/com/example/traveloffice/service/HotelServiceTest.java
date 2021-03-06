package com.example.traveloffice.service;

import com.example.traveloffice.domain.EntityNotFoundException;
import com.example.traveloffice.domain.Hotel;
import com.example.traveloffice.domain.HotelDto;
import com.example.traveloffice.domain.Stars;
import com.example.traveloffice.mapper.HotelMapper;
import com.example.traveloffice.repository.HotelOperationRepository;
import com.example.traveloffice.repository.HotelRepository;
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
public class HotelServiceTest {
    @InjectMocks
    private HotelService hotelService;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private HotelMapper hotelMapper;

    @Mock
    private HotelOperationRepository hotelOperationRepository;

    @Test
    public void testGetHotels() {
        //Given
        List<Hotel> list = new ArrayList<>();
        list.add(new Hotel(1L, "task", "city", Stars.FIVE, "3456"));
        List<HotelDto> listDto = new ArrayList<>();
        listDto.add(new HotelDto(1L, "task", "city", Stars.FIVE, "3456"));
        when(hotelMapper.mapToDtoList(list)).thenReturn(listDto);
        when(hotelRepository.findAll()).thenReturn(list);
        //When
        List<HotelDto> retrievedList = hotelService.getHotels();
        //Then
        assertEquals(1, retrievedList.size());
    }

    @Test
    public void testGetHotel() {
        //Given
        Optional<Hotel> hotel = Optional.of(new Hotel(1L, "task", "city", Stars.FIVE, "3456"));
        Long id = hotel.get().getId();
        HotelDto hotelDto = new HotelDto(1L, "task", "city", Stars.FIVE, "3456");
        when(hotelRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(hotel);
        when(hotelMapper.mapToDto(hotel.orElseThrow(() -> new EntityNotFoundException(Hotel.class, id)))).thenReturn(hotelDto);
        //When
        HotelDto retrievedHotelDto = hotelService.getHotel(id);
        //Then
        assertEquals(1, retrievedHotelDto.getId());
    }

    @Test
    public void testCreate() {
        //Given
        Hotel hotel = new Hotel(1L, "task", "city", Stars.FIVE, "3456");
        HotelDto hotelDto = new HotelDto(1L, "task", "city", Stars.FIVE, "3456");
        when(hotelMapper.map(hotelDto)).thenReturn(hotel);
        when(hotelRepository.save(hotel)).thenReturn(hotel);
        when(hotelMapper.mapToDto(hotel)).thenReturn(hotelDto);
        //When
        HotelDto createdHotel = hotelService.create(hotelDto);
        //Then
        assertNull(createdHotel.getId());
    }

    @Test
    public void testUpdate() {
        //Given
        Optional<Hotel> optHotel = Optional.of(new Hotel(1L, "task", "city", Stars.FIVE, "3456"));
        Hotel hotel = new Hotel(1L, "task", "city", Stars.FIVE, "3456");
        HotelDto hotelDto = new HotelDto(1L, "task", "city", Stars.FIVE, "3456");
        when(hotelMapper.map(hotelDto)).thenReturn(hotel);
        when(hotelRepository.save(hotel)).thenReturn(hotel);
        when(hotelMapper.mapToDto(hotel)).thenReturn(hotelDto);
        when(hotelRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(optHotel);
        //When
        HotelDto updatedHotel = hotelService.update(hotelDto);
        //Then
        assertEquals(1, updatedHotel.getId());
    }

    @Test
    public void testDelete() {
        //Given
        Optional<Hotel> optHotel = Optional.of(new Hotel(1L, "task", "city", Stars.FIVE, "3456"));
        Hotel hotel = new Hotel(1L, "task", "city", Stars.FIVE, "3456");
        when(hotelRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(optHotel);
        //When
        hotelService.deleteHotel(hotel.getId());
        //Then
        verify(hotelRepository, times(1)).deleteById(hotel.getId());
    }

}
