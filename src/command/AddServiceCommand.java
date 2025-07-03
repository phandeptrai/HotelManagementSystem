package command;

import decorator.BreakfastDecorator;
import decorator.LaundryDecorator;
import decorator.SpaDecorator;
import room.BaseRoom;

public class AddServiceCommand implements RoomTypeCommand {
    private BaseRoom room;
    private String serviceType;

    public AddServiceCommand(BaseRoom room, String serviceType) {
        this.room = room;
        this.serviceType = serviceType;
    }

    @Override
    public void execute() {
        switch (serviceType) {
            case "BREAKFAST":
                room = new BreakfastDecorator(room);
                break;
            case "LAUNDRY":
                room = new LaundryDecorator(room);
                break;
            case "SPA":
                room = new SpaDecorator(room);
                break;
            default:
                System.out.println("Dịch vụ không hợp lệ!");
        }
    }

    public BaseRoom getDecoratedRoom() {
        return room;
    }
} 