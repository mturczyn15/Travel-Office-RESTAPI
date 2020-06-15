package com.example.traveloffice.service;

import com.example.traveloffice.domain.*;
import com.example.traveloffice.mapper.BookingMapper;
import com.example.traveloffice.repository.BookingRepository;
import com.example.traveloffice.repository.CustomerRepository;
import com.example.traveloffice.repository.HotelRepository;
import com.example.traveloffice.repository.TravelAgencyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {
    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private TravelAgencyRepository travelAgencyRepository;

    @Mock
    private BookingMapper bookingMapper;

    @Test
    public void testGetBookinges() {
        //Given
        List<Booking> list = new ArrayList<>();
        list.add(new Booking(1L, new Customer(), new TravelAgency(), new Hotel(), new BigDecimal(345), LocalDate.of(2003,12,12), LocalDate.of(2003,12,19), PaymentType.CREDIT_CARD));
        List<BookingDto> listDto = new ArrayList<>();
        listDto.add(new BookingDto(1L, 1L, 1L, 1L, new BigDecimal(345), LocalDate.of(2003,12,12), LocalDate.of(2003,12,19), PaymentType.CREDIT_CARD));
        when(bookingMapper.mapToDtoList(list)).thenReturn(listDto);
        when(bookingRepository.findAll()).thenReturn(list);
        //When
        List<BookingDto> retrievedList = bookingService.getBookings();
        //Then
        assertEquals(1, retrievedList.size());
    }

    @Test
    public void testGetBooking() {
        //Given
        Optional<Booking> booking = Optional.of(new Booking(1L, new Customer(), new TravelAgency(), new Hotel(), new BigDecimal(345), LocalDate.of(2003,12,12), LocalDate.of(2003,12,19), PaymentType.CREDIT_CARD));
        Long id = booking.get().getId();
        BookingDto bookingDto = new BookingDto(1L, 1L, 1L, 1L, new BigDecimal(345), LocalDate.of(2003,12,12), LocalDate.of(2003,12,19), PaymentType.CREDIT_CARD);
        when(bookingRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(booking);
        when(bookingMapper.mapToDto(booking.orElseThrow(() -> new EntityNotFoundException(Booking.class, id)))).thenReturn(bookingDto);
        //When
        BookingDto retrievedBookingDto = bookingService.getBooking(id);
        //Then
        assertEquals(1, retrievedBookingDto.getId());
    }

    @Test
    public void testCreate() {
        //Given
        Booking booking = new Booking(1L, new Customer(), new TravelAgency(), new Hotel(), new BigDecimal(345), LocalDate.of(2003,12,12), LocalDate.of(2003,12,19), PaymentType.CREDIT_CARD);
        BookingDto bookingDto = new BookingDto(1L, 1L, 1L, 1L, new BigDecimal(345), LocalDate.of(2003,12,12), LocalDate.of(2003,12,19), PaymentType.CREDIT_CARD);
        Customer customer = new Customer(1L, "task", "city", "3456", "mail", new ArrayList<>());
        Optional<Customer> optCustomer = Optional.of(customer);
        TravelAgency travelAgency = new TravelAgency(1L, "task", "city", "3456");
        Optional<TravelAgency> optTravelAgency = Optional.of(travelAgency);
        Hotel hotel = new Hotel(1L, "task", "city", Stars.FIVE, "3456");
        Optional<Hotel> optHotel = Optional.of(hotel);
        when(customerRepository.findById(customer.getId())).thenReturn(optCustomer);
        when(travelAgencyRepository.findById(travelAgency.getId())).thenReturn(optTravelAgency);
        when(hotelRepository.findById(hotel.getId())).thenReturn(optHotel);
        when(bookingMapper.map(bookingDto, customer, travelAgency, hotel)).thenReturn(booking);
        when(bookingRepository.save(booking)).thenReturn(booking);
        when(bookingMapper.mapToDto(booking)).thenReturn(bookingDto);
        //When
        BookingDto createdBooking = bookingService.create(bookingDto);
        //Then
        assertNull(createdBooking.getId());
    }

    @Test
    public void testUpdate() {
        //Given
        Optional<Booking> optBooking = Optional.of(new Booking(1L, new Customer(), new TravelAgency(), new Hotel(), new BigDecimal(345), LocalDate.of(2003,12,12), LocalDate.of(2003,12,19), PaymentType.CREDIT_CARD));
        Booking booking = new Booking(1L, new Customer(), new TravelAgency(), new Hotel(), new BigDecimal(345), LocalDate.of(2003,12,12), LocalDate.of(2003,12,19), PaymentType.CREDIT_CARD);
        BookingDto bookingDto = new BookingDto(1L, 1L, 1L, 1L, new BigDecimal(345), LocalDate.of(2003,12,12), LocalDate.of(2003,12,19), PaymentType.CREDIT_CARD);
        Customer customer = new Customer(1L, "task", "city", "3456", "mail", new ArrayList<>());
        Optional<Customer> optCustomer = Optional.of(customer);
        TravelAgency travelAgency = new TravelAgency(1L, "task", "city", "3456");
        Optional<TravelAgency> optTravelAgency = Optional.of(travelAgency);
        Hotel hotel = new Hotel(1L, "task", "city", Stars.FIVE, "3456");
        Optional<Hotel> optHotel = Optional.of(hotel);
        when(customerRepository.findById(customer.getId())).thenReturn(optCustomer);
        when(travelAgencyRepository.findById(travelAgency.getId())).thenReturn(optTravelAgency);
        when(hotelRepository.findById(hotel.getId())).thenReturn(optHotel);
        when(bookingMapper.map(bookingDto, customer, travelAgency, hotel)).thenReturn(booking);
        when(bookingRepository.save(booking)).thenReturn(booking);
        when(bookingMapper.mapToDto(booking)).thenReturn(bookingDto);
        when(bookingRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(optBooking);
        //When
        BookingDto updatedBooking = bookingService.update(bookingDto);
        //Then
        assertEquals(1, updatedBooking.getId());
    }

    @Test
    public void testDelete() {
        //Given
        Optional<Booking> optBooking = Optional.of(new Booking(1L, new Customer(), new TravelAgency(), new Hotel(), new BigDecimal(345), LocalDate.of(2003,12,12), LocalDate.of(2003,12,19), PaymentType.CREDIT_CARD));
        Booking booking = new Booking(1L, new Customer(), new TravelAgency(), new Hotel(), new BigDecimal(345), LocalDate.of(2003,12,12), LocalDate.of(2003,12,19), PaymentType.CREDIT_CARD);
        when(bookingRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(optBooking);
        //When
        bookingService.deleteBooking(booking.getId());
        //Then
        verify(bookingRepository, times(1)).deleteById(booking.getId());
    }
}
