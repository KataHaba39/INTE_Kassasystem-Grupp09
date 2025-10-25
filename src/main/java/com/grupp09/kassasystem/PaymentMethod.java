package com.grupp09.kassasystem;

public class PaymentMethod implements Payment {
    private final String method;
    private final Money totalAmount;
    private final Money amountPaid;

    public PaymentMethod(String paymentMethod, Money totalAmount, Money amountPaid) {
        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new IllegalArgumentException("Payment method cannot be null or empty");
        }

        if (totalAmount == null || amountPaid == null) {
            throw new IllegalArgumentException("Amounts cannot be null");
        }

        String normalized = paymentMethod.trim().toLowerCase();
        if (!normalized.equals("cash") && !normalized.equals("card") && !normalized.equals("swish")) {
            throw new IllegalArgumentException("Unsupported payment method: " + normalized);
        }

        this.method = normalized;
        this.totalAmount = totalAmount;
        this.amountPaid = amountPaid;
    }

    @Override
    public Money getTotalAmount() {
        return totalAmount;
    }

    @Override
    public Money getAmountPaid() {
        return amountPaid;
    }

    // Vi behövde compareTo i Money-klassen för att detta ska fungera
    public boolean isPaidEnough() {
        return amountPaid.compareTo(totalAmount) >= 0;
    }

    @Override
    public Money getChange() {
        if (!method.equals("cash"))
            return Money.toMoney(0.0);
        if (!isPaidEnough())
            throw new IllegalStateException("Insufficient cash payment");
        return amountPaid.subtract(totalAmount);
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return String.format("Payment[%s, total=%s, paid=%s, change=%s]",
                method, totalAmount, amountPaid, getChange());
    }

}
