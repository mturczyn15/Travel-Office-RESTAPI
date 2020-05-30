package com.example.traveloffice.controller;

import com.example.traveloffice.domain.AddressDto;
import com.example.traveloffice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping(method = RequestMethod.GET, value = "addresses")
    public List<AddressDto> getAddresses() {
        return addressService.getAddresses();
    }

    @RequestMapping(method = RequestMethod.GET, value = "addresses/{addressId}")
    public AddressDto getAddress(@PathVariable Long addressId) {
        return addressService.getAddress(addressId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "addresses/{addressId}")
    public void deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addresses", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AddressDto updateAddress(@RequestBody AddressDto addressDto) {
        return addressService.update(addressDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "addresses", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createAddress(@RequestBody AddressDto addressDto) {
        addressService.create(addressDto);
    }

}
