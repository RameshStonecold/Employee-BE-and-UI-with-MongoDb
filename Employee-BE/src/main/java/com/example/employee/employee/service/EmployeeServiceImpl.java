package com.example.employee.employee.service;

import com.example.employee.employee.model.Employee;
import com.example.employee.employee.model.EmployeeList;
import com.example.employee.employee.model.EmployeeState;
import com.example.employee.employee.model.dto.EmployeeDto;
import com.example.employee.employee.model.dto.EmployeeDtoConverter;
import com.example.employee.employee.model.dto.ForgotPassword;
import com.example.employee.employee.repository.EmployeeJPARepository;
import com.example.employee.employee.repository.EmployeeRepo;
import com.example.employee.otp.model.OTPType;
import com.example.employee.otp.model.OtpModel;
import com.example.employee.otp.model.OtpState;
import com.example.employee.otp.service.IOTPService;
import com.example.employee.otp.twiliosms.SendingSms;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmpService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private IOTPService iotpService;

    private SendingSms sendingSms= new SendingSms();

    @Autowired
    private EmployeeJPARepository employeeJPARepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public Either<Exception, EmployeeDto> getById(String id) {

        Employee employee = employeeRepo.getById(id);
        if (employee.getEmployeeState()!=null){
            EmployeeDto employeeDto= EmployeeDtoConverter.getInstance().empBeanToEmpDto(employee);
            return Either.right(employeeDto);
        }
        return Either.left(new Exception("Employee not found"));
    }

    @Override
    public Either<Exception, String> createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeDtoConverter.getInstance().empDtoToempBean(employeeDto);
        String encryptedPass= encoder.encode(employee.getEmployeeState().getPassword());
        employee.getEmployeeState().setPassword(encryptedPass);

        List<Employee> oldEmployeeList = EmployeeDtoConverter.getInstance().
                empDtoListToEmpBeanList(this.findAllEmps());
      if (oldEmployeeList.isEmpty()){
          EmployeeState employeeState = employee.create(employee);
         employeeRepo.saveEmployee(employeeState);
         return Either.right(employeeState.getId());
      }
      Optional<Employee> employeeOptional = oldEmployeeList.
              stream().filter(x->x.getEmployeeState().getId().equals(employeeDto.getId())).findAny();
        if(employeeOptional.isPresent()){
            return Either.left(new Exception("Employee already registered"));
        }
        EmployeeList employeeList = new EmployeeList(oldEmployeeList);
       Either empEither= employeeList.createEmployee(employee.getEmployeeState());
       if (empEither.isRight()){
           employeeRepo.saveEmployee((EmployeeState) empEither.get());
           return Either.right(((EmployeeState) empEither.get()).getId());
       }
        return Either.left(new Exception(empEither.getLeft().toString()));
    }

    @Override
    public List<EmployeeDto> findAllEmps() {

        return employeeRepo.getAllEmps();
    }

    @Override
    public Either<Exception, String> forgotPassword(ForgotPassword forgotPassword) {


        EmployeeState employeeState = employeeJPARepository.findByMobileNo(forgotPassword.getMobileNumber());

        if (employeeState==null){
            return Either.left(new Exception("Invalid Mobile Number entered"));
        }
        OtpState otpState = new OtpState();
        OtpModel otpModel = new OtpModel();
        otpModel.setOtpState(otpState);

        OtpState otpState1 =otpModel.generateOTP();
        otpState1.setMobileNo(forgotPassword.getMobileNumber());
        otpState1.setEmailId(forgotPassword.getEmailId());
        otpState1.setOtpType(OTPType.FORGOT_PASSWORD);

        if (otpState1.getOtpType().equals(OTPType.FORGOT_PASSWORD)){
            iotpService.saveOtp(otpState1);
            try {
                sendingSms.sendSms(otpState1.getOtp(), forgotPassword.getMobileNumber());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Either.right("OTP send to your mobile number");
        }
        return Either.left(new Exception("Some thing went wrong"));

    }


}
