package factory;

import room.BaseRoom;

//=================================================================================
//DESIGN PATTERN: ABSTRACT FACTORY
//=================================================================================
/**
 * Abstract Factory Pattern: Cung cấp một interface với method createRoom để tạo
 * ra các họ đối tượng phòng.
 */
public interface AbstractRoomFactory {
	BaseRoom createRoom(String roomNumber);
}
