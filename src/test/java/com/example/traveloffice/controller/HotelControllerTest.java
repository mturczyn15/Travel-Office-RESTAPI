package com.example.traveloffice.controller;

import com.example.traveloffice.domain.HotelDto;
import com.example.traveloffice.service.HotelService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HotelController.class)
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelService hotelService;

    @Test
    public void shouldFetchEmptyHotels() throws Exception {
        //Given
        List<HotelDto> hotelsDto = new ArrayList<>();
        when(hotelService.getHotels()).thenReturn(hotelsDto);
        //When&Then
        mockMvc.perform(get("/v1/hotels").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchHotels() throws Exception {
        //Given
        List<HotelDto> hotelsDto = new ArrayList<>();
        hotelsDto.add(new HotelDto(1L, "hotel", "city", 4, "435"));
        when(hotelService.getHotels()).thenReturn(hotelsDto);
        //When&Then
        mockMvc.perform(get("/v1/hotels").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("hotel")))
                .andExpect(jsonPath("$[0].city", is("city")))
                .andExpect(jsonPath("[0].stars", is(4)))
                .andExpect(jsonPath("[0].phoneNumber", is("435")));
    }


    @Test
    public void shouldFetchHotel() throws Exception {
        //Given
        HotelDto hotelDto = new HotelDto(1L, "hotel", "city", 4, "435");
        when(hotelService.getHotel(ArgumentMatchers.any(Long.class))).thenReturn(hotelDto);
        //When&Then
        mockMvc.perform(get("/v1/hotels/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("hotel")))
                .andExpect(jsonPath("$.city", is("city")))
                .andExpect(jsonPath("$.stars", is(4)))
                .andExpect(jsonPath("$.phoneNumber", is("435")));
    }

    @Test
    public void shouldDeleteHotel() throws Exception {
        //Given
        HotelDto hotelDto = new HotelDto.HotelDtoBuilder()
                .id(1L)
                .city("city")
                .stars(3)
                .name("name")
                .phoneNumber("123")
                .build();
        when(hotelService.create(hotelDto)).thenReturn(hotelDto);
        //When&Then
        mockMvc.perform(delete("/v1/hotels/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateHotel() throws Exception {
        //Given
        HotelDto hotelDto = new HotelDto(1L, "hotel", "city", 4, "435");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(hotelDto);
        when(hotelService.create(hotelDto)).thenReturn(hotelDto);
        //When&Then
        mockMvc.perform(post("/v1/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdateHotel() throws Exception {
        //Given
        HotelDto hotelDto = new HotelDto(1L, "hotel", "city", 4, "435");
        HotelDto updatedHotelDto = new HotelDto(1L, "updatedhotel", "updatedcity", 4, "435");
        when(hotelService.update(hotelDto)).thenReturn(updatedHotelDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(hotelDto);
        //When&Then
        mockMvc.perform(put("/v1/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("updatedhotel")))
                .andExpect(jsonPath("$.city", is("updatedcity")))
                .andExpect(jsonPath("$.stars", is(4)))
                .andExpect(jsonPath("$.phoneNumber", is("435")));
    }
}
