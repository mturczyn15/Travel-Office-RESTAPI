package com.example.traveloffice.service;

import com.example.traveloffice.domain.Address;
import com.example.traveloffice.domain.Customer;
import com.example.traveloffice.domain.CustomerDto;
import com.example.traveloffice.domain.EntityNotFoundException;
import com.example.traveloffice.mapper.CustomerMapper;
import com.example.traveloffice.repository.AddressRepository;
import com.example.traveloffice.repository.CustomerOperationRepository;
import com.example.traveloffice.repository.CustomerRepository;
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
public class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private CustomerOperationRepository customerOperationRepository;

    @Test
    public void testGetCustomers() {
        //Given
        List<Customer> list = new ArrayList<>();
        list.add(new Customer(1L,1L, "task", "city", "3456", "dada", "huj", new ArrayList<>(), new ArrayList<>()));
        List<CustomerDto> listDto = new ArrayList<>();
        listDto.add(new CustomerDto(1L,1L, "task", "city", "3456", "dada", "mail"));
        when(customerMapper.mapToDtoList(list)).thenReturn(listDto);
        when(customerRepository.findAll()).thenReturn(list);
        //When
        List<CustomerDto> retrievedList = customerService.getCustomers();
        //Then
        assertEquals(1, retrievedList.size());
    }

    @Test
    public void testGetCustomer() {
        //Given
        Optional<Customer> customer = Optional.of(new Customer(1L,1L, "task", "city", "3456", "dada", "mail", new ArrayList<>(), new ArrayList<>()));
        Long id = customer.get().getId();
        CustomerDto customerDto = new CustomerDto(1L,1L, "task", "city", "3456", "dada", "mail");
        when(customerRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(customer);
        when(customerMapper.mapToDto(customer.orElseThrow(() -> new EntityNotFoundException(Customer.class, id)))).thenReturn(customerDto);
        //When
        CustomerDto retrievedCustomerDto = customerService.getCustomer(id);
        //Then
        assertEquals(1, retrievedCustomerDto.getId());
    }

    @Test
    public void testCreate() {
        //Given
        Customer customer = new Customer(1L,1L, "task", "city", "3456", "dada", "mail", new ArrayList<>(), new ArrayList<>());
        Address address = new Address(1L, customer, "street", "4","city", "435", "5");
        CustomerDto customerDto = new CustomerDto(1L,1L, "task", "city", "3456", "dada", "mail");
        Optional<Address> optAddress = Optional.of(address);
        when(customerMapper.map(customerDto)).thenReturn(customer);
        when(addressRepository.findById(customerDto.getMainAddressId())).thenReturn(optAddress);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.mapToDto(customer)).thenReturn(customerDto);
        //When
        CustomerDto createdCustomer = customerService.create(customerDto);
        //Then
        assertNull(createdCustomer.getId());
    }

    @Test
    public void testUpdate() {
        //Given
        Optional<Customer> optCustomer = Optional.of(new Customer(1L,1L, "task", "city", "3456", "dada", "huj", new ArrayList<>(), new ArrayList<>()));
        Customer customer = new Customer(1L,1L, "task", "city", "3456", "dada", "huj", new ArrayList<>(), new ArrayList<>());
        CustomerDto customerDto = new CustomerDto(1L,1L, "task", "city", "3456", "dada", "mail");
        Address address = new Address(1L, customer, "street", "4","city", "435", "5");
        Optional<Address> optAddress = Optional.of(address);
        when(addressRepository.findById(customerDto.getMainAddressId())).thenReturn(optAddress);
        when(customerMapper.map(customerDto)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.mapToDto(customer)).thenReturn(customerDto);
        when(customerRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(optCustomer);
        //When
        CustomerDto updatedCustomer = customerService.update(customerDto);
        //Then
        assertEquals(1, updatedCustomer.getId());
    }

    @Test
    public void testDelete() {
        //Given
        Optional<Customer> optCustomer = Optional.of(new Customer(1L,1L, "task", "city", "3456", "dada", "huj", new ArrayList<>(), new ArrayList<>()));
        Customer customer = new Customer(1L,1L, "task", "city", "3456", "dada", "huj", new ArrayList<>(), new ArrayList<>());
        when(customerRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(optCustomer);
        //When
        customerService.deleteCustomer(customer.getId());
        //Then
        verify(customerRepository, times(1)).deleteById(customer.getId());
    }
}
