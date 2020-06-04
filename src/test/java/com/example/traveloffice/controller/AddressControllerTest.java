package com.example.traveloffice.controller;

import com.example.traveloffice.domain.AddressDto;
import com.example.traveloffice.service.AddressService;
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
@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @Test
    public void shouldFetchEmptyAddresses() throws Exception {
        //Given
        List<AddressDto> addressesDto = new ArrayList<>();
        when(addressService.getAddresses()).thenReturn(addressesDto);
        //When&Then
        mockMvc.perform(get("/v1/addresses").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchAddresses() throws Exception {
        //Given
        List<AddressDto> addressesDto = new ArrayList<>();
        addressesDto.add(new AddressDto(1L, 1L, "street", "4","city", "435", "5"));
        when(addressService.getAddresses()).thenReturn(addressesDto);
        //When&Then
        mockMvc.perform(get("/v1/addresses").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].customerId", is(1)))
                .andExpect(jsonPath("$[0].street", is("street")))
                .andExpect(jsonPath("$[0].houseNumber", is("4")))

                .andExpect(jsonPath("$[0].city", is("city")))
                .andExpect(jsonPath("[0].zipCode", is("435")))
                .andExpect(jsonPath("[0].phoneNumber", is("5")));
    }


    @Test
    public void shouldFetchAddress() throws Exception {
        //Given
        AddressDto addressDto = new AddressDto(1L, 1L, "street", "4","city", "435", "5");
        when(addressService.getAddress(ArgumentMatchers.any(Long.class))).thenReturn(addressDto);
        //When&Then
        mockMvc.perform(get("/v1/addresses/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.customerId", is(1)))
                .andExpect(jsonPath("$.street", is("street")))
                .andExpect(jsonPath("$.houseNumber", is("4")))

                .andExpect(jsonPath("$.city", is("city")))
                .andExpect(jsonPath("$.zipCode", is("435")))
                .andExpect(jsonPath("$.phoneNumber", is("5")));
    }

    @Test
    public void shouldDeleteAddress() throws Exception {
        //Given
        AddressDto addressDto = new AddressDto(1L, 1L, "street", "4","city", "435", "5");
        when(addressService.create(addressDto)).thenReturn(addressDto);
        //When&Then
        mockMvc.perform(delete("/v1/addresses/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateAddress() throws Exception {
        //Given
        AddressDto addressDto = new AddressDto(1L, 1L, "street", "4","city", "435", "5");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(addressDto);
        when(addressService.create(addressDto)).thenReturn(addressDto);
        //When&Then
        mockMvc.perform(post("/v1/addresses")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
        //CleanUp
        addressService.deleteAddress(1L);
    }

    @Test
    public void shouldUpdateAddress() throws Exception {
        //Given
        AddressDto addressDto = new AddressDto(1L, 1L, "street", "4","city", "435", "5");
        AddressDto updatedAddressDto = new AddressDto(1L, 1L, "updatedstreet", "4","city", "435", "5");
        when(addressService.update(addressDto)).thenReturn(updatedAddressDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(addressDto);
        //When&Then
        mockMvc.perform(put("/v1/addresses")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.customerId", is(1)))
                .andExpect(jsonPath("$.street", is("updatedstreet")))
                .andExpect(jsonPath("$.houseNumber", is("4")))

                .andExpect(jsonPath("$.city", is("city")))
                .andExpect(jsonPath("$.zipCode", is("435")))
                .andExpect(jsonPath("$.phoneNumber", is("5")));
    }
}
