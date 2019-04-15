package com.example.employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class AddressState {

    @Id
    private String addressId;
    private String doorNo;
    private String streetName;
    private String area;
    private String district;
    private String zipcode;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressState that = (AddressState) o;
        return Objects.equals(addressId, that.addressId) &&
                Objects.equals(doorNo, that.doorNo) &&
                Objects.equals(streetName, that.streetName) &&
                Objects.equals(area, that.area) &&
                Objects.equals(district, that.district) &&
                Objects.equals(zipcode, that.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, doorNo, streetName, area, district, zipcode);
    }
}
