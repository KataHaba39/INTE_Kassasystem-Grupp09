package com.grupp09.kassasystem;

public interface Payment {
    Money getTotalAmount();  
    Money getAmountPaid();        
    boolean isPaidEnough();         // true if amountPaid >= totalAmount
    Money getChange();              // bara för kontanbetalning, null annars
    String getMethod();
}