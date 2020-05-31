package com.example.traveloffice.entity;

import com.example.traveloffice.domain.Address;
import com.example.traveloffice.repository.AddressRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressCrudTestSuite {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void testSaveAddress() {
        //Given
        Address address = Address.builder()
                .city("Radom")
                .houseNumber("13")
                .phoneNumber("000")
                .street("Dobre")
                .zipCode("31313213")
                .build();
        //When
        Address saveAddress = addressRepository.save(address);
        Long addressId = saveAddress.getId();
        //Then
        assertNotEquals((Object) 0L, addressId);
        assertEquals("Radom", saveAddress.getCity());
        assertEquals("13", saveAddress.getHouseNumber());
        assertEquals("000", saveAddress.getPhoneNumber());
        assertEquals("Dobre", saveAddress.getStreet());
        assertEquals("31313213", saveAddress.getZipCode());
        //CleanUp
        addressRepository.deleteById(addressId);
    }

    @Test
    public void testDeleteAddress() {
        //Given
        Address address = Address.builder()
                .city("Radom")
                .houseNumber("13")
                .phoneNumber("000")
                .street("Dobre")
                .zipCode("31313213")
                .build();
        //When
        Address saveAddress = addressRepository.save(address);
        Long addressId = saveAddress.getId();
        addressRepository.deleteById(addressId);
        //Then
        assertEquals(0, addressRepository.findAll().size());
    }


    @Test
    public void testGetAllAddresses() {
        //Given
        Address address1 = Address.builder()
                .id(11L)
                .city("3")
                .houseNumber("13")
                .phoneNumber("000")
                .street("Dobre")
                .zipCode("31313213")
                .build();

        Address address2 = Address.builder()
                .id(21L)
                .city("2")
                .houseNumber("13")
                .phoneNumber("000")
                .street("Dobre")
                .zipCode("31313213")
                .build();
        //When
        Address saveAddress1 = addressRepository.save(address1);
        Address saveAddress2 = addressRepository.save(address2);
        Long addressId1 = saveAddress1.getId();
        Long addressId2 = saveAddress2.getId();
        int countOfAddresses = addressRepository.findAll().size();
        //Then
        Assert.assertEquals(2, countOfAddresses);
        //CleanUp
        addressRepository.deleteById(addressId1);
        addressRepository.deleteById(addressId2);
    }


    @Test
    public void testGetAddressById() {
        Address address = Address.builder()
                .city("Radom")
                .houseNumber("13")
                .phoneNumber("000")
                .street("Dobre")
                .zipCode("31313213")
                .build();

        Address saveAddress = addressRepository.save(address);
        Long addressId = saveAddress.getId();

        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        Assert.assertTrue(optionalAddress.isPresent());
        addressRepository.deleteById(addressId);
    }
}

