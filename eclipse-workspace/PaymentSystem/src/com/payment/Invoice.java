package com.payment;

public class Invoice {
    private final String id;
    private final double amount;

    public Invoice(String id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }
}


