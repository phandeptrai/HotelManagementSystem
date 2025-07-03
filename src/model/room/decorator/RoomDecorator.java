package model.room.decorator;

import enums.RoomType;
import model.room.BaseRoom;

/**
 * Abstract Decorator cho Room (Thêm loại phòng có dịch vụ)
 */
public abstract class RoomDecorator extends BaseRoom {
	protected BaseRoom decoratedRoom;

	public RoomDecorator(BaseRoom decoratedRoom) {
		super(
			decoratedRoom.getRoomNumber(),
			decoratedRoom.getRoomType(),
			decoratedRoom.getPrice(),
			decoratedRoom.isAvailable()
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
	public void bookRoom() {
		decoratedRoom.bookRoom();
	}

	@Override
	public void checkOut() {
		decoratedRoom.checkOut();
	}
}