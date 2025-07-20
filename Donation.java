package com.example.healthsuppliesdonation;

public class Donation {

    private String suppliesDonated;
    private String hospitalName;

    public Donation(String suppliesDonated, String hospitalName) {
        this.suppliesDonated = suppliesDonated;
        this.hospitalName = hospitalName;
    }

    public String getSuppliesDonated() {
        return suppliesDonated;
    }

    public String getHospitalName() {
        return hospitalName;
    }
}
