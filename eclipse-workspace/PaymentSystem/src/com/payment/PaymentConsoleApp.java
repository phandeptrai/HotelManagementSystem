package com.payment;

import java.util.Scanner;

public class PaymentConsoleApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Invoice invoice = new Invoice("INV-001", 1500000);
        InvoiceManager manager = new InvoiceManager();

        System.out.println("=== HỆ THỐNG THANH TOÁN ===");
        System.out.println("Tổng tiền: " + invoice.getAmount() + " VND");
        System.out.println("Chọn phương thức thanh toán:");
        System.out.println("1. Thẻ tín dụng");
        System.out.println("2. Chuyển khoản ngân hàng");
        System.out.print("Nhập lựa chọn (1/2): ");

        int choice = scanner.nextInt();
        PaymentMethod method;

        switch (choice) {
            case 1 -> method = new CreditCardPayment();
            case 2 -> method = new BankTransferPayment();
            default -> {
                System.out.println("Phương thức không hợp lệ.");
                return;
            }
        }

        manager.setPaymentMethod(method);
        manager.processPayment(invoice);
        System.out.println("Cảm ơn bạn đã sử dụng dịch vụ.");
    }
}


