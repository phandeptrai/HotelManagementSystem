package com.hotelsystem.factory;

import com.hotelsystem.enums.RoomType;
import com.hotelsystem.enums.ServiceType;
import com.hotelsystem.room.Room;
import com.hotelsystem.room.DoubleRoom;
import com.hotelsystem.room.SingleRoom;
import com.hotelsystem.room.SuiteRoom;
import com.hotelsystem.utils.RoomServiceDecoratorUtil;
import java.util.List;

//=================================================================================
//DESIGN PATTERN: FACTORY METHOD
//=================================================================================

/**
* Factory Method Pattern
* Factory tạo ra các loại Room cụ thể
* 
*/
public class RoomFactory {
	public static Room createRoom(RoomType type, String roomNumber) {
		switch(type) {
		case SINGLE:
			return new SingleRoom(roomNumber);
        case DOUBLE:
            return new DoubleRoom(roomNumber);
        case SUITE:
            return new SuiteRoom(roomNumber);
        default:
            throw new IllegalArgumentException("Loại phòng không hợp lệ: " + type);
		}
	}

	// Overload method: tạo phòng có dịch vụ
	public static Room createRoom(RoomType type, String roomNumber, List<ServiceType> services) {
		Room room = createRoom(type, roomNumber);
		return RoomServiceDecoratorUtil.decorate(room, services);
	}
}
