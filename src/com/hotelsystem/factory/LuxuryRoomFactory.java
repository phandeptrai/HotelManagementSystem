package com.hotelsystem.factory;

import com.hotelsystem.room.Room;
import com.hotelsystem.room.SuiteRoom;

/**
 * Factory cụ thể để tạo loại phòng sang trọng (Luxury).
 */
public class LuxuryRoomFactory implements AbstractRoomFactory {
	@Override
	public Room createRoom(String roomNumber) {
		return new SuiteRoom(roomNumber);
	}
}
