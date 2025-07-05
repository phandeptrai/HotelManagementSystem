package com.hotelsystem.room.state;

import com.hotelsystem.room.Room;
import com.hotelsystem.room.state.AvailableState;

/**
 * Occupied state - room is currently occupied by guests
 */
public class OccupiedState implements RoomState {
    
    @Override
    public void checkIn(Room room) {
        System.out.println("Phòng " + room.getRoomNumber() + " đã có khách, không thể check-in.");
    }
    
    @Override
    public void checkOut(Room room) {
        System.out.println("Phòng " + room.getRoomNumber() + " đã được check-out.");
        room.setState(new AvailableState());
        room.notifyObservers();
    }
    
    @Override
    public void reserve(Room room) {
        System.out.println("Phòng " + room.getRoomNumber() + " đang có khách, không thể đặt trước.");
    }
    
    @Override
    public void cancelReservation(Room room) {
        System.out.println("Phòng " + room.getRoomNumber() + " đang có khách, không có đặt trước.");
    }
    
    @Override
    public void startMaintenance(Room room) {
        System.out.println("Phòng " + room.getRoomNumber() + " đang có khách, không thể bảo trì.");
    }
    
    @Override
    public void finishMaintenance(Room room) {
        System.out.println("Phòng " + room.getRoomNumber() + " đang có khách, không cần bảo trì.");
    }
    
    @Override
    public String getStateName() {
        return "Occupied";
    }
} 