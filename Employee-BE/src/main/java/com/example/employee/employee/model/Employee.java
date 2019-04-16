package com.example.employee.employee.model;

public class Employee {

    private EmployeeState employeeState;

    public EmployeeState getEmployeeState() {
        return employeeState;
    }

    public void setEmployeeState(EmployeeState employeeState) {
        this.employeeState = employeeState;
    }

    public Employee(EmployeeState employeeState){
        this.employeeState=employeeState;
    }

    public EmployeeState create(Employee employee){
        this.employeeState.setId(employee.employeeState.getId());
        this.employeeState.setUsername(employee.employeeState.getUsername());
        this.employeeState.setPassword(employee.employeeState.getPassword());
        this.employeeState.setEmailId(employee.employeeState.getEmailId());
        this.employeeState.setAge(employee.employeeState.getAge());
        this.employeeState.setMobileNo(employee.employeeState.getMobileNo());
        this.employeeState.setAddressState(employee.getEmployeeState().getAddressState());
        return this.employeeState;
    }


}
