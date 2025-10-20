package com.grupp09.kassasystem;

public class Membership {
    private String name;
    private int id;
    private int phoneNumber;
    private String address;

    public Membership(String name, int id, int phoneNumber, String address) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Member name must not be blank!");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("Member ID must be greater than zero!");
        }

        if (phoneNumber <= 0) {
            throw new IllegalArgumentException("Phone number must be a positive number!");
        }

        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address must not be blank!");
        }

        this.name = name.trim();
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.address = address.trim();
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
