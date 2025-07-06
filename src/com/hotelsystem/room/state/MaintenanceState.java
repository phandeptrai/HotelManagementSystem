package com.hotelsystem.room.state;

import com.hotelsystem.room.Room;
import com.hotelsystem.room.state.AvailableState;
import com.hotelsystem.user.User;

/**
 * Maintenance state - room is under maintenance
 */
public class MaintenanceState implements RoomState {
    
    @Override
    public void checkIn(Room room, User user) {
        System.out.println("Phòng " + room.getRoomNumber() + " đang bảo trì, không thể check-in.");
    }
    
    @Override
    public void checkOut(Room room, User user) {
        System.out.println("Phòng " + room.getRoomNumber() + " đang bảo trì, không có khách.");
    }
    
    @Override
    public void reserve(Room room, User user) {
        System.out.println("Phòng " + room.getRoomNumber() + " đang bảo trì, không thể đặt trước.");
    }
    
    @Override
    public void cancelReservation(Room room, User user) {
        System.out.println("Phòng " + room.getRoomNumber() + " đang bảo trì, không có đặt trước.");
    }
    
    @Override
    public void startMaintenance(Room room) {
        System.out.println("Phòng " + room.getRoomNumber() + " đang bảo trì rồi.");
    }
    
    @Override
    public void finishMaintenance(Room room) {
        System.out.println("Phòng " + room.getRoomNumber() + " đã hoàn thành bảo trì.");
        room.setState(new AvailableState());

    }
    
    @Override
    public String getStateName() {
        return "Maintenance";
    }
} 