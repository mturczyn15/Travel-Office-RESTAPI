package com.example.traveloffice.repository;

import com.example.traveloffice.domain.AddressOperation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface AddressOperationRepository extends CrudRepository<AddressOperation, Long> {
    @Override
    AddressOperation save(AddressOperation operation);
}