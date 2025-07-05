package com.hotelsystem.room;

import com.hotelsystem.enums.RoomType;
import com.hotelsystem.enums.RoomStatus;

public class SingleRoom extends Room {
	
	public SingleRoom(String roomNumber) {
		super(roomNumber, 100.0);
	}

	@Override
	public double getCost() {
		return 500000;
	}
	
	@Override
	public RoomType getRoomType() {
	    return RoomType.SINGLE;
	}

	
	@Override
	public String toString() {
		return super.toString() + "Giá: " + getCost();
	}

}

