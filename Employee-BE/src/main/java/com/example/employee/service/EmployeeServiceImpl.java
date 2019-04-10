package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeList;
import com.example.employee.model.EmployeeState;
import com.example.employee.model.dto.EmployeeDto;
import com.example.employee.model.dto.EmployeeDtoConverter;
import com.example.employee.repository.EmployeeRepo;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements IEmpService {

    @Autowired
    private EmployeeRepo employeeRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

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

         List<Employee> oldEmployeeList = EmployeeDtoConverter.getInstance().
              empDtoListToEmpBeanList(this.findAllEmps());
        Employee employee = EmployeeDtoConverter.getInstance().empDtoToempBean(employeeDto);

        String encryptedPass= encoder.encode(employee.getEmployeeState().getPassword());
        employee.getEmployeeState().setPassword(encryptedPass);


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
        return Either.left(new Exception("Emp already exist"));
    }

    @Override
    public List<EmployeeDto> findAllEmps() {
        return employeeRepo.getAllEmps();
    }
}
