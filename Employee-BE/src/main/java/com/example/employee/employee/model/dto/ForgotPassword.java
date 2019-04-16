package com.example.employee.employee.model.dto;

import java.util.Objects;

public class ForgotPassword {

    private String mobileNumber;
    private String emailId;
    private String newPassword;


    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForgotPassword that = (ForgotPassword) o;
        return Objects.equals(mobileNumber, that.mobileNumber) &&
                Objects.equals(emailId, that.emailId) &&
                Objects.equals(newPassword, that.newPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobileNumber, emailId, newPassword);
    }
}
