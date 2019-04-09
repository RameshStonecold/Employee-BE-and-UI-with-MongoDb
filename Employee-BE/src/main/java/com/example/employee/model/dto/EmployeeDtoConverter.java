package com.example.employee.model.dto;

import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeState;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDtoConverter {

    private static EmployeeDtoConverter ourInstance = new EmployeeDtoConverter();
    public static EmployeeDtoConverter getInstance() {
        return ourInstance;
    }
    private EmployeeDtoConverter() {
    }


    public Employee empDtoToempBean(EmployeeDto employeeDto){
        EmployeeState employeeState = new EmployeeState();
        if (employeeDto!=null)
        {
            BeanUtils.copyProperties(employeeDto,employeeState);
        }
        return new Employee(employeeState);
    }



    public EmployeeDto empBeanToEmpDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();

        if (employee!=null){
            BeanUtils.copyProperties(employee,employeeDto);
        }
        return employeeDto;
    }


    public List<EmployeeDto> empBeanListToEmpDtoList(List<Employee> employeeList){

        List<EmployeeDto> employeeDtoList= new ArrayList<>();

        if (employeeList!=null){

            employeeList.forEach(x->employeeDtoList.add(empBeanToEmpDto(x)));
        }
        return employeeDtoList;
    }

    public List<Employee> empDtoListToEmpBeanList(List<EmployeeDto> employeeDtoList){

        List<Employee> employeeList = new ArrayList<>();

        if (employeeDtoList!=null){
            employeeDtoList.forEach(x->employeeList.add(empDtoToempBean(x)));
        }
        return employeeList;
    }

}
