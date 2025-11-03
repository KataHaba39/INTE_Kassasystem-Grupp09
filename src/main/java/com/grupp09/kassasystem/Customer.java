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

        String nameWithoutBlankSpace = name.replace(" ", "");
        char[] str = nameWithoutBlankSpace.toCharArray();
        for (char c: str) {
            if (!Character.isLetter(c)) {
                throw new IllegalArgumentException("Name must only contain letters");
            }
        }

        if (phoneNumber != null && !phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Phone number must contain only digits");
        }

        if (phoneNumber.length() != 13 || !phoneNumber.startsWith("0046")) {
            throw new IllegalArgumentException(
                    "Phone number must be 13 digits long and start with country code '0046'");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email must contain '@'");
        }

        String[] emailParts = email.split("@");

        if (emailParts.length != 2 || emailParts[0].isBlank() || emailParts[1].isBlank()) {
            throw new IllegalArgumentException("Email must have a valid format before and after '@'");
        }

        String beforeAt = emailParts[0];
        String afterAt = emailParts[1];

        if (beforeAt.length() > 15) {
            throw new IllegalArgumentException("Email part before '@' must be between 1 and 15 characters long");
        }

        if (afterAt.length() < 5 || afterAt.length() > 20) {
            throw new IllegalArgumentException("Email part after '@' must be between 5 and 20 characters long");
        }

        if (!afterAt.endsWith(".com")) {
            throw new IllegalArgumentException("Email must end with '.com'");
        }

        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.membership = membership;
    }

    public Customer(String customerId, String name, String phoneNumber, String email) {
        this(customerId, name, phoneNumber, email, null);
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
