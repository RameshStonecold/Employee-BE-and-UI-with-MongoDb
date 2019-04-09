package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeList;
import com.example.employee.model.EmployeeState;
import com.example.employee.model.dto.EmployeeDto;
import com.example.employee.model.dto.EmployeeDtoConverter;
import com.example.employee.repository.EmployeeRepo;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements IEmpService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Either<Exception, EmployeeDto> getById(String id) {

        Either empEither = employeeRepo.getById(id);
        if (empEither.isLeft()){
            return Either.left(new Exception("Employee not found"));
        }
         return Either.right(EmployeeDtoConverter.getInstance().empBeanToEmpDto((Employee) empEither.get()));
    }

    @Override
    public Either<Exception, String> createEmployee(EmployeeDto employeeDto) {

         List<Employee> oldEmployeeList=  EmployeeDtoConverter.getInstance().
              empDtoListToEmpBeanList(this.findAllEmps());

        Employee employee = EmployeeDtoConverter.getInstance().empDtoToempBean(employeeDto);

      if (oldEmployeeList.isEmpty()){
          EmployeeState employeeState = employee.create(employee);
         employeeRepo.saveEmployee(employeeState);
      }
        EmployeeList employeeList = new EmployeeList(oldEmployeeList);
       Either empEither= employeeList.createEmployee(employee);
       if (empEither.isRight()){

           employeeRepo.saveEmployee((EmployeeState) empEither.get());
           return Either.right(((EmployeeState) empEither.get()).getId());
       }
        return Either.left(new Exception("Emp already exist"));
    }

    @Override
    public List<EmployeeDto> findAllEmps() {
        return employeeRepo.getAllEmps();
    }
}
