package com.example.employee;

import com.example.employee.employee.controller.EmployeeController;
import com.example.employee.employee.model.dto.EmployeeDto;
import com.example.employee.employee.model.dto.ForgotPassword;
import com.example.employee.employee.service.IEmpService;
import io.vavr.control.Either;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeIT {

    @Autowired
    private IEmpService empService;

    @Autowired
    private EmployeeController employeeController;


    @Test
    public void createEmployeeTest(){

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId("12345");
        employeeDto.setUsername("Ramesh223");
        employeeDto.setPassword("password");
        employeeDto.setEmailId("rameshshaka@gmail.com");
        employeeDto.setMobileNo("8179170688");
        employeeDto.setAge("23");

       Either either= empService.createEmployee(employeeDto);

        Assert.assertEquals(true,either.isRight());
    }

    @Test
    public void getEmpById(){
        String id ="1234";
       Either either= empService.getById(id);
      Assert.assertNotNull(either.get());
    }


    @Test
    public void updatepassword(){

        ForgotPassword forgotPassword = new ForgotPassword();
        forgotPassword.setMobileNumber("8179170688");
        forgotPassword.setNewPassword("password123");
        Either either =  empService.forgotPassword(forgotPassword);
        Assert.assertTrue(either.isRight());
    }


 /*   @Test
    public void getAllEmpsTest(){

       List<EmployeeDto> employeeDtoList= empService.findAllEmps();

       Assert.assertNotNull(employeeDtoList);
    }*/




}
