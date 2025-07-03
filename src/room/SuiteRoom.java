package room;

import enums.RoomType;

public class SuiteRoom extends BaseRoom {
	public SuiteRoom(String roomNumber) {
		super(roomNumber, RoomType.SUITE, 300.0, true);
	}

	@Override
	public double getCost() {
		return 1500000;
	}
}
