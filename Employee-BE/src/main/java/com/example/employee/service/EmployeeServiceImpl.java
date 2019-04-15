package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeList;
import com.example.employee.model.EmployeeState;
import com.example.employee.model.dto.EmployeeDto;
import com.example.employee.model.dto.EmployeeDtoConverter;
import com.example.employee.repository.EmployeeRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmpService {

    @Autowired
    private EmployeeRepo employeeRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public Either<Exception, EmployeeDto> getById(String id) {

        Employee employee = employeeRepo.getById(id);
        if (employee.getEmployeeState()!=null){
            EmployeeDto employeeDto= EmployeeDtoConverter.getInstance().empBeanToEmpDto(employee);
            return Either.right(employeeDto);
        }
        return Either.left(new Exception("Employee not found"));
    }

    @Override
    public Either<Exception, String> createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeDtoConverter.getInstance().empDtoToempBean(employeeDto);
        String encryptedPass= encoder.encode(employee.getEmployeeState().getPassword());
        employee.getEmployeeState().setPassword(encryptedPass);

        List<Employee> oldEmployeeList = EmployeeDtoConverter.getInstance().
                empDtoListToEmpBeanList(this.findAllEmps());
      if (oldEmployeeList.isEmpty()){
          EmployeeState employeeState = employee.create(employee);
         employeeRepo.saveEmployee(employeeState);
         return Either.right(employeeState.getId());
      }
      Optional<Employee> employeeOptional = oldEmployeeList.
              stream().filter(x->x.getEmployeeState().getId().equals(employeeDto.getId())).findAny();
        if(employeeOptional.isPresent()){
            return Either.left(new Exception("Employee already registered"));
        }
        EmployeeList employeeList = new EmployeeList(oldEmployeeList);
       Either empEither= employeeList.createEmployee(employee.getEmployeeState());
       if (empEither.isRight()){
           employeeRepo.saveEmployee((EmployeeState) empEither.get());
           return Either.right(((EmployeeState) empEither.get()).getId());
       }
        return Either.left(new Exception(empEither.getLeft().toString()));
    }

    @Override
    public List<EmployeeDto> findAllEmps() {

        return employeeRepo.getAllEmps();
    }
}
