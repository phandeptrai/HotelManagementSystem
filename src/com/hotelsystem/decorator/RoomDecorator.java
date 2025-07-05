package com.hotelsystem.decorator;

import com.hotelsystem.enums.RoomType;
import com.hotelsystem.enums.RoomStatus;
import com.hotelsystem.room.Room;

/**
 * Abstract Decorator cho Room (Thêm loại phòng có dịch vụ)
 */
public abstract class RoomDecorator extends Room {
	protected Room decoratedRoom;

	public RoomDecorator(Room decoratedRoom) {
		super(
			decoratedRoom.getRoomNumber(),
			decoratedRoom.getPrice()
		);
		this.decoratedRoom = decoratedRoom;
	}

	@Override
	public String getRoomNumber() {
		return decoratedRoom.getRoomNumber();
	}

	@Override
	public RoomType getRoomType() {
		return decoratedRoom.getRoomType();
	}

	@Override
	public double getPrice() {
		return decoratedRoom.getPrice();
	}

	@Override
	public String getDescription() {
		return decoratedRoom.getDescription();
	}


	@Override
	public boolean isAvailable() {
		return decoratedRoom.isAvailable();
	}


	@Override
	public void checkOut() {
		decoratedRoom.checkOut();
	}

	@Override
	public double getCost() {
		return decoratedRoom.getCost();
	}
}