package utils;

import enums.ServiceType;
import room.BaseRoom;

import java.util.List;

import decorator.BreakfastDecorator;
import decorator.LaundryDecorator;
import decorator.SpaDecorator;

public class RoomServiceDecoratorUtil {
    public static BaseRoom decorate(BaseRoom room, List<ServiceType> services) {
        for (ServiceType service : services) {
            switch (service) {
                case BREAKFAST:
                    room = new BreakfastDecorator(room);
                    break;
                case SPA:
                    room = new SpaDecorator(room);
                    break;
                case LAUNDRY:
                    room = new LaundryDecorator(room);
                    break;
            }
        }
        return room;
    }
} 