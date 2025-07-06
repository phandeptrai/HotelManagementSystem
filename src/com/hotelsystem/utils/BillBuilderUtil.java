package com.hotelsystem.utils;

import com.hotelsystem.builder.Bill;
import com.hotelsystem.builder.BillBuilder;
import com.hotelsystem.room.RoomInfo;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Utility class để tạo Bill với Builder Pattern
 * Cung cấp các method tiện lợi để tạo Bill với các trạng thái khác nhau
 */
public class BillBuilderUtil {
    
    /**
     * Tạo Bill đã thanh toán (mặc định)
     */
    public static Bill createPaidBill(RoomInfo roomInfo, double totalAmount, String paymentMethod) {
        return new BillBuilder()
            .setBillId(UUID.randomUUID().toString())
            .setRoomInfo(roomInfo)
            .setTotalAmount(totalAmount)
            .setPaymentMethod(paymentMethod)
            .setPaid(true)
            .setPaymentTime(LocalDateTime.now())
            .build();
    }
    
    /**
     * Tạo Bill chưa thanh toán
     */
    public static Bill createUnpaidBill(RoomInfo roomInfo, double totalAmount, String paymentMethod) {
        return new BillBuilder()
            .setBillId(UUID.randomUUID().toString())
            .setRoomInfo(roomInfo)
            .setTotalAmount(totalAmount)
            .setPaymentMethod(paymentMethod)
            .setPaid(false)
            .setPaymentTime(null)
            .build();
    }
    
    /**
     * Tạo Bill với thời gian thanh toán tùy chỉnh
     */
    public static Bill createBillWithCustomTime(RoomInfo roomInfo, double totalAmount, 
                                               String paymentMethod, LocalDateTime paymentTime) {
        return new BillBuilder()
            .setBillId(UUID.randomUUID().toString())
            .setRoomInfo(roomInfo)
            .setTotalAmount(totalAmount)
            .setPaymentMethod(paymentMethod)
            .setPaid(true)
            .setPaymentTime(paymentTime)
            .build();
    }
    
    /**
     * Tạo Bill với ID tùy chỉnh
     */
    public static Bill createBillWithCustomId(String billId, RoomInfo roomInfo, 
                                             double totalAmount, String paymentMethod) {
        return new BillBuilder()
            .setBillId(billId)
            .setRoomInfo(roomInfo)
            .setTotalAmount(totalAmount)
            .setPaymentMethod(paymentMethod)
            .setPaid(true)
            .setPaymentTime(LocalDateTime.now())
            .build();
    }
    
    /**
     * Tạo Bill từ Room object
     */
    public static Bill createBillFromRoom(com.hotelsystem.room.Room room, double totalAmount, String paymentMethod) {
        RoomInfo roomInfo = new RoomInfo(room, true);
        return createPaidBill(roomInfo, totalAmount, paymentMethod);
    }
} 