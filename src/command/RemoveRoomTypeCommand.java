package command;

import enums.RoomType;
import services.RoomTypeManager;

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