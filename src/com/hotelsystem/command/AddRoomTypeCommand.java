package com.hotelsystem.command;

import com.hotelsystem.enums.RoomType;
import com.hotelsystem.services.RoomTypeManager;

public class AddRoomTypeCommand implements RoomTypeCommand {
    private RoomTypeManager manager;
    private RoomType type;

    public AddRoomTypeCommand(RoomTypeManager manager, RoomType type) {
        this.manager = manager;
        this.type = type;
    }

    @Override
    public void execute() {
        manager.addRoomType(type);
    }
}
