package com.hotelsystem.services;

import com.hotelsystem.room.SingleRoom;
import com.hotelsystem.room.DoubleRoom;
import com.hotelsystem.room.SuiteRoom;
import com.hotelsystem.room.Room;
import com.hotelsystem.enums.RoomStatus;
import java.util.List;

public class RoomMockData {
    public static RoomManager createMockRoomManager() {
        RoomManager roomManager = new RoomManager();
        
        // Phòng SINGLE (101-110)
        for (int i = 1; i <= 7; i++) {
        	String roomNumber = "Single" + String.format("%03d", i); 
        	roomManager.addRoom(new SingleRoom(roomNumber));
        }
        
        // Phòng DOUBLE (201-210)
        for (int i = 1; i <= 7; i++) {
        	String roomNumber = "Double" + String.format("%03d", i); 
        	roomManager.addRoom(new DoubleRoom(roomNumber));
        }
        
        // Phòng SUITE (301-305)
        for (int i = 1; i <= 7; i++) {
        	String roomNumber = "Suite" + String.format("%03d", i); 
        	roomManager.addRoom(new SuiteRoom(roomNumber));
        }
        
        
        return roomManager;
    }
} 