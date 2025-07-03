package room;

import enums.RoomType;

public class SingleRoom extends BaseRoom {
	public SingleRoom(String roomNumber) {
		super(roomNumber, RoomType.SINGLE, 100.0, true);
	}

	@Override
	public double getCost() {
		return 500000;
	}
}
