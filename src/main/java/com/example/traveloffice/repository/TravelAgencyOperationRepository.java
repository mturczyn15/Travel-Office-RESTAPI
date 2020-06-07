package com.example.traveloffice.repository;

import com.example.traveloffice.domain.TravelAgencyOperation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface TravelAgencyOperationRepository extends CrudRepository<TravelAgencyOperation, Long> {
    @Override
    TravelAgencyOperation save(TravelAgencyOperation operation);
}
