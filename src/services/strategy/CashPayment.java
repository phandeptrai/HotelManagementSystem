package services.strategy;

public class CashPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Thanh toán tiền mặt: " + amount + " VND");
        return true;
    }
} 