package com.example.healthsuppliesdonation;

public class Request {
    private String hospitalName;
    private String suppliesNeeded;
    private String phoneNumber;
    private String email;

    public Request(String hospitalName, String suppliesNeeded, String phoneNumber, String email) {
        this.hospitalName = hospitalName;
        this.suppliesNeeded = suppliesNeeded;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getSuppliesNeeded() {
        return suppliesNeeded;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return hospitalName + " needs " + suppliesNeeded;
    }
}
