package com.example.employee.employee.model.dto;

import com.example.employee.employee.model.AddressState;
import com.example.employee.employee.model.Employee;
import com.example.employee.employee.model.EmployeeState;
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
        if (employeeDto!=null) {
            BeanUtils.copyProperties(employeeDto,employeeState);
        }
        AddressState addressState = new AddressState();
        if (employeeDto.getAddressDto()!=null){
            BeanUtils.copyProperties(employeeDto.getAddressDto(),addressState );
           employeeState.setAddressState(addressState);
        }

        return new Employee(employeeState);
    }



    public EmployeeDto empBeanToEmpDto(Employee employee){

        EmployeeDto employeeDto = new EmployeeDto();

        if (employee.getEmployeeState()!=null){
            BeanUtils.copyProperties(employee.getEmployeeState(),employeeDto);
        }
        AddressDto addressDto = new AddressDto();
        if (employee.getEmployeeState().getAddressState()!=null){
            BeanUtils.copyProperties(employee.getEmployeeState().getAddressState(),addressDto);
            employeeDto.setAddressDto(addressDto);
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
