package com.hotelsystem.services.strategy;

public class CreditCardPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Thanh toán qua thẻ tín dụng: " + amount + " VND");
        return true;
    }
} 