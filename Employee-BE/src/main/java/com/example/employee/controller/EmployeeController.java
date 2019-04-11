package com.example.employee.controller;

import com.example.employee.config.ResponseWithError;
import com.example.employee.model.EmployeeState;
import com.example.employee.service.IEmpService;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private IEmpService iEmpService;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    @GetMapping("/getEmployee/{empId}")
    public ResponseWithError<String> getById(@PathVariable("empId") String empId){
     Either empEither= iEmpService.getById(empId);
     if (empEither.isLeft()){
         logger.error("Exception occured by getting Employee Id");
     return ResponseWithError.ofError(empEither.left().toString());
     }
     logger.info("Employee :: empId");
     return ResponseWithError.of(empEither.get().toString());
    }

}
