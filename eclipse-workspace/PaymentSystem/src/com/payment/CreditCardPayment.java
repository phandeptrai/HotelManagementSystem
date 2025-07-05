package com.payment;

public class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Đã thanh toán " + amount + " VND bằng Thẻ tín dụng.");
    }
}


