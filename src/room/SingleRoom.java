package room;

import enums.RoomType;
import enums.RoomStatus;

public class SingleRoom extends BaseRoom {
	public SingleRoom(String roomNumber) {
		super(roomNumber, RoomType.SINGLE, 100.0, RoomStatus.AVAILABLE);
	}
	public SingleRoom(String roomNumber, RoomStatus status) {
		super(roomNumber, RoomType.SINGLE, 100.0, status);
	}
	@Override
	public double getCost() {
		return 500000;
	}
}
