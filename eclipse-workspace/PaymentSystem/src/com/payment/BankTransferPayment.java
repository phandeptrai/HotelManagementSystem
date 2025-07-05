package com.payment;

public class BankTransferPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Đã chuyển khoản " + amount + " VND thành công.");
    }
}

