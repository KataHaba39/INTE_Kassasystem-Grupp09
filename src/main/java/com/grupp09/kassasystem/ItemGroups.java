package com.grupp09.kassasystem;

public enum ItemGroups {
    FARDIGMAT(0), TOBAK(18), GODIS(0), DRYCK(0), FRUKT_GRONT(0), MEJERI(0), KOTT(0), SKALDJUR(0), BROD(0),
    DRYCK_ALKOHOL(18);

    private final int minimumAge;

    ItemGroups(int minimumAge) {
        this.minimumAge = validateMinimumAge(minimumAge);
    }

    public static int validateMinimumAge(int age) {
        if (age < 0) throw new IllegalArgumentException("Minimum age cannot be negative: " + age);
        return age;
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
}
