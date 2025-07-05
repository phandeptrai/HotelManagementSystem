package com.hotelsystem.command;

import com.hotelsystem.decorator.BreakfastDecorator;
import com.hotelsystem.decorator.LaundryDecorator;
import com.hotelsystem.decorator.SpaDecorator;
import com.hotelsystem.room.Room;

public class AddServiceCommand implements RoomTypeCommand {
    private Room room;
    private String serviceType;

    public AddServiceCommand(Room room, String serviceType) {
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

    public Room getDecoratedRoom() {
        return room;
    }
} 