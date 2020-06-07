package com.example.traveloffice.repository;

import com.example.traveloffice.domain.HotelOperation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface HotelOperationRepository extends CrudRepository<HotelOperation, Long> {
    @Override
    HotelOperation save(HotelOperation operation);
}
