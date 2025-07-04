package decorator;

import enums.RoomType;
import enums.RoomStatus;
import room.BaseRoom;

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
			decoratedRoom.getStatus()
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
	public RoomStatus getStatus() {
		return decoratedRoom.getStatus();
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

	@Override
	public double getCost() {
		return decoratedRoom.getCost();
	}
}