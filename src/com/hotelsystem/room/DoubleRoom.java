package com.hotelsystem.room;

import com.hotelsystem.enums.RoomType;
import com.hotelsystem.enums.RoomStatus;

public class DoubleRoom extends Room {
	public DoubleRoom(String roomNumber) {
		super(roomNumber, 250.0);
	}

	@Override
	public double getCost() {
		return 800000;
	}

	@Override
	public RoomType getRoomType() {
		return RoomType.DOUBLE;
	}
}
