package com.brainmatics.pos.core.Employee;

public class Address {
    private final String street;
    private final String zipCode;
    private final String country;


    public Address(String street, String zipCode, String country) {
        this.street = street;
        this.zipCode = zipCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }
}
