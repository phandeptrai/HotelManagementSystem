package com.hotelsystem.command.room;

import com.hotelsystem.room.Room;
import com.hotelsystem.room.state.RoomState;
import com.hotelsystem.user.User;
import com.hotelsystem.observer.Observer;
import com.hotelsystem.observer.UiDisplay;


/**
 * Command for starting/finishing room maintenance
 */
public class MaintenanceCommand implements ReservationCommand {
    private Room room;
    private User user;
    private RoomState previousState;
    private boolean isStartMaintenance;
    
    public MaintenanceCommand(Room room, User user, boolean isStartMaintenance) {
        this.room = room;
        this.user = user;
        this.isStartMaintenance = isStartMaintenance;
    }
    
    @Override
    public void execute() {
        // Store previous state for undo
        previousState = room.getCurrentState();
        
                // Observer sẽ được tự động notify khi state thay đổi
        
        // Execute maintenance using State pattern
        if (isStartMaintenance) {
            room.startMaintenance();
        } else {
            room.finishMaintenance();
        }
        
        System.out.println("✅ Đã thực hiện lệnh bảo trì: " + getDescription());
    }
    
    @Override
    public void undo() {
        // Restore previous state
        if (previousState != null) {
            room.setState(previousState);
            System.out.println("🔄 Đã khôi phục trạng thái phòng về: " + previousState.getStateName());
        }
        
        System.out.println("❌ Đã hủy lệnh bảo trì: " + getDescription());
    }
    
    @Override
    public String getDescription() {
        String action = isStartMaintenance ? "Bắt đầu bảo trì" : "Kết thúc bảo trì";
        return action + " phòng " + room.getRoomNumber();
    }
    
    public Room getRoom() {
        return room;
    }
    
    @Override
    public User getUser() {
        return user;
    }
    
    @Override
    public void setUser(User user) {
        this.user = user;
    }
    
    public boolean isStartMaintenance() {
        return isStartMaintenance;
    }
} 