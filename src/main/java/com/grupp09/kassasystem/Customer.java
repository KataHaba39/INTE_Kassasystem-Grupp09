package com.grupp09.kassasystem;

public class Customer {
    private String customerId;
    private String name;
    private String phoneNumber;
    private String email;
    private Membership membership;

    public Customer(String customerId, String name, String phoneNumber, String email, Membership membership) {

        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("Customer ID can not be empty");
        }

        if (customerId.length() < 4 || customerId.length() > 8) {
            throw new IllegalArgumentException("Customer ID must be between 4 and 8 digits");
        }

        try {
            Integer.parseInt(customerId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Customer ID must contain only digits");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can not be empty");
        }

        if (name.length() > 20) {
            throw new IllegalArgumentException("Name can not be longer than 20 characters");
        }

        if (name.length() < 3 || !name.contains(" ")) {
            throw new IllegalArgumentException("Name must be at least 3 characters long or contain a space");
        }

        if (phoneNumber != null && !phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Phone number must contain only digits");
        }

        if (phoneNumber.length() != 13 || !phoneNumber.startsWith("0046")) {
            throw new IllegalArgumentException("Phone number must be 13 digits long including country code with double zero or start with 0046");
        }


        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.membership = membership;
    }

    public Customer(String customerId, String name, String phoneNumber, String email) { // phoneNumber int?
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
        if (this.email == null || this.phoneNumber == null) {
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
