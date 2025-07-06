package com.hotelsystem.factory;

import com.hotelsystem.room.Room;
import com.hotelsystem.room.DoubleRoom;
import com.hotelsystem.room.SingleRoom;

//=================================================================================
//DESIGN PATTERN: ABSTRACT FACTORY
//=================================================================================
/**
 * Factory cụ thể để tạo loại phòng đơn giản (Standard).
 */
public class SimpleRoomFactory implements AbstractRoomFactory {
	
	/**
	 * Tạo phòng theo quy tắc: số phòng chẵn = phòng đôi, số lẻ = phòng đơn
	 * 
	 * @param roomNumber Số phòng (dạng string)
	 * @return Room object được tạo theo quy tắc
	 */
	@Override
	public Room createRoom(String roomNumber) {
		// Logic tạo phòng: số chẵn = DoubleRoom, số lẻ = SingleRoom
		if (Integer.parseInt(roomNumber) % 2 == 0) {
			return new DoubleRoom(roomNumber);
		}
		return new SingleRoom(roomNumber);
	}
}
