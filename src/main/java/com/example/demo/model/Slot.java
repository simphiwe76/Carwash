package com.example.demo.model;

public class Slot {

    private  String ID;
    private  String SlotID;
    private  String Time;

    public Slot() {
    }

    public Slot(String ID, String slotID, String time) {
        this.ID = ID;
        SlotID = slotID;
        Time = time;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSlotID() {
        return SlotID;
    }

    public void setSlotID(String slotID) {
        SlotID = slotID;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "ID='" + ID + '\'' +
                ", SlotID='" + SlotID + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }
}
