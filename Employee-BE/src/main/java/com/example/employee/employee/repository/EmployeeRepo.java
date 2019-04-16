package com.example.employee.employee.repository;


import com.example.employee.employee.model.Employee;
import com.example.employee.employee.model.EmployeeState;
import com.example.employee.employee.model.dto.EmployeeDto;


import java.util.List;


public interface EmployeeRepo {

    String saveEmployee(EmployeeState employeeState);

    List<EmployeeDto> getAllEmps();

     Employee getById(String empId);


}
