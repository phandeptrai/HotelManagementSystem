package com.hotelsystem.testde;

import com.hotelsystem.observer.UiDisplay;
import com.hotelsystem.room.*;
import com.hotelsystem.room.state.MaintenanceState;
import com.hotelsystem.room.state.OccupiedState;
import com.hotelsystem.services.RoomManager;
import com.hotelsystem.services.RoomMockData;

public class Demo {
	public static void main(String[] args) {
		RoomManager manager = RoomMockData.createMockRoomManager();

		 Room room101 = new SingleRoom("101");

		 UiDisplay uiDisplay = new UiDisplay();


	        room101.registerObserver(uiDisplay);
	        room101.setState(new OccupiedState());


	}
}
