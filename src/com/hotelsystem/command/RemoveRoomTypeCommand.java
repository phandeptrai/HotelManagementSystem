package com.hotelsystem.command;

import com.hotelsystem.enums.RoomType;
import com.hotelsystem.services.RoomTypeManager;

public class RemoveRoomTypeCommand implements RoomTypeCommand {
    private RoomTypeManager manager;
    private RoomType type;

    public RemoveRoomTypeCommand(RoomTypeManager manager, RoomType type) {
        this.manager = manager;
        this.type = type;
    }

    @Override
    public void execute() {
        manager.removeRoomType(type);
    }
} 