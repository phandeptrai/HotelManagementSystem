package command;

import model.room.BaseRoom;
import model.room.decorator.BreakfastDecorator;
import model.room.decorator.LaundryDecorator;
import model.room.decorator.SpaDecorator;

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