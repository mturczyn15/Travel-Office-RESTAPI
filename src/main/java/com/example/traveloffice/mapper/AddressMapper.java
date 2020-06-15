package com.example.traveloffice.mapper;

import com.example.traveloffice.domain.Address;
import com.example.traveloffice.domain.AddressDto;
import com.example.traveloffice.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressMapper {
    public Address map(final AddressDto addressDto, final Customer customer) {
        return Address.builder()
                .id(addressDto.getId())
                .customer(customer)
                .street(addressDto.getStreet())
                .houseNumber(addressDto.getHouseNumber())
                .city(addressDto.getCity())
                .zipCode(addressDto.getZipCode())
                .phoneNumber(addressDto.getPhoneNumber())
                .build();
    }

    public AddressDto mapToDto(final Address address) {
        return new AddressDto(
                address.getId(),
                address.getCustomer().getId(),
                address.getStreet(),
                address.getHouseNumber(),
                address.getCity(),
                address.getZipCode(),
                address.getPhoneNumber()
        );
    }

    public List<AddressDto> mapToDtoList(final List<Address> addressList) {
        return addressList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
