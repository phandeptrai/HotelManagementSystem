package factory;

import enums.RoomType;
import enums.ServiceType;
import model.room.BaseRoom;
import model.room.DoubleRoom;
import model.room.SingleRoom;
import model.room.SuiteRoom;
import utils.RoomServiceDecoratorUtil;
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
	public static BaseRoom createRoom(RoomType type, String roomNumber) {
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
	public static BaseRoom createRoom(RoomType type, String roomNumber, List<ServiceType> services) {
		BaseRoom room = createRoom(type, roomNumber);
		return RoomServiceDecoratorUtil.decorate(room, services);
	}
}
