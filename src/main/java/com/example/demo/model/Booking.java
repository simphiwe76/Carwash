package com.example.demo.model;

public class Booking
{
    private String BookingID;
    private String BookingStatus;
    private String Car;
    private String CompanyID;
    private String CustomerID;
    private String Date;
    private String Time;
    private String  WashType;
    private  String SlotID;
    private  String Location;
    public Booking() {
    }

    public Booking(String bookingID, String bookingStatus, String car,
                   String companyID, String customerID, String date, String time,
                   String washType, String slotID, String location) {
        BookingID = bookingID;
        BookingStatus = bookingStatus;
        Car = car;
        CompanyID = companyID;
        CustomerID = customerID;
        Date = date;
        Time = time;
        WashType = washType;
        SlotID = slotID;
        Location = location;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getSlotID() {
        return SlotID;
    }

    public void setSlotID(String slotID) {
        SlotID = slotID;
    }

    public String getBookingID() {
        return BookingID;
    }

    public void setBookingID(String bookingID) {
        BookingID = bookingID;
    }

    public String getBookingStatus() {
        return BookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        BookingStatus = bookingStatus;
    }

    public String getCar() {
        return Car;
    }

    public void setCar(String car) {
        Car = car;
    }

    public String getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(String companyID) {
        CompanyID = companyID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getWashType() {
        return WashType;
    }

    public void setWashType(String washType) {
        WashType = washType;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "BookingID='" + BookingID + '\'' +
                ", BookingStatus='" + BookingStatus + '\'' +
                ", Car='" + Car + '\'' +
                ", CompanyID='" + CompanyID + '\'' +
                ", CustomerID='" + CustomerID + '\'' +
                ", Date='" + Date + '\'' +
                ", Time='" + Time + '\'' +
                ", WashType='" + WashType + '\'' +
                ", SlotID='" + SlotID + '\'' +
                ", Location='" + Location + '\'' +
                '}';
    }
}
