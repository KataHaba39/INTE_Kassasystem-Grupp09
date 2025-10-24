package com.grupp09.kassasystem;

public class FixedTaxCategories implements TaxCategories {

    @Override
    public int categoryIdFor(ItemGroup group) {
        // 1 = 6%, 2 = 12%, 3 = 25%
        if (group == ItemGroups.KOTT || group == ItemGroups.SKALDJUR) {
            return 1; // 6%
        } else if (group == ItemGroups.FARDIGMAT || group == ItemGroups.FRUKT_GRONT ||
                group == ItemGroups.MEJERI || group == ItemGroups.BROD) {
            return 2; // 12%
        } else if (group == ItemGroups.TOBAK || group == ItemGroups.GODIS ||
                group == ItemGroups.DRYCK || group == ItemGroups.DRYCK_ALKOHOL) {
            return 3; // 25%
        } else {
            return 3; // default to 25%
        }
    }

    @Override
    public int vatBpsFor(ItemGroup group) {
        if (group == ItemGroups.KOTT || group == ItemGroups.SKALDJUR) {
            return 600; // 6%
        } else if (group == ItemGroups.FARDIGMAT || group == ItemGroups.FRUKT_GRONT
                || group == ItemGroups.MEJERI || group == ItemGroups.BROD) {
            return 1200; // 12%
        } else if (group == ItemGroups.TOBAK || group == ItemGroups.GODIS
                || group == ItemGroups.DRYCK || group == ItemGroups.DRYCK_ALKOHOL) {
            return 2500; // 25%
        } else {
            return 2500; // default to 25%
        }
    }

}
