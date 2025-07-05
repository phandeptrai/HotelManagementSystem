package com.payment;

public class InvoiceManager {
    private PaymentMethod paymentMethod;

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void processPayment(Invoice invoice) {
        if (paymentMethod == null) {
            System.out.println("Chưa chọn phương thức thanh toán.");
            return;
        }

        System.out.println("Xử lý thanh toán cho hóa đơn #" + invoice.getId());
        paymentMethod.pay(invoice.getAmount());
    }
}


