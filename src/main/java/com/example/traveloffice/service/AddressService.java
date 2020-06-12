package com.example.traveloffice.service;

import com.example.traveloffice.domain.*;
import com.example.traveloffice.mapper.AddressMapper;
import com.example.traveloffice.repository.AddressOperationRepository;
import com.example.traveloffice.repository.AddressRepository;
import com.example.traveloffice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressOperationRepository addressOperationRepository;

    public AddressDto create(final AddressDto addressDto) {
        Customer customer = customerRepository.findById(addressDto.getCustomerId()).orElseThrow(() -> new EntityNotFoundException(Customer.class, addressDto.getCustomerId()));
        addressDto.setId(null);
        Address address = addressMapper.map(addressDto, customer);
        return addressMapper.mapToDto(addressRepository.save(address));
    }

    public AddressDto update(final AddressDto addressDto) {
        addressRepository.findById(addressDto.getId()).orElseThrow(() -> new EntityNotFoundException(Address.class, addressDto.getId()));
        Customer customer = customerRepository.findById(addressDto.getCustomerId()).orElseThrow(() -> new EntityNotFoundException(Customer.class, addressDto.getCustomerId()));
        return addressMapper.mapToDto(addressRepository.save(addressMapper.map(addressDto, customer)));
    }

    public List<AddressDto> getAddresses() {
        return addressMapper.mapToDtoList(addressRepository.findAll());
    }

    public AddressDto getAddress(final Long id) {
        Optional<Address> address = addressRepository.findById(id);
        addressOperationRepository.save(AddressOperation.builder()
                .address(address.orElse(null))
                .operation(Operation.GET)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build()
        );
        return addressMapper.mapToDto(address.orElseThrow(() -> new EntityNotFoundException(Address.class, id)));
    }

    public void deleteAddress(final Long addressId) {
        addressRepository.findById(addressId).orElseThrow(() -> new EntityNotFoundException(Address.class, addressId));
        addressRepository.deleteById(addressId);
    }

    public List<AddressDto> getAddressesByCity(String city) {
        return addressMapper.mapToDtoList(addressRepository.findAddressesByCityContains(city));
    }
}
