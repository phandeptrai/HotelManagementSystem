package command;

import enums.RoomType;
import services.RoomTypeManager;

public class EditRoomTypeCommand implements RoomTypeCommand {
    private RoomTypeManager manager;
    private RoomType type;
    private String newName;

    public EditRoomTypeCommand(RoomTypeManager manager, RoomType type, String newName) {
        this.manager = manager;
        this.type = type;
        this.newName = newName;
    }

    @Override
    public void execute() {
        manager.editRoomTypeName(type, newName);
    }
} 