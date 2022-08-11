package com.example.demo.model;

public class CostPrice {
    private String ID;
    private String QuotationID;
    private String Wash_Type;

    public CostPrice() {
    }

    public CostPrice(String ID, String quotationID, String wash_Type) {
        this.ID = ID;
        QuotationID = quotationID;
        Wash_Type = wash_Type;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getQuotationID() {
        return QuotationID;
    }

    public void setQuotationID(String quotationID) {
        QuotationID = quotationID;
    }

    public String getWash_Type() {
        return Wash_Type;
    }

    public void setWash_Type(String wash_Type) {
        Wash_Type = wash_Type;
    }

    @Override
    public String toString() {
        return "CostPrice{" +
                "ID='" + ID + '\'' +
                ", QuotationID='" + QuotationID + '\'' +
                ", Wash_Type='" + Wash_Type + '\'' +
                '}';
    }
}
