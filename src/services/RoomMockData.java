package services;

import room.SingleRoom;
import room.DoubleRoom;
import room.SuiteRoom;
import enums.RoomStatus;

public class RoomMockData {
    public static RoomManager createMockRoomManager() {
        RoomManager roomManager = new RoomManager();
        // 2 phòng đơn trống, 1 phòng đơn đã đặt, 1 phòng đơn bảo trì
        roomManager.addRoom(new SingleRoom("S1", RoomStatus.AVAILABLE));
        roomManager.addRoom(new SingleRoom("S2", RoomStatus.BOOKED));
        roomManager.addRoom(new SingleRoom("S3", RoomStatus.AVAILABLE));
        roomManager.addRoom(new SingleRoom("S4", RoomStatus.MAINTENANCE));
        // 1 phòng đôi trống, 1 phòng đôi đã đặt
        roomManager.addRoom(new DoubleRoom("D1", RoomStatus.AVAILABLE));
        roomManager.addRoom(new DoubleRoom("D2", RoomStatus.BOOKED));
        // 1 suite trống
        roomManager.addRoom(new SuiteRoom("SU1", RoomStatus.AVAILABLE));
        return roomManager;
    }
} 