package com.hotelsystem.utils;

import com.hotelsystem.enums.ServiceType;
import com.hotelsystem.room.Room;
import java.util.ArrayList;
import java.util.List;

import com.hotelsystem.decorator.BreakfastDecorator;
import com.hotelsystem.decorator.LaundryDecorator;
import com.hotelsystem.decorator.SpaDecorator;

public class RoomServiceDecoratorUtil {
    public static Room decorate(Room room, List<ServiceType> services) {
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