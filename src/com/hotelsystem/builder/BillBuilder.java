package com.hotelsystem.builder;

import com.hotelsystem.room.RoomInfo;
import java.time.LocalDateTime;

public class BillBuilder {
    private String billId;
    private RoomInfo roomInfo;
    private double totalAmount;
    private String paymentMethod;
    private boolean paid;
    private LocalDateTime paymentTime;

    public BillBuilder setBillId(String billId) {
        this.billId = billId;
        return this;
    }
    public BillBuilder setRoomInfo(RoomInfo roomInfo) {
        this.roomInfo = roomInfo;
        return this;
    }
    public BillBuilder setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }
    public BillBuilder setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }
    public BillBuilder setPaid(boolean paid) {
        this.paid = paid;
        return this;
    }
    public BillBuilder setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
        return this;
    }
    public Bill build() {
        return new Bill(this.billId, this.roomInfo, this.totalAmount, this.paymentMethod, this.paid, this.paymentTime);
    }
} 