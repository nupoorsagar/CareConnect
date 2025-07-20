package com.example.healthsuppliesdonation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataModel {

    private static DataModel instance;

    private final List<Request> requests;
    private final List<Donation> donations;

    private DataModel() {
        requests = new ArrayList<>();
        donations = new ArrayList<>();
    }

    public static DataModel getInstance() {
        if (instance == null) {
            instance = new DataModel();
        }
        return instance;
    }

    // Requests management
    public void addRequest(Request request) {
        requests.add(request);
    }

    public void removeRequest(Request request) {
        requests.remove(request);
    }

    public List<Request> getRequests() {
        return new ArrayList<>(requests);
    }

    // Donations management
    public void addDonation(Donation donation) {
        donations.add(donation);
    }

    public List<Donation> getDonations() {
        return new ArrayList<>(donations);
    }

    // Fetch all unique supplies requested
    public List<String> getAllSuppliesRequested() {
        Set<String> suppliesSet = new HashSet<>();
        for (Request request : requests) {
            suppliesSet.add(request.getSuppliesNeeded());
        }
        return new ArrayList<>(suppliesSet);
    }

    // Fetch all unique organizations that requested supplies
    public List<String> getAllOrganizationsRequested() {
        Set<String> organizationsSet = new HashSet<>();
        for (Request request : requests) {
            organizationsSet.add(request.getHospitalName());
        }
        return new ArrayList<>(organizationsSet);
    }
}
