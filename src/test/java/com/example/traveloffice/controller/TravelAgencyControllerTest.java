package com.example.traveloffice.controller;

import com.example.traveloffice.domain.TravelAgencyDto;
import com.example.traveloffice.service.TravelAgencyService;
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
@WebMvcTest(TravelAgencyController.class)
public class TravelAgencyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TravelAgencyService travelAgencyService;

    @Test
    public void shouldFetchEmptyTravelAgencies() throws Exception {
        //Given
        List<TravelAgencyDto> travelAgenciesDto = new ArrayList<>();
        when(travelAgencyService.getTravelAgencies()).thenReturn(travelAgenciesDto);
        //When&Then
        mockMvc.perform(get("/v1/travelAgencies").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchTravelAgencies() throws Exception {
        //Given
        List<TravelAgencyDto> travelAgenciesDto = new ArrayList<>();
        travelAgenciesDto.add(new TravelAgencyDto(1L, "travelAgency", "city", "435"));
        when(travelAgencyService.getTravelAgencies()).thenReturn(travelAgenciesDto);
        //When&Then
        mockMvc.perform(get("/v1/travelAgencies").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("travelAgency")))
                .andExpect(jsonPath("$[0].city", is("city")))
                .andExpect(jsonPath("[0].phoneNumber", is("435")));
    }


    @Test
    public void shouldFetchTravelAgency() throws Exception {
        //Given
        TravelAgencyDto travelAgencyDto = new TravelAgencyDto(1L, "travelAgency", "city", "435");
        when(travelAgencyService.getTravelAgency(ArgumentMatchers.any(Long.class))).thenReturn(travelAgencyDto);
        //When&Then
        mockMvc.perform(get("/v1/travelAgencies/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("travelAgency")))
                .andExpect(jsonPath("$.city", is("city")))
                .andExpect(jsonPath("$.phoneNumber", is("435")));
    }

    @Test
    public void shouldDeleteTravelAgency() throws Exception {
        //Given
        TravelAgencyDto travelAgencyDto = new TravelAgencyDto(1L, "travelAgency", "city", "435");
        when(travelAgencyService.create(travelAgencyDto)).thenReturn(travelAgencyDto);
        //When&Then
        mockMvc.perform(delete("/v1/travelAgencies/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateTravelAgency() throws Exception {
        //Given
        TravelAgencyDto travelAgencyDto = new TravelAgencyDto(1L, "travelAgency", "city", "435");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(travelAgencyDto);
        when(travelAgencyService.create(travelAgencyDto)).thenReturn(travelAgencyDto);
        //When&Then
        mockMvc.perform(post("/v1/travelAgencies")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdateTravelAgency() throws Exception {
        //Given
        TravelAgencyDto travelAgencyDto = new TravelAgencyDto(1L, "travelAgency", "city", "435");
        TravelAgencyDto updatedTravelAgencyDto = new TravelAgencyDto(1L, "updatedtravelAgency", "updatedcity", "435");
        when(travelAgencyService.update(travelAgencyDto)).thenReturn(updatedTravelAgencyDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(travelAgencyDto);
        //When&Then
        mockMvc.perform(put("/v1/travelAgencies")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("updatedtravelAgency")))
                .andExpect(jsonPath("$.city", is("updatedcity")))
                .andExpect(jsonPath("$.phoneNumber", is("435")));
    }
}
