package com.example.employee.model;

import io.vavr.control.Either;

import java.util.List;
import java.util.Optional;

public class EmployeeList  {

  private List<Employee> employeeList;

 public EmployeeList(List<Employee> employees){
     this.employeeList=employees;
 }



  public Either<Exception,EmployeeState> createEmployee(Employee employee){

     if (this.employeeList.stream().anyMatch(x->x.getEmployeeState().getEmailId()
             .equalsIgnoreCase(employee.getEmployeeState().getEmailId())))
     {
         return Either.left(new Exception("Employee Email Id already registered"));
     }

     if (this.employeeList.stream().anyMatch(x->x.getEmployeeState().getMobileNo().
             compareTo(employee.getEmployeeState().getMobileNo())==0)){
         return Either.left(new Exception("Employee Mobile Number already registered "));
     }

      Optional<Employee> employeeOptional= this.employeeList.stream().
              filter(x->x.getEmployeeState().getId().
                      compareTo(employee.getEmployeeState().getId())==0 ).findAny();
     if (!employeeOptional.isPresent())
     {
         EmployeeState employeeState =employeeOptional.get().create(employee);
         return Either.right(employeeState);
     }
      return Either.left(new Exception("Employee already exist"));

  }

}
