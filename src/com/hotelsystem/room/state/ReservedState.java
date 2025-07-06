package com.hotelsystem.room.state;

import com.hotelsystem.room.Room;
import com.hotelsystem.room.state.AvailableState;
import com.hotelsystem.room.state.OccupiedState;
import com.hotelsystem.user.User;

/**
 * Reserved state - room is reserved but not yet occupied
 */
public class ReservedState implements RoomState {
    
    @Override
    public void checkIn(Room room, User user) {
        if (user != null) {
            System.out.println("Phòng " + room.getRoomNumber() + " đã được check-in từ đặt trước cho: " + user.getName());
            System.out.println("Thông tin khách check-in: " + user.toString());
        } else {
            System.out.println("Phòng " + room.getRoomNumber() + " đã được check-in từ đặt trước.");
        }
        room.setState(new OccupiedState());

    }
    
    @Override
    public void checkOut(Room room, User user) {
        System.out.println("Phòng " + room.getRoomNumber() + " chỉ đặt trước, chưa check-in.");
    }
    
    @Override
    public void reserve(Room room, User user) {
        System.out.println("Phòng " + room.getRoomNumber() + " đã được đặt trước rồi.");
    }
    
    @Override
    public void cancelReservation(Room room, User user) {
        if (user != null) {
            System.out.println("Phòng " + room.getRoomNumber() + " đã hủy đặt trước bởi: " + user.getName());
        } else {
            System.out.println("Phòng " + room.getRoomNumber() + " đã hủy đặt trước.");
        }
        room.setState(new AvailableState());

    }
    
    @Override
    public void startMaintenance(Room room) {
        System.out.println("Phòng " + room.getRoomNumber() + " đã đặt trước, không thể bảo trì.");
    }
    
    @Override
    public void finishMaintenance(Room room) {
        System.out.println("Phòng " + room.getRoomNumber() + " đã đặt trước, không cần bảo trì.");
    }
    
    @Override
    public String getStateName() {
        return "Reserved";
    }
} 