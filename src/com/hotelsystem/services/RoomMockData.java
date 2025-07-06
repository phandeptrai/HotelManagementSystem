package com.hotelsystem.services;

import com.hotelsystem.factory.RoomFactory;
import com.hotelsystem.enums.RoomType;

public class RoomMockData {
    public static RoomManager createMockRoomManager() {
        RoomManager roomManager = new RoomManager();
        
        // Phòng SINGLE (101-110) - Sử dụng Factory Pattern
        for (int i = 1; i <= 7; i++) {
        	String roomNumber = "Single" + String.format("%03d", i); 
        	roomManager.addRoom(RoomFactory.createRoom(RoomType.SINGLE, roomNumber));
        }
        
        // Phòng DOUBLE (201-210) - Sử dụng Factory Pattern
        for (int i = 1; i <= 7; i++) {
        	String roomNumber = "Double" + String.format("%03d", i); 
        	roomManager.addRoom(RoomFactory.createRoom(RoomType.DOUBLE, roomNumber));
        }
        
        // Phòng SUITE (301-305) - Sử dụng Factory Pattern
        for (int i = 1; i <= 7; i++) {
        	String roomNumber = "Suite" + String.format("%03d", i); 
        	roomManager.addRoom(RoomFactory.createRoom(RoomType.SUITE, roomNumber));
        }
        
        
        return roomManager;
    }
} 