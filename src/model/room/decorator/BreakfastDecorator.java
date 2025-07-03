package model.room.decorator;

import model.room.BaseRoom;

/**
 * Concrete Decorator: Thêm dịch vụ bữa sáng cho phòng.
 */
public class BreakfastDecorator extends RoomDecorator {
    public BreakfastDecorator(BaseRoom decoratedRoom) {
        super(decoratedRoom);
    }
    @Override
    public double getPrice() {
        return super.getPrice() + 50.0; // Thêm giá bữa sáng
    }
    @Override
    public String getDescription() {
        return super.getDescription() + ", kèm bữa sáng";
    }
} 