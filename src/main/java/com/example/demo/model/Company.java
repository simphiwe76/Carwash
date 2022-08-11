package com.example.demo.model;

public class Company
{
    private String CompanyID;
    private  String Company_Name;
    private  String Email;
    private  String Location;
    private  String Slot;
    private  String QuotationID;


    public Company() {
    }

    public Company(String companyID, String company_Name, String email, String location, String slot, String quotationID) {
        CompanyID = companyID;
        Company_Name = company_Name;
        Email = email;
        Location = location;
        Slot = slot;
        QuotationID = quotationID;
    }

    public String getQuotationID() {
        return QuotationID;
    }

    public void setQuotationID(String quotationID) {
        QuotationID = quotationID;
    }

    public String getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(String companyID) {
        CompanyID = companyID;
    }

    public String getCompany_Name() {
        return Company_Name;
    }

    public void setCompany_Name(String company_Name) {
        Company_Name = company_Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getSlot() {
        return Slot;
    }

    public void setSlot(String slot) {
        Slot = slot;
    }

    @Override
    public String toString() {
        return "Company{" +
                "CompanyID='" + CompanyID + '\'' +
                ", Company_Name='" + Company_Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Location='" + Location + '\'' +
                ", Slot='" + Slot + '\'' +
                ", QuotationID='" + QuotationID + '\'' +
                '}';
    }
}
