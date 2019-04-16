package com.example.employee.otp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
public class OtpState {
    @Id
    private String otpId= UUID.randomUUID().toString();
    private String otp;
    private LocalDateTime createTime;
    private LocalDateTime validateTime;
    private OtpStatus otpStatus;
    private OTPType otpType;
    private String emailId;
    private String mobileNo;

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public OTPType getOtpType() {
        return otpType;
    }
    public void setOtpType(OTPType otpType) {
        this.otpType = otpType;
    }

    public String getOtpId() {
        return otpId;
    }
    public void setOtpId(String otpId) {
        this.otpId = otpId;
    }
    public String getOtp() {
        return otp;
    }
    public void setOtp(String otp) {
        this.otp = otp;
    }

    public OtpStatus getOtpStatus() {
        return otpStatus;
    }
    public void setOtpStatus(OtpStatus otpStatus) {
        this.otpStatus = otpStatus;
    }


    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getValidateTime() {
        return validateTime;
    }

    public void setValidateTime(LocalDateTime validateTime) {
        this.validateTime = validateTime;
    }
}
