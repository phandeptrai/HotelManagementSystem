package com.hotelsystem.services.strategy;

public class BankTransferPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Thanh toán chuyển khoản: " + amount + " VND");
        return true;
    }
} 