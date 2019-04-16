package com.example.employee.otp.service;


import com.example.employee.otp.model.OtpState;
import com.example.employee.otp.repository.OTPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OTPServiceImpl implements IOTPService {


    @Autowired
    private OTPRepository otpRepository;

    @Override
    public String saveOtp(OtpState otpState) {
        otpRepository.save(otpState);
        return otpState.getOtpId();
    }

    @Override
    public List<OtpState> getAllOtps() {
        return  otpRepository.findAll();
    }




}
