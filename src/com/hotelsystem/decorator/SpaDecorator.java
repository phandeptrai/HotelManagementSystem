package com.hotelsystem.decorator;

import com.hotelsystem.room.Room;

/**
 * Concrete Decorator: Thêm dịch vụ spa cho phòng.
 */
public class SpaDecorator extends RoomDecorator {
    public SpaDecorator(Room decoratedRoom) {
        super(decoratedRoom);
    }
    @Override
    public double getPrice() {
        return super.getPrice() + 120.0; // Thêm giá dịch vụ spa
    }
    @Override
    public String getDescription() {
        return super.getDescription() + ", kèm dịch vụ spa";
    }
    @Override
    public double getCost() {
        return super.getCost() + 100000;
    }
} 