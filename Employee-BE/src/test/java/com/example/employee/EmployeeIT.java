package com.example.employee;

import com.example.employee.model.dto.EmployeeDto;
import com.example.employee.service.IEmpService;
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

    @Test
    public void createEmployeeTest(){

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId("1234");
        employeeDto.setUsername("Ramesh123");
        employeeDto.setPassword("password");
        employeeDto.setEmailId("rameshshaka@gmail.com");
        employeeDto.setMobileNo("8179170688");
        employeeDto.setAge("25");

       Either either= empService.createEmployee(employeeDto);

        Assert.assertTrue(either.isRight());
    }

    @Test
    public void getEmpById(){
        String id ="";
      Assert.assertNotNull(empService.getById(id));
    }

}
