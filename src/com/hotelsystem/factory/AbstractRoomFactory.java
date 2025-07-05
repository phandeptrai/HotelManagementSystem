package com.hotelsystem.factory;

import com.hotelsystem.room.Room;

//=================================================================================
//DESIGN PATTERN: ABSTRACT FACTORY
//=================================================================================
/**
 * Abstract Factory Pattern: Cung cấp một interface với method createRoom để tạo
 * ra các họ đối tượng phòng.
 */
public interface AbstractRoomFactory {
	Room createRoom(String roomNumber);
}
