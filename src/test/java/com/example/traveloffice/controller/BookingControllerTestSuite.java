package com.example.traveloffice.controller;

import com.example.traveloffice.domain.BookingDto;
import com.example.traveloffice.domain.PaymentType;
import com.example.traveloffice.service.BookingService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;


    @Test
    public void shouldFetchBooking() throws Exception {

        //Given
        BookingDto bookingDto = new BookingDto(1L, 1L, 1L, 1L, new BigDecimal(345), LocalDate.of(2003,12,12), LocalDate.of(2003,12,19), PaymentType.CREDIT_CARD);
        when(bookingService.getBooking(ArgumentMatchers.any(Long.class))).thenReturn(bookingDto);
        //When&Then
        mockMvc.perform(get("/v1/bookings/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.customerId", is(1)))
                .andExpect(jsonPath("$.travelAgencyId", is(1)))
                .andExpect(jsonPath("$.hotelId", is(1)))
                .andExpect(jsonPath("$.price", is(345)))
                .andExpect(jsonPath("$.startDate", is("2003-12-12")))
                .andExpect(jsonPath("$.endDate", is("2003-12-19")))
                .andExpect(jsonPath("$.paymentType", is("CREDIT_CARD")));
    }

    @Test
    public void shouldDeleteBooking() throws Exception {
        //Given
        BookingDto bookingDto = new BookingDto(1L, 1L, 1L, 1L, new BigDecimal(345), LocalDate.of(2003,12,12), LocalDate.of(2003,12,19), PaymentType.CREDIT_CARD);
        when(bookingService.create(bookingDto)).thenReturn(bookingDto);
        //When&Then
        mockMvc.perform(delete("/v1/bookings/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateBooking() throws Exception {
        //Given
        BookingDto bookingDto = new BookingDto(1L, 1L, 1L, 1L, new BigDecimal(345), LocalDate.of(2003,12,12), LocalDate.of(2003,12,19), PaymentType.CREDIT_CARD);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe()).create();
        String jsonContent = gson.toJson(bookingDto);
        when(bookingService.create(bookingDto)).thenReturn(bookingDto);
        //When&Then
        mockMvc.perform(post("/v1/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldFetchEmptyBookings() throws Exception {
        //Given
        List<BookingDto> bookingsDto = new ArrayList<>();
        when(bookingService.getBookings()).thenReturn(bookingsDto);
        //When&Then
        mockMvc.perform(get("/v1/bookings").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchBookings() throws Exception {
        //Given
        List<BookingDto> bookingsDto = new ArrayList<>();
        bookingsDto.add(new BookingDto(1L, 1L, 1L, 1L, new BigDecimal(345), LocalDate.of(2003,12,12), LocalDate.of(2003,12,19), PaymentType.CREDIT_CARD));
        when(bookingService.getBookings()).thenReturn(bookingsDto);
        //When&Then
        mockMvc.perform(get("/v1/bookings").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].customerId", is(1)))
                .andExpect(jsonPath("$[0].travelAgencyId", is(1)))
                .andExpect(jsonPath("$[0].hotelId", is(1)))
                .andExpect(jsonPath("$[0].price", is(345)))
                .andExpect(jsonPath("$[0].startDate", is("2003-12-12")))
                .andExpect(jsonPath("$[0].endDate", is("2003-12-19")))
                .andExpect(jsonPath("$[0].paymentType", is("CREDIT_CARD")));
    }


    @Test
    public void shouldUpdateBooking() throws Exception {
        //Given
        BookingDto bookingDto = new BookingDto(1L, 1L, 1L, 1L, new BigDecimal(345), LocalDate.of(2003,12,12), LocalDate.of(2003,12,19), PaymentType.CREDIT_CARD);
        BookingDto updatedBookingDto = new BookingDto(1L, 1L, 1L, 1L, new BigDecimal(345), LocalDate.of(2003,12,12), LocalDate.of(2003,12,19), PaymentType.BLIK);
        when(bookingService.update(bookingDto)).thenReturn(updatedBookingDto);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe()).create();
        String jsonContent = gson.toJson(bookingDto);
        //When&Then
        mockMvc.perform(put("/v1/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.customerId", is(1)))
                .andExpect(jsonPath("$.travelAgencyId", is(1)))
                .andExpect(jsonPath("$.hotelId", is(1)))
                .andExpect(jsonPath("$.price", is(345)))
                .andExpect(jsonPath("$.startDate", is("2003-12-12")))
                .andExpect(jsonPath("$.endDate", is("2003-12-19")))
                .andExpect(jsonPath("$.paymentType", is("BLIK")));
    }

    private static final class LocalDateAdapter extends TypeAdapter<LocalDate> {
        @Override
        public void write(final JsonWriter jsonWriter, final LocalDate localDate ) throws IOException {
            jsonWriter.value(localDate.toString());
        }

        @Override
        public LocalDate read( final JsonReader jsonReader ) throws IOException {
            return LocalDate.parse(jsonReader.nextString());
        }
    }
}
