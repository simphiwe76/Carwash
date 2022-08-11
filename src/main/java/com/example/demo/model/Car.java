package com.example.demo.model;

public class Car {
    private  String CarID;
    private  String Color;
    private  String CustomerID;
    private  String Make;
    private  String Model;
    private  String Registration;
    private  String Year;

    public Car() {
    }

    public Car(String carID, String color, String customerID,
               String make, String model, String registration,
               String year) {
        CarID = carID;
        Color = color;
        CustomerID = customerID;
        Make = make;
        Model = model;
        Registration = registration;
        Year = year;
    }

    public String getCarID() {
        return CarID;
    }

    public void setCarID(String carID) {
        CarID = carID;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getRegistration() {
        return Registration;
    }

    public void setRegistration(String registration) {
        Registration = registration;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "CarID='" + CarID + '\'' +
                ", Color='" + Color + '\'' +
                ", CustomerID='" + CustomerID + '\'' +
                ", Make='" + Make + '\'' +
                ", Model='" + Model + '\'' +
                ", Registration='" + Registration + '\'' +
                ", Year='" + Year + '\'' +
                '}';
    }
}
