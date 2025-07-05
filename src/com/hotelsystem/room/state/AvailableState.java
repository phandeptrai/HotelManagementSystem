package com.hotelsystem.room.state;

import com.hotelsystem.room.Room;
import com.hotelsystem.room.state.OccupiedState;
import com.hotelsystem.room.state.ReservedState;
import com.hotelsystem.room.state.MaintenanceState;

/**
 * Available state - room is ready for check-in or reservation
 */
public class AvailableState implements RoomState {
    
    @Override
    public void checkIn(Room room) {
        System.out.println("Phòng " + room.getRoomNumber() + " đã được check-in.");
        room.setState(new OccupiedState());
        room.notifyObservers();
    }
    
    @Override
    public void checkOut(Room room) {
        System.out.println("Phòng " + room.getRoomNumber() + " đã trống, không thể check-out.");
    }
    
    @Override
    public void reserve(Room room) {
        System.out.println("Phòng " + room.getRoomNumber() + " đã được đặt trước.");
        room.setState(new ReservedState());
        room.notifyObservers();
    }
    
    @Override
    public void cancelReservation(Room room) {
        System.out.println("Phòng " + room.getRoomNumber() + " chưa được đặt trước.");
    }
    
    @Override
    public void startMaintenance(Room room) {
        System.out.println("Phòng " + room.getRoomNumber() + " đang bảo trì.");
        room.setState(new MaintenanceState());
        room.notifyObservers();
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