package com.example.traveloffice.entity;

import com.example.traveloffice.domain.Address;
import com.example.traveloffice.domain.Booking;
import com.example.traveloffice.domain.Customer;
import com.example.traveloffice.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerCrudTestSuite {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testSaveCustomerWithAddressAndBooking() {
        //Given
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

        Address address = Address.builder()
                .city("Radom")
                .houseNumber("13")
                .phoneNumber("000")
                .street("Dobre")
                .zipCode("31313213")
                .build();

        Booking booking = Booking.builder()
                .customer(customer)
                .startDate(LocalDate.of(2003,12,19))
                .endDate(LocalDate.of(2003,12,23))
                .build();

        customer.addAddress(address);
        customer.addBooking(booking);
        address.setCustomer(customer);
        booking.setCustomer(customer);


        //When
        customerRepository.save(customer);

        //Then
        Customer savedCustomer = customerRepository.save(customer);
        Long id = savedCustomer.getId();

        //Then
        Assert.assertNotEquals((Object) 0L, id);

        //CleanUp
        customerRepository.deleteById(id);
    }

    @Test
    public void testSaveCustomer() {
        //Given
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

        //When
        Customer savedCustomer = customerRepository.save(customer);
        Long id = savedCustomer.getId();

        //Then
        Assert.assertNotEquals((Object) 0L, id);

        //CleanUp
        customerRepository.deleteById(id);
    }

    @Test
    public void testDeleteCustomer() {
        //Given
        //Given
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
        //When
        Customer saveCustomer = customerRepository.save(customer);
        Long customerId = saveCustomer.getId();
        customerRepository.deleteById(customerId);
        //Then
        assertEquals(0, customerRepository.findAll().size());
    }


    @Test
    public void testGetAllCustomers() {
        //Given
        //Given
        Customer customer1 = Customer.builder()
                .mainAddressId(1L)
                .firstName("test_firstName")
                .lastName("test_lastName")
                .login("test_login")
                .password("test_password")
                .email("test_email")
                .bookings(new ArrayList<>())
                .addresses(new ArrayList<>())
                .build();

        //Given
        Customer customer2 = Customer.builder()
                .mainAddressId(1L)
                .firstName("test_firstName")
                .lastName("test_lastName")
                .login("test_login")
                .password("test_password")
                .email("test_email")
                .bookings(new ArrayList<>())
                .addresses(new ArrayList<>())
                .build();
        //When
        Customer saveCustomer1 = customerRepository.save(customer1);
        Customer saveCustomer2 = customerRepository.save(customer2);
        Long customerId1 = saveCustomer1.getId();
        Long customerId2 = saveCustomer2.getId();
        int countOfCustomers = customerRepository.findAll().size();
        //Then
        Assert.assertEquals(2, countOfCustomers);
        //CleanUp
        customerRepository.deleteById(customerId1);
        customerRepository.deleteById(customerId2);
    }


    @Test
    public void testGetCustomerById() {
        //Given
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

        Customer saveCustomer = customerRepository.save(customer);
        Long customerId = saveCustomer.getId();

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Assert.assertTrue(optionalCustomer.isPresent());
        customerRepository.deleteById(customerId);
    }
}
