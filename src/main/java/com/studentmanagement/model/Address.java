package com.studentmanagement.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String city;
    private String state;
    private String pincode;

    //no args constructor
    public Address() {}

    public Address(String street, String city, String state, String pincode) {
        this.street = street;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
    }

    //getters and setters
    public String getStreet() { return street; }
    public void setStreet() { this.street=street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    //override how you print the address
    @Override
    public String toString() {
        return street + ", " + city + ", " + state + " - " + pincode;
    }

}
