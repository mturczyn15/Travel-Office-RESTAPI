package com.example.traveloffice.controller;

import com.example.traveloffice.domain.BookingDto;
import com.example.traveloffice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/v1/")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @RequestMapping(method = RequestMethod.GET, value = "bookings")
    public List<BookingDto> getBookings() {
        return bookingService.getBookings();
    }

    @RequestMapping(method = RequestMethod.GET, value = "bookings/{bookingId}")
    public BookingDto getBooking(@PathVariable Long bookingId) {
        return bookingService.getBooking(bookingId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "bookings/{bookingId}")
    public void deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "bookings")
    public BookingDto updateBooking(@RequestBody BookingDto bookingDto) {
        return bookingService.update(bookingDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "bookings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBooking(@RequestBody BookingDto bookingDto) {
        bookingService.create(bookingDto);
    }

}
