package utils;

import model.room.BaseRoom;
import enums.ServiceType;
import model.room.decorator.BreakfastDecorator;
import model.room.decorator.SpaDecorator;
import model.room.decorator.LaundryDecorator;
import java.util.List;

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