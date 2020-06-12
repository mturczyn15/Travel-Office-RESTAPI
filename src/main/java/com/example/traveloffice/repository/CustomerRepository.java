package com.example.traveloffice.repository;

import com.example.traveloffice.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Override
    List<Customer> findAll();

    @Override
    Optional<Customer> findById(Long id);

    @Override
    Customer save(Customer user);

    @Override
    void deleteById(Long id);

    List<Customer> findCustomersByFirstNameContains(String firstName);
}
