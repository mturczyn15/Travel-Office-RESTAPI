package com.example.traveloffice.service;

import com.example.traveloffice.domain.Address;
import com.example.traveloffice.domain.AddressDto;
import com.example.traveloffice.domain.Customer;
import com.example.traveloffice.domain.EntityNotFoundException;
import com.example.traveloffice.mapper.AddressMapper;
import com.example.traveloffice.repository.AddressOperationRepository;
import com.example.traveloffice.repository.AddressRepository;
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
public class AddressServiceController {
    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AddressMapper addressMapper;

    @Mock
    private AddressOperationRepository addressOperationRepository;

    @Test
    public void testGetAddresses() {
        //Given
        List<Address> list = new ArrayList<>();
        list.add(new Address(1L, new Customer(), "street", "4","city", "435", "5"));
        List<AddressDto> listDto = new ArrayList<>();
        listDto.add(new AddressDto(1L, 1L, "street", "4","city", "435", "5"));
        when(addressMapper.mapToDtoList(list)).thenReturn(listDto);
        when(addressRepository.findAll()).thenReturn(list);
        //When
        List<AddressDto> retrievedList = addressService.getAddresses();
        //Then
        assertEquals(1, retrievedList.size());
    }

    @Test
    public void testGetAddress() {
        //Given
        Optional<Address> address = Optional.of(new Address(1L, new Customer(), "street", "4","city", "435", "5"));
        Long id = address.get().getId();
        AddressDto addressDto = new AddressDto(1L, 1L, "street", "4","city", "435", "5");
        when(addressRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(address);
        when(addressMapper.mapToDto(address.orElseThrow(() -> new EntityNotFoundException(Address.class, id)))).thenReturn(addressDto);
        //When
        AddressDto retrievedAddressDto = addressService.getAddress(id);
        //Then
        assertEquals(1, retrievedAddressDto.getId());
    }

    @Test
    public void testCreate() {
        //Given
        Address address = new Address(1L, new Customer(), "street", "4","city", "435", "5");
        AddressDto addressDto = new AddressDto(1L, 1L, "street", "4","city", "435", "5");
        Customer customer = new Customer(1L, "task", "city", "3456", "mail", new ArrayList<>(), new ArrayList<>());
        Optional<Customer> optCustomer = Optional.of(customer);
        when(customerRepository.findById(customer.getId())).thenReturn(optCustomer);
        when(addressMapper.map(addressDto, customer)).thenReturn(address);
        when(addressRepository.save(address)).thenReturn(address);
        when(addressMapper.mapToDto(address)).thenReturn(addressDto);
        //When
        AddressDto createdAddress = addressService.create(addressDto);
        //Then
        assertNull(createdAddress.getId());
    }

    @Test
    public void testUpdate() {
        //Given
        Optional<Address> optAddress = Optional.of(new Address(1L, new Customer(), "street", "4","city", "435", "5"));
        Address address = new Address(1L, new Customer(), "street", "4","city", "435", "5");
        AddressDto addressDto = new AddressDto(1L, 1L, "street", "4","city", "435", "5");
        Customer customer = new Customer(1L, "task", "city", "3456", "mail", new ArrayList<>(), new ArrayList<>());
        Optional<Customer> optCustomer = Optional.of(customer);
        when(customerRepository.findById(customer.getId())).thenReturn(optCustomer);
        when(addressMapper.map(addressDto, customer)).thenReturn(address);
        when(addressRepository.save(address)).thenReturn(address);
        when(addressMapper.mapToDto(address)).thenReturn(addressDto);
        when(addressRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(optAddress);
        //When
        AddressDto updatedAddress = addressService.update(addressDto);
        //Then
        assertEquals(1, updatedAddress.getId());
    }

    @Test
    public void testDelete() {
        //Given
        Optional<Address> optAddress = Optional.of(new Address(1L, new Customer(), "street", "4","city", "435", "5"));
        Address address = new Address(1L, new Customer(), "street", "4","city", "435", "5");
        when(addressRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(optAddress);
        //When
        addressService.deleteAddress(address.getId());
        //Then
        verify(addressRepository, times(1)).deleteById(address.getId());
    }
}
