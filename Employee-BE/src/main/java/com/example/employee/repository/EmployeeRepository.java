package com.example.employee.repository;

import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeState;
import com.example.employee.model.dto.EmployeeDto;
import com.example.employee.model.dto.EmployeeDtoConverter;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class EmployeeRepository implements EmployeeRepo {


   @Autowired
   private EmployeeJPARepository employeeJPARepository;

    @Override
    public String saveEmployee(EmployeeState employeeState) {
        employeeJPARepository.save(employeeState);
        return employeeState.getId();
    }

    @Override
    public List<EmployeeDto> getAllEmps() {

      List<Employee> allEmps=  employeeJPARepository.findAll().
              stream().map(x-> new Employee(x)).collect(Collectors.toList());

        return EmployeeDtoConverter.getInstance().empBeanListToEmpDtoList(allEmps);
    }

    @Override
    public Employee getById(String empId) {

      EmployeeState employeeState= employeeJPARepository.findById_id(empId);

      return new Employee(employeeState);

    }
}
