package com.example.traveloffice.mapper;

import com.example.traveloffice.domain.Customer;
import com.example.traveloffice.domain.CustomerDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {
    public Customer map(final CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .login(customerDto.getLogin())
                .email(customerDto.getEmail())
                .build();
    }

    public CustomerDto mapToDto(final Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getLogin(),
                customer.getEmail());
    }

    public List<CustomerDto> mapToDtoList(final List<Customer> customerList) {
        return customerList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
