package com.example.employee.model;

public class Address {

    private AddressState addressState;
    public AddressState getAddressState() {
        return addressState;
    }
    public void setAddressState(AddressState addressState) {
        this.addressState = addressState;
    }
    public Address(AddressState addressState) {
        this.addressState=addressState;
    }


    public AddressState createAddress(Address address){
        this.addressState.setAddressId(address.addressState.getAddressId());
        this.addressState.setDoorNo(address.addressState.getDoorNo());
        this.addressState.setStreetName(address.addressState.getStreetName());
        this.addressState.setArea(address.addressState.getArea());
        this.addressState.setDistrict(address.addressState.getDistrict());
        this.addressState.setZipcode(address.addressState.getZipcode());
     return this.addressState;
    }





}
