package com.grupp09.kassasystem;

public class Customer {
    private String customerId;
    private String name;
    private String phoneNumber;
    private String email;
    private Membership membership;

    public Customer (String customerId, String name, String phoneNumber, String email) {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.membership = null;
    }

    public Customer(String customerId, String name) {
        this(customerId, name, null, null);
    }

    public Customer(String customerId, String name, String phoneNumber, String email, Membership membership) {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.membership = membership;
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

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public boolean hasMembership(Membership membership) {
        return membership != null;
    }

    @Override
    public String toString() {
        return "Customer{" + "Id='" + customerId + '\'' + 
        ", Name='" + name + '\'' +
        ", Phone='" + phoneNumber + '\'' +
        ", email='" + email + '\'' +
        ", membership=" + (membership != null ? "Yes" : "No") +
        '}';
    }
}
