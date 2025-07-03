package factory;

import model.room.BaseRoom;
import model.room.SuiteRoom;

/**
 * Factory cụ thể để tạo loại phòng sang trọng (Luxury).
 */
public class LuxuryRoomFactory implements AbstractRoomFactory {
	@Override
	public BaseRoom createRoom(String roomNumber) {
		return new SuiteRoom(roomNumber);
	}
}
