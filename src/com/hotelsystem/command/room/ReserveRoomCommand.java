package com.hotelsystem.command.room;

import com.hotelsystem.room.Room;
import com.hotelsystem.room.state.RoomState;
import com.hotelsystem.user.User;
import com.hotelsystem.observer.Observer;
import com.hotelsystem.observer.UiDisplay;


/**
 * Command for reserving a room
 */
public class ReserveRoomCommand implements ReservationCommand {
    private Room room;
    private User user;
    private RoomState previousState;
    
    public ReserveRoomCommand(Room room, User user) {
        this.room = room;
        this.user = user;
    }
    
    @Override
    public void execute() {
        // Store previous state for undo
        previousState = room.getCurrentState();
        
        // Observer sẽ được tự động notify khi state thay đổi
        
        // Execute reservation using State pattern
        room.reserve(user);
        
        System.out.println("✅ Đã thực hiện lệnh đặt phòng: " + getDescription());
    }
    
    @Override
    public void undo() {
        // Restore previous state
        if (previousState != null) {
            room.setState(previousState);
            System.out.println("🔄 Đã khôi phục trạng thái phòng về: " + previousState.getStateName());
        }
        
        // Clear current user
        room.setCurrentUser(null);
        
        System.out.println("❌ Đã hủy lệnh đặt phòng: " + getDescription());
    }
    
    @Override
    public String getDescription() {
        return "Đặt phòng " + room.getRoomNumber() + " cho " + 
               (user != null ? user.getName() : "Khách");
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
} 