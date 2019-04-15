package com.example.employee.repository;


import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeState;
import com.example.employee.model.dto.EmployeeDto;
import io.vavr.control.Either;


import java.util.List;


public interface EmployeeRepo {

    String saveEmployee(EmployeeState employeeState);

    List<EmployeeDto> getAllEmps();

     Employee getById(String empId);


}
