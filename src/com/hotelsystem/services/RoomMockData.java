package com.hotelsystem.services;

import com.hotelsystem.room.SingleRoom;
import com.hotelsystem.room.DoubleRoom;
import com.hotelsystem.room.SuiteRoom;
import com.hotelsystem.enums.RoomStatus;

public class RoomMockData {
    public static RoomManager createMockRoomManager() {
        RoomManager roomManager = new RoomManager();
        // 2 phòng đơn trống, 1 phòng đơn đã đặt, 1 phòng đơn bảo trì
        roomManager.addRoom(new SingleRoom("S1"));
        roomManager.addRoom(new SingleRoom("S2"));
        roomManager.addRoom(new SingleRoom("S3"));
        roomManager.addRoom(new SingleRoom("S4"));
        // 1 phòng đôi trống, 1 phòng đôi đã đặt
        roomManager.addRoom(new DoubleRoom("D1"));
        roomManager.addRoom(new DoubleRoom("D2"));
        // 1 suite trống
        roomManager.addRoom(new SuiteRoom("SU1"));
        return roomManager;
    }
} 