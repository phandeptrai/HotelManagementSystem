package factory;

import room.BaseRoom;
import room.DoubleRoom;
import room.SingleRoom;

/**
 * Factory cụ thể để tạo loại phòng đơn giản (Standard).
 */
public class SimpleRoomFactory implements AbstractRoomFactory {
	@Override
	public BaseRoom createRoom(String roomNumber) {
		if (Integer.parseInt(roomNumber) % 2 == 0) {
			return new DoubleRoom(roomNumber);
		}
		return new SingleRoom(roomNumber);
	}
}
