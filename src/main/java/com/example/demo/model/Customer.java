package com.example.demo.model;


public class Customer {

    private String CustomerID;
    private  String Email;
    private  String Gender;
    private  String Name;
    private  String Password;
    private  String Surname;


    public Customer() {
    }

    public Customer(String customerID, String email, String gender, String name, String password, String surname) {
        CustomerID = customerID;
        Email = email;
        Gender = gender;
        Name = name;
        Password = password;
        Surname = surname;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "CustomerID='" + CustomerID + '\'' +
                ", Email='" + Email + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Name='" + Name + '\'' +
                ", Password='" + Password + '\'' +
                ", Surname='" + Surname + '\'' +
                '}';
    }
}
