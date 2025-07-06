package com.hotelsystem.services.strategy;

public class BankTransferPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("🏦 Thanh toán chuyển khoản: " + String.format("%,.0f", amount) + " VND");
        System.out.println("✅ Giao dịch thành công!");
        return true;
    }

    @Override
    public String getPaymentMethodName() {
        return "Chuyển khoản";
    }


} 