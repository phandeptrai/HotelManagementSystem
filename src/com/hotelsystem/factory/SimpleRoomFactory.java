package com.hotelsystem.factory;

import com.hotelsystem.room.Room;
import com.hotelsystem.room.DoubleRoom;
import com.hotelsystem.room.SingleRoom;

/**
 * Factory cụ thể để tạo loại phòng đơn giản (Standard).
 */
public class SimpleRoomFactory implements AbstractRoomFactory {
	@Override
	public Room createRoom(String roomNumber) {
		if (Integer.parseInt(roomNumber) % 2 == 0) {
			return new DoubleRoom(roomNumber);
		}
		return new SingleRoom(roomNumber);
	}
}
