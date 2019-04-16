package com.example.employee.repository;

import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeState;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeJPARepository extends MongoRepository<EmployeeState,String> {

    @Query(value="{ 'id' : ?0 }")
    EmployeeState findById_id(String id);

    List<Employee> findAllByIdAndAge();
}
