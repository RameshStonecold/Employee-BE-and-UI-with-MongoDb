package com.example.employee.model;

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
        this.employeeState.setId(employee.getEmployeeState().getId());
        this.employeeState.setUsername(employee.getEmployeeState().getUsername());
        this.employeeState.setPassword(employee.getEmployeeState().getPassword());
        this.employeeState.setEmailId(employee.getEmployeeState().getEmailId());
        this.employeeState.setAge(employee.getEmployeeState().getAge());
        this.employeeState.setMobileNo(employee.getEmployeeState().getMobileNo());
        return this.employeeState;

    }


}
