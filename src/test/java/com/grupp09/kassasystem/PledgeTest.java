package com.grupp09.kassasystem;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PledgeTest {

    @Test
    void pledge_returnsCorrectPledge(){
        FixedPriceItem softDrinkItem = new FixedPriceItem("Soda", Money.toMoney(10.0d), ItemGroups.DRYCK);
        FixedPriceItem alcoholicBeverage = new FixedPriceItem("Beer", Money.toMoney(20.0d), ItemGroups.DRYCK_ALKOHOL);

        assertEquals(Money.toMoney(1.0d), Pledge.pledgeOf(softDrinkItem));
        assertEquals(Money.toMoney(2.0d), Pledge.pledgeOf(alcoholicBeverage));
    }

    @Test
    void pledge_addsCorrectPledge(){
        FixedPriceItem softDrinkItem = new FixedPriceItem("Soda", Money.toMoney(10.0d), ItemGroups.DRYCK);
        FixedPriceItem alcoholicBeverage = new FixedPriceItem("Beer", Money.toMoney(20.0d), ItemGroups.DRYCK_ALKOHOL);

        assertEquals(Money.toMoney(11.0d), Pledge.priceWithPledge(softDrinkItem));
        assertEquals(Money.toMoney(22.0d), Pledge.priceWithPledge(alcoholicBeverage));
    }

}
