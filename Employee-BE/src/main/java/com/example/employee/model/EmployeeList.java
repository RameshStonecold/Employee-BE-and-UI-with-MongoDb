package com.example.employee.model;

import io.vavr.control.Either;

import java.util.List;
import java.util.Optional;

public class EmployeeList  {

  private List<Employee> employeeList;

 public EmployeeList(List<Employee> employees){
     this.employeeList=employees;
 }



  public Either<Exception,EmployeeState> createEmployee(EmployeeState employeeState){

     if (this.employeeList.stream().anyMatch(x->x.getEmployeeState().getEmailId()
             .equalsIgnoreCase(employeeState.getEmailId())))
     {
         return Either.left(new Exception("Employee Email Id already registered"));
     }

     if (this.employeeList.stream().anyMatch(x->x.getEmployeeState().getMobileNo().
             compareTo(employeeState.getMobileNo())==0)){
         return Either.left(new Exception("Employee Mobile Number already registered "));
     }

      Optional<Employee> employeeOptional= this.employeeList.stream().
              filter(x->x.getEmployeeState().getId().
                      compareTo(employeeState.getId())==0 ).findAny();
     if (!employeeOptional.isPresent())
     {
         EmployeeState employeeState1 =employeeOptional.get().create(employeeOptional.get());
         return Either.right(employeeState1);
     }
      return Either.left(new Exception("Employee already exist"));

  }

}
