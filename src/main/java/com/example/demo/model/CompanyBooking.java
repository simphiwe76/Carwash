package com.example.demo.model;

public class CompanyBooking {
    private  String companyID;

    public CompanyBooking() {
    }
    public CompanyBooking(String companyID) {
        this.companyID = companyID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    @Override
    public String toString() {
        return "CompanyBooking{" +
                "companyID='" + companyID + '\'' +
                '}';
    }
}
