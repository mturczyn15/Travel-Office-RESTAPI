package com.example.traveloffice.service;

import com.example.traveloffice.domain.*;
import com.example.traveloffice.mapper.CustomerMapper;
import com.example.traveloffice.repository.CustomerOperationRepository;
import com.example.traveloffice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerOperationRepository customerOperationRepository;

    public CustomerDto create(final CustomerDto customerDto) {
        customerDto.setId(null);
        Customer customer = customerMapper.map(customerDto);
        return customerMapper.mapToDto(customerRepository.save(customer));
    }

    public CustomerDto update(final CustomerDto customerDto) {
        customerRepository.findById(customerDto.getId()).orElseThrow(() -> new EntityNotFoundException(Customer.class, customerDto.getId()));
        return customerMapper.mapToDto(customerRepository.save(customerMapper.map(customerDto)));
    }

    public List<CustomerDto> getCustomers() {

        return customerMapper.mapToDtoList(customerRepository.findAll());
    }

    public CustomerDto getCustomer(final Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customerMapper.mapToDto(customer.orElseThrow(() -> new EntityNotFoundException(Customer.class, id)));
    }

    public void deleteCustomer(final Long customerId) {
        customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException(Customer.class, customerId));
        customerRepository.deleteById(customerId);
    }

    public List<CustomerDto> getCustomersByFirstName(String name) {
        List<Customer> customersByFirstNameContains = customerRepository.findCustomersByFirstNameContains(name);
        for (Customer customer : customersByFirstNameContains) {
            customerOperationRepository.save(CustomerOperation.builder()
                    .customer(customer.getFirstName() + customer.getLastName() + customer.getId())
                    .operation(Operation.GET)
                    .date(LocalDate.now())
                    .time(LocalTime.now())
                    .build()
            );
        }
        return customerMapper.mapToDtoList(customersByFirstNameContains);
    }
}