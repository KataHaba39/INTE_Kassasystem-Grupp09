package com.grupp09.kassasystem;

public class Customer {
    private String customerId;
    private String name;
    private String phoneNumber;
    private String email;
    private Membership membership;

    public Customer(String customerId, String name, String phoneNumber, String email, Membership membership) {
        
        if(customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("customerId kan inte tom");
        }
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("name kan inte tom");
        }
         if(phoneNumber != null && !phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("ogiltig telefon nummer");
        }
        
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.membership = membership;
    }

    public Customer (String customerId, String name, String phoneNumber, String email) { // phoneNumber int? 
        this(customerId, name, phoneNumber, email, null);
    }

    public Customer(String customerId, String name) {
        this(customerId, name, null, null, null);
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

    public boolean hasMembership() {
        return this.membership != null;
    }

    public void activateMembership(Membership membership) {
        if(this.email == null || this.phoneNumber == null) {
            throw new IllegalStateException("Kan ej aktivera medlemskap utan kontakt information");
        }
        this.membership = membership;
    }

    @Override
    public String toString() {
        return "Customer{" + 
        "Id='" + customerId + '\'' + 
        ", Name='" + name + '\'' +
        ", Phone='" + phoneNumber + '\'' +
        ", email='" + email + '\'' +
        ", membership=" + (hasMembership() ? "Yes" : "No") +
        '}';
    }
}
