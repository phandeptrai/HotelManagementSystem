package com.hotelsystem.command;

import com.hotelsystem.room.Room;

public class CancelRoomCommand implements RoomCommand {
    private Room room;
    public CancelRoomCommand(Room room) {
        this.room = room;
    }
    @Override
    public void execute() {
        
    }
} 