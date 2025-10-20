package com.grupp09.kassasystem;

public class Membership {
    private String name;
    private int id;
    private int phoneNumber;
    private String address;

    public Membership(String name, int id, int phoneNumber, String address) {
        this.name = name;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }
    
    public int getPhoneNumber() {
        return phoneNumber;
    }
}
