package com.example.employee.repository;

import com.example.employee.model.EmployeeState;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeJPARepository extends MongoRepository<EmployeeState,String> {

    @Query(value="{ 'id' : ?0 }")
    EmployeeState findById_id(String id);
}
