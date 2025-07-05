package com.hotelsystem.room;

import com.hotelsystem.enums.RoomType;


public class SuiteRoom extends Room {
	public SuiteRoom(String roomNumber) {
		super(roomNumber, 300.0);
	}

	@Override
	public double getCost() {
		return 1500000;
	}

	@Override
	public RoomType getRoomType() {

		return RoomType.SUITE;
	}
	@Override
	public String toString() {
		return super.toString();
	}
}
