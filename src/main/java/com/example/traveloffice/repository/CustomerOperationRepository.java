package com.example.traveloffice.repository;

import com.example.traveloffice.domain.CustomerOperation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CustomerOperationRepository extends CrudRepository<CustomerOperation, Long> {
    @Override
    CustomerOperation save(CustomerOperation operation);
}