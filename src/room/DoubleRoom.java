package room;

import enums.RoomType;

public class DoubleRoom extends BaseRoom {
	public DoubleRoom(String roomNumber) {
		super(roomNumber, RoomType.DOUBLE, 250.0, true);
	}

	@Override
	public double getCost() {
		return 800000;
	}
}
