package com.hotelsystem.room.state;

import com.hotelsystem.room.Room;
import com.hotelsystem.room.state.OccupiedState;
import com.hotelsystem.room.state.ReservedState;
import com.hotelsystem.services.strategy.PaymentStrategy;
import com.hotelsystem.room.state.MaintenanceState;
import com.hotelsystem.user.User;

/**
 * Available state - room is ready for check-in or reservation
 */
public class AvailableState implements RoomState {
    
    @Override
    public void checkIn(Room room, User user) {
        if (user != null) {
            System.out.println("Phòng " + room.getRoomNumber() + " đã được check-in cho khách: " + user.getName());
            System.out.println("Thông tin khách: " + user.toString());
        } else {
            System.out.println("Phòng " + room.getRoomNumber() + " đã được check-in.");
        }
        room.setState(new OccupiedState());

    }
    public void checkIn(Room room, User user,PaymentStrategy paymentStrategy) {
    	boolean paymentSuccess = paymentStrategy.pay(room.getPrice());
        if (!paymentSuccess) {
            System.out.println("❌ Thanh toán thất bại. Không thể check-in.");
            return;
        }
        System.out.println("✅ Thanh toán thành công. Tiến hành check-in cho phòng " + room.getRoomNumber());
        checkIn(room, user);
    }
    
    @Override
    public void checkOut(Room room, User user) {
        System.out.println("Phòng " + room.getRoomNumber() + " đã trống, không thể check-out.");
    }
    
    @Override
    public void reserve(Room room, User user) {
        if (user != null) {
            System.out.println("Phòng " + room.getRoomNumber() + " đã được đặt trước bởi: " + user.getName());
            System.out.println("Thông tin đặt phòng: " + user.toString());
        } else {
            System.out.println("Phòng " + room.getRoomNumber() + " đã được đặt trước.");
        }
        room.setState(new ReservedState());

    }
    
    @Override
    public void cancelReservation(Room room, User user) {
        System.out.println("Phòng " + room.getRoomNumber() + " chưa được đặt trước.");
    }
    
    @Override
    public void startMaintenance(Room room) {
        System.out.println("Phòng " + room.getRoomNumber() + " đang bảo trì.");
        room.setState(new MaintenanceState());

    }
    
    @Override
    public void finishMaintenance(Room room) {
        System.out.println("Phòng " + room.getRoomNumber() + " đã sẵn sàng, không cần bảo trì.");
    }
    
    @Override
    public String getStateName() {
        return "Available";
    }
} 