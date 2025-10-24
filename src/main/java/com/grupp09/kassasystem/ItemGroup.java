package com.grupp09.kassasystem;

public class ItemGroup {
    private final String name;
    private final int minimumAge;

    public ItemGroup(String name, int minimumAge) {
        this.name = name;
        this.minimumAge = validateMinimumAge(minimumAge);
    }

    public static int validateMinimumAge(int age) {
        if (age < 0) throw new IllegalArgumentException("Minimum age cannot be negative: " + age);
        return age;
    }

    public String getName() {
        return name;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public boolean isAgeRestricted() {
        return minimumAge > 0;
    }

    public boolean isAllowedFor(int age) {
        return age >= minimumAge;
    }

    @Override
    public String toString() {
        return name + " (min age: " + minimumAge + ")";
    }
}
