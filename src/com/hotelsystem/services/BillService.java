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

    // Có thể mở rộng thêm các phương thức tìm kiếm, xuất hóa đơn...
} 