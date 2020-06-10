package com.example.traveloffice.controller;

import com.example.traveloffice.domain.CustomerDto;
import com.example.traveloffice.service.CustomerService;
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
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void shouldFetchEmptyCustomeres() throws Exception {
        //Given
        List<CustomerDto> customersDto = new ArrayList<>();
        when(customerService.getCustomers()).thenReturn(customersDto);
        //When&Then
        mockMvc.perform(get("/v1/customers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchCustomeres() throws Exception {
        //Given
        List<CustomerDto> customersDto = new ArrayList<>();
        customersDto.add(new CustomerDto(1L, "kamil", "4","city", "435"));
        when(customerService.getCustomers()).thenReturn(customersDto);
        //When&Then
        mockMvc.perform(get("/v1/customers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("kamil")))
                .andExpect(jsonPath("$[0].lastName", is("4")))
                .andExpect(jsonPath("$[0].login", is("city")))
                .andExpect(jsonPath("$[0].email", is("435")));
    }


    @Test
    public void shouldFetchCustomer() throws Exception {
        //Given
        CustomerDto customerDto = new CustomerDto(1L, "kamil", "4","city", "435");
        when(customerService.getCustomer(ArgumentMatchers.any(Long.class))).thenReturn(customerDto);
        //When&Then
        mockMvc.perform(get("/v1/customers/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("kamil")))
                .andExpect(jsonPath("$.lastName", is("4")))
                .andExpect(jsonPath("$.login", is("city")))
                .andExpect(jsonPath("$.email", is("435")));
    }

    @Test
    public void shouldDeleteCustomer() throws Exception {
        //Given
        CustomerDto customerDto = new CustomerDto(1L, "kamil", "4","city", "435");
        when(customerService.create(customerDto)).thenReturn(customerDto);
        //When&Then
        mockMvc.perform(delete("/v1/customers/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateCustomer() throws Exception {
        //Given
        CustomerDto customerDto = new CustomerDto(1L, "kamil", "4","city", "435");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(customerDto);
        when(customerService.create(customerDto)).thenReturn(customerDto);
        //When&Then
        mockMvc.perform(post("/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdateCustomer() throws Exception {
        //Given
        CustomerDto customerDto = new CustomerDto(1L, "kamil", "4","city", "435");
        CustomerDto updatedCustomerDto = new CustomerDto(1L, "updatedkamil", "4","city", "435");
        when(customerService.update(customerDto)).thenReturn(updatedCustomerDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(customerDto);
        //When&Then
        mockMvc.perform(put("/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("updatedkamil")))
                .andExpect(jsonPath("$.lastName", is("4")))
                .andExpect(jsonPath("$.login", is("city")))
                .andExpect(jsonPath("$.email", is("435")));
    }
}
