package com.example.employee.employee.service;

import com.example.employee.employee.model.dto.EmployeeDto;
import com.example.employee.employee.model.dto.ForgotPassword;
import io.vavr.control.Either;



import java.util.List;

public interface IEmpService {
     Either<Exception, EmployeeDto> getById(String id);

     Either<Exception,String> createEmployee(EmployeeDto employeeDto);

   List<EmployeeDto> findAllEmps();

    Either<Exception,String> forgotPassword(ForgotPassword forgotPassword);


}
