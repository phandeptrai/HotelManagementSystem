package room;

import enums.RoomType;

/**
 * Abstract Class cơ sở cho các loại phòng cụ thể.
 */
public abstract class BaseRoom {
	protected String roomNumber;
	protected RoomType roomType;
	protected double price;
	protected boolean available = true;

	public BaseRoom(String roomNumber, RoomType roomType, double price, boolean available) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.price = price;
		this.available = available;
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

	public boolean isAvailable() {
		return available;
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

	public void setAvailable(boolean available) {
		this.available = available;
	}

	// Logic đặt phòng/trả phòng
	public void bookRoom() {
		this.available = false;
	}

	public void checkOut() {
		this.available = true;
	}

	public abstract double getCost();
}
