package decorator;

import room.BaseRoom;

/**
 * Concrete Decorator: Thêm dịch vụ spa cho phòng.
 */
public class SpaDecorator extends RoomDecorator {
    public SpaDecorator(BaseRoom decoratedRoom) {
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