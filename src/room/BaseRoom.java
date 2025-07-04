package room;

import enums.RoomType;
import enums.RoomStatus;

/**
 * Abstract Class cơ sở cho các loại phòng cụ thể.
 */
public abstract class BaseRoom {
	protected String roomNumber;
	protected RoomType roomType;
	protected double price;
	protected RoomStatus status = RoomStatus.AVAILABLE;

	public BaseRoom(String roomNumber, RoomType roomType, double price, RoomStatus status) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.price = price;
		this.status = status;
	}

	// Getter
	public String getRoomNumber() {
		return roomNumber;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public double getPrice() {
		return price;
	}

	public String getDescription() {
		return roomType + " #" + roomNumber;
	}

	public RoomStatus getStatus() {
		return status;
	}

	public boolean isAvailable() {
		return status == RoomStatus.AVAILABLE;
	}

	// Setter
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setStatus(RoomStatus status) {
		this.status = status;
	}

	// Logic đặt phòng/trả phòng
	public void bookRoom() {
		this.status = RoomStatus.BOOKED;
	}

	public void checkOut() {
		this.status = RoomStatus.AVAILABLE;
	}

	public void setMaintenance() {
		this.status = RoomStatus.MAINTENANCE;
	}

	public abstract double getCost();
}
