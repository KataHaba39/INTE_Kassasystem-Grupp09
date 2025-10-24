package com.grupp09.kassasystem;

public class Pledge {

    public static Money pledgeOf(FixedPriceItem item) {
        ItemGroup group = item.getItemGroup();
        if (group == ItemGroups.DRYCK) {
            return Money.toMoney(1.00);
        } else if (group == ItemGroups.DRYCK_ALKOHOL) {
            return Money.toMoney(2.00);
        } else {
            return Money.toMoney(0.00);
        }
    }

    public static Money priceWithPledge(FixedPriceItem item) {
        return item.getPricePerUnit().add(pledgeOf(item));
    }
}
