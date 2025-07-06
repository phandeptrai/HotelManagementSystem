package com.hotelsystem.services;

import com.hotelsystem.room.RoomInfo;
import com.hotelsystem.builder.Bill;
import com.hotelsystem.builder.BillBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;

public class BillService {
    private final List<Bill> bills = new ArrayList<>();

    public Bill createBill(RoomInfo roomInfo, double totalAmount, String paymentMethod) {
        Bill bill = new BillBuilder()
            .setBillId(UUID.randomUUID().toString())
            .setRoomInfo(roomInfo)
            .setTotalAmount(totalAmount)
            .setPaymentMethod(paymentMethod)
            .setPaid(true)
            .setPaymentTime(LocalDateTime.now())
            .build();
        bills.add(bill);
        return bill;
    }

    public List<Bill> getAllBills() {
        return bills;
    }

    public void displayAllBills() {
        if (bills.isEmpty()) {
            System.out.println("❌ Không có hóa đơn nào!");
            return;
        }
        
        System.out.println("Danh sách hóa đơn:");
        System.out.println("ID\t\tPhòng\t\tSố tiền\t\tPhương thức\tThời gian");
        System.out.println("──\t\t─────\t\t───────\t\t───────────\t─────────");
        
        for (Bill bill : bills) {
            System.out.printf("%-8s\t%-8s\t%,.0f VND\t%-10s\t%s\n",
                            bill.getBillId().substring(0, 8),
                            bill.getRoomInfo().getRoom().getRoomNumber(),
                            bill.getTotalAmount(),
                            bill.getPaymentMethod(),
                            bill.getPaymentTime());
        }
    }

    // Có thể mở rộng thêm các phương thức tìm kiếm, xuất hóa đơn...
} 