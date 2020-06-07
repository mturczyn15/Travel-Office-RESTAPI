package com.example.traveloffice.repository;

import com.example.traveloffice.domain.Response;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ResponseRepository extends CrudRepository<Response, Long>{

        @Override
        Response save(Response response);
}
