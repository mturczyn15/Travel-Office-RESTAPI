package com.example.traveloffice.entity;

import com.example.traveloffice.domain.*;
import com.example.traveloffice.repository.BookingRepository;
import com.example.traveloffice.repository.CustomerRepository;
import com.example.traveloffice.repository.HotelRepository;
import com.example.traveloffice.repository.TravelAgencyRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingCrudTestSuite {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private TravelAgencyRepository travelAgencyRepository;
    private Customer customer;
    private Hotel hotel;
    private TravelAgency travelAgency;

    @BeforeAll
    public void initEntities() {
        Customer customer = Customer.builder()
                .mainAddressId(1L)
                .firstName("test_firstName")
                .lastName("test_lastName")
                .login("test_login")
                .password("test_password")
                .email("test_email")
                .bookings(new ArrayList<>())
                .addresses(new ArrayList<>())
                .build();
        this.customer = customerRepository.save(customer);

        Hotel hotel = Hotel.builder()
                .name("Lucjusz")
                .city("Radom")
                .stars(3)
                .phoneNumber("000")
                .build();
        this.hotel = hotelRepository.save(hotel);

        TravelAgency travelAgency = TravelAgency.builder()
                .city("Radom")
                .name("Agency")
                .phoneNumber("000")
                .build();
        this.travelAgency = travelAgencyRepository.save(travelAgency);
    }

    @AfterAll
    public void cleanUp() {
        customerRepository.deleteById(1L);
        hotelRepository.deleteById(1L);
        travelAgencyRepository.deleteById(1L);
    }
    @Test
    public void testSaveBookingWithCustomerHotelAndTravelAgency() {

    }

    @Test
    public void testSaveBooking() {
        //Given
        Booking booking = Booking.builder()
                .customer(customer)
                .hotel(hotel)
                .paymentType(PaymentType.BLIK)
                .travelAgency(travelAgency)
                .price(new BigDecimal(2000))
                .startDate(LocalDate.of(2003,12,22))
                .endDate(LocalDate.of(2003,12, 27))
                .build();
        //When
        Booking saveBooking = bookingRepository.save(booking);
        Long bookingId = saveBooking.getId();
        //Then
        assertNotEquals((Object) 0L, bookingId);
        assertEquals(customer, saveBooking.getCustomer());
        assertEquals(hotel, saveBooking.getHotel());
        assertEquals(travelAgency, saveBooking.getTravelAgency());
        assertEquals(PaymentType.BLIK, saveBooking.getPaymentType());
        assertEquals(new BigDecimal(2000), saveBooking.getPrice());
        assertEquals(LocalDate.of(2003,12,22), saveBooking.getStartDate());
        assertEquals(LocalDate.of(2003,12,27), saveBooking.getEndDate());
        //CleanUp
        bookingRepository.deleteById(bookingId);
    }

    @Test
    public void testDeleteBooking() {
        //Given
        Booking booking = Booking.builder()
                .customer(customer)
                .hotel(hotel)
                .paymentType(PaymentType.BLIK)
                .travelAgency(travelAgency)
                .price(new BigDecimal(2000))
                .startDate(LocalDate.of(2003,12,22))
                .endDate(LocalDate.of(2003,12, 27))
                .build();
        //When
        Booking saveBooking = bookingRepository.save(booking);
        Long bookingId = saveBooking.getId();
        bookingRepository.deleteById(bookingId);
        //Then
        assertEquals(0, bookingRepository.findAll().size());
    }


    @Test
    public void testGetAllBookinges() {
        //Given
        Booking booking1 = Booking.builder()
                .customer(customer)
                .hotel(hotel)
                .paymentType(PaymentType.BLIK)
                .travelAgency(travelAgency)
                .price(new BigDecimal(2000))
                .startDate(LocalDate.of(2003,12,22))
                .endDate(LocalDate.of(2003,12, 27))
                .build();

        Booking booking2 = Booking.builder()
                .customer(customer)
                .hotel(hotel)
                .paymentType(PaymentType.BLIK)
                .travelAgency(travelAgency)
                .price(new BigDecimal(2000))
                .startDate(LocalDate.of(2003,12,22))
                .endDate(LocalDate.of(2003,12, 27))
                .build();
        //When
        Booking saveBooking1 = bookingRepository.save(booking1);
        Booking saveBooking2 = bookingRepository.save(booking2);
        Long bookingId1 = saveBooking1.getId();
        Long bookingId2 = saveBooking2.getId();
        int countOfTravelAgencies = bookingRepository.findAll().size();
        //Then
        Assert.assertEquals(2, countOfTravelAgencies);
        //CleanUp
        bookingRepository.deleteById(bookingId1);
        bookingRepository.deleteById(bookingId2);
    }


    @Test
    public void testGetBookingById() {
        //Given
        Booking booking = Booking.builder()
                .customer(customer)
                .hotel(hotel)
                .paymentType(PaymentType.BLIK)
                .travelAgency(travelAgency)
                .price(new BigDecimal(2000))
                .startDate(LocalDate.of(2003,12,22))
                .endDate(LocalDate.of(2003,12, 27))
                .build();
        //When
        Booking saveBooking = bookingRepository.save(booking);
        Long bookingId = saveBooking.getId();
        //Then
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        Assert.assertTrue(optionalBooking.isPresent());
        //CleanUp
        bookingRepository.deleteById(bookingId);
    }
}
