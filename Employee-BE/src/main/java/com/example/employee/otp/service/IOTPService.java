package com.example.employee.otp.service;



import com.example.employee.otp.model.OtpState;

import java.util.List;

public interface IOTPService {

    String saveOtp(OtpState otpState);

    List<OtpState> getAllOtps();

}
