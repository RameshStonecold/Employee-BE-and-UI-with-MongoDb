package com.example.employee.service;

import com.example.employee.model.dto.EmployeeDto;
import io.vavr.control.Either;



import java.util.List;

public interface IEmpService {
     Either<Exception, EmployeeDto> getById(String id);

     Either<Exception,String> createEmployee(EmployeeDto employeeDto);

   List<EmployeeDto> findAllEmps();

}
