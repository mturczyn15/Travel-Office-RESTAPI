package com.example.traveloffice.controller;

import com.example.traveloffice.domain.CustomerDto;
import com.example.traveloffice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET, value = "customers")
    public List<CustomerDto> getCustomers() {
        return customerService.getCustomers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "customers/{customerId}")
    public CustomerDto getCustomer(@PathVariable Long customerId) {
        return customerService.getCustomer(customerId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "customers/firstname")
    public List<CustomerDto> getCustomersByName(@RequestParam String name) {
        return customerService.getCustomersByFirstName(name);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "customers")
    public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.update(customerDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "customers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCustomer(@RequestBody CustomerDto customerDto) {
        customerService.create(customerDto);
    }


}
