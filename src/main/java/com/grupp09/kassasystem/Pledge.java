package com.grupp09.kassasystem;

public class Pledge {

    public static Money pledgeOf(FixedPriceItem item){
        switch (item.getItemGroup()){

            case DRYCK:
                return Money.toMoney(1.0d);
            case DRYCK_ALKOHOL:
                return Money.toMoney(2.0d);
            default:
                return Money.toMoney(0.0d);
        }
    }

    public static Money priceWithPledge(FixedPriceItem item){
        return item.getPricePerUnit().add(pledgeOf(item));
    }
}
