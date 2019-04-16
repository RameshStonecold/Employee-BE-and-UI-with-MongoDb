package com.example.employee.employee.controller;

import com.example.employee.employee.config.ResponseWithError;
import com.example.employee.employee.model.dto.EmployeeDto;
import com.example.employee.employee.service.IEmpService;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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


    @PostMapping("/createEmployee")
    public ResponseEntity createEmployee(@RequestBody EmployeeDto employeeDto) {
        Either<Exception,String> empEither= iEmpService.createEmployee(employeeDto);
        try {
            if (empEither.isLeft()) {
                logger.error("error {}");
               return new ResponseEntity(ResponseWithError.ofError(empEither.getLeft().getMessage()), HttpStatus.BAD_REQUEST);
            }
            logger.info("Employee created successfully");
            return new ResponseEntity(ResponseWithError.of(empEither.get()), HttpStatus.OK);
        }catch (Exception e){

            logger.error("error {}", e);
            return new ResponseEntity(ResponseWithError.ofError(("false")),HttpStatus.BAD_REQUEST);
        }

        }


        @GetMapping("/allEmployees")
    public ResponseEntity getAllEmployees() {
         List<EmployeeDto> allEmps = iEmpService.findAllEmps();
            try {
                if (!allEmps.isEmpty()) {
                    logger.info("Employee:: getAllEmployees");
                    return new ResponseEntity(ResponseWithError.of(allEmps), HttpStatus.OK);
                }
                logger.error("error {}");
                return new ResponseEntity(ResponseWithError.ofError("Employee not yet registered"), HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.error("error {}", e);
                return new ResponseEntity(ResponseWithError.ofError(("false")),HttpStatus.BAD_REQUEST);
            }
        }


}
