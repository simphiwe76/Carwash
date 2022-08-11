package com.example.demo.model;


public class Location {
    private  String companyID;
    private  String address;

    public Location() {
    }

    public Location(String companyID, String address) {
        this.companyID = companyID;
        this.address = address;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Location{" +
                "companyID='" + companyID + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
