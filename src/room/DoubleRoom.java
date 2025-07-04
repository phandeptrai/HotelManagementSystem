package room;

import enums.RoomType;
import enums.RoomStatus;

public class DoubleRoom extends BaseRoom {
	public DoubleRoom(String roomNumber) {
		super(roomNumber, RoomType.DOUBLE, 250.0, RoomStatus.AVAILABLE);
	}
	public DoubleRoom(String roomNumber, RoomStatus status) {
		super(roomNumber, RoomType.DOUBLE, 250.0, status);
	}
	@Override
	public double getCost() {
		return 800000;
	}
}
