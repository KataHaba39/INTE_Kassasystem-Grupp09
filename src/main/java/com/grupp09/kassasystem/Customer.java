package com.grupp09.kassasystem;

public class Customer {
    private String customerId;
    private String name;
    private String phoneNumber;
    private String email;

    public Customer (String customerId, String name, String phoneNumber, String email) {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Customer(String customerId, String name) {
        this(customerId, name, null, null);
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer{" + "Id='" + customerId + '\'' + 
        ", Name='" + name + '\'' +
        ", Phone='" + phoneNumber + '\'' +
        ", email='" + email + '\'' +
        '}';
    }
}
