package com.example.employee.employee.model.dto;


import java.util.Objects;

public class EmployeeDto {
    private String id;
    private String username;
    private String password;
    private String emailId;
    private String mobileNo;
    private String age;
    private AddressDto addressDto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public AddressDto getAddressDto() { return addressDto; }

    public void setAddressDto(AddressDto addressDto) { this.addressDto = addressDto; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto that = (EmployeeDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(emailId, that.emailId) &&
                Objects.equals(mobileNo, that.mobileNo) &&
                Objects.equals(age, that.age) &&
                Objects.equals(addressDto, that.addressDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, emailId, mobileNo, age, addressDto);
    }
}
