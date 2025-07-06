package com.hotelsystem.observer;

import com.hotelsystem.room.Room;
import com.hotelsystem.services.RoomManager;

/**
 * AvailableRoomListObserver theo chuẩn GoF Observer pattern
 * Cập nhật danh sách phòng trống khi trạng thái thay đổi
 */
public class UiDisplay implements Observer {

   
    @Override
    public void update(Subject subject) {
        if (subject instanceof Room) {
        	Room room = (Room) subject;
        	System.out.println("\u001B[31mPhòng " + room.getRoomNumber() + " chuyển sang trạng thái " + room.getCurrentState().getStateName() + "\u001B[0m");
        }
    }
}

