package com.example.employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document
public class EmployeeState {

    @Id
    private String id= UUID.randomUUID().toString();
    private String username;
    private String password;
    private String emailId;
    private String mobileNo;
    private String age;
    private AddressState addressState;



    public String getId() { return id; }

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

    public AddressState getAddressState()
    { return addressState; }

    public void setAddressState(AddressState addressState) { this.addressState = addressState; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeState that = (EmployeeState) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(emailId, that.emailId) &&
                Objects.equals(mobileNo, that.mobileNo) &&
                Objects.equals(age, that.age) &&
                Objects.equals(addressState, that.addressState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, emailId, mobileNo, age, addressState);
    }
}
