package room;

import enums.RoomType;
import enums.RoomStatus;

public class SuiteRoom extends BaseRoom {
	public SuiteRoom(String roomNumber) {
		super(roomNumber, RoomType.SUITE, 300.0, RoomStatus.AVAILABLE);
	}
	public SuiteRoom(String roomNumber, RoomStatus status) {
		super(roomNumber, RoomType.SUITE, 300.0, status);
	}
	@Override
	public double getCost() {
		return 1500000;
	}
}
