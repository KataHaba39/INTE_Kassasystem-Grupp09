package com.grupp09.kassasystem;

public class FixedTaxCategories implements TaxCategories {
   
    @Override
    public int categoryIdFor(ItemGroups group) {
        // 1 = 6%, 2 = 12%, 3 = 25%
        switch (group) {
            case KOTT, SKALDJUR -> { return 1; } //6%
            case FARDIGMAT, FRUKT_GRONT, MEJERI, BROD -> {return 2; } //12%
            case TOBAK, GODIS, DRYCK, DRYCK_ALKOHOL -> { return 3; }  //25%
            default -> { return 3; } //default to 25%
        }
    }

    @Override
    public int vatBpsFor(ItemGroups group) {
        switch (group) {
            case KOTT, SKALDJUR -> { return 600; } //6%
            case FARDIGMAT, FRUKT_GRONT, MEJERI, BROD -> {return 1200; } //12%
            case TOBAK, GODIS, DRYCK, DRYCK_ALKOHOL -> { return 2500; }  //25%
            default -> { return 2500; } // default to 25%
        }
    }
    
}
