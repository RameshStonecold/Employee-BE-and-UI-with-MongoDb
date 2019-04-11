package com.example.employee.controller;

import com.example.employee.config.ResponseWithError;
import com.example.employee.model.dto.EmployeeDto;
import com.example.employee.service.IEmpService;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class EmployeeController {

    @Autowired
    private IEmpService iEmpService;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping("/getEmployee/{empId}")
    public ResponseWithError<EmployeeDto> getEmpById(@PathVariable("empId") String empId){
        Either<Exception,EmployeeDto> empEither= iEmpService.getById(empId);
        try {
            if (empEither.isRight()){
                logger.info("Employee:: getEmployeeById");
                return ResponseWithError.of(empEither.get());
            }
            logger.error("error {}");
            return ResponseWithError.ofError(empEither.getLeft().getMessage());
        }catch (Exception e) {
            logger.error("error {}", e);
            return ResponseWithError.ofError(("false"),HttpStatus.BAD_REQUEST);
        }
    }


 /*   @PostMapping("")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDto employeeDto) {
        Either<Exception,String> empEither= iEmpService.createEmployee(employeeDto);
        try {
            if (empEither.isLeft()) {
                logger.error("error {}");
               return ResponseEntity(ResponseWithError.ofError(empEither.getLeft().getMessage()));
            }
            logger.info("Employee created successfully");
            return ResponseEntity(ResponseWithError.of(empEither.get()), HttpStatus.OK);
        }catch (Exception e){

        }
    }*/


}
