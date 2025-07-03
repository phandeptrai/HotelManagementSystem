package decorator;

import room.BaseRoom;

/**
 * Concrete Decorator: Thêm dịch vụ giặt là cho phòng.
 */
public class LaundryDecorator extends RoomDecorator {
    public LaundryDecorator(BaseRoom decoratedRoom) {
        super(decoratedRoom);
    }
    @Override
    public double getPrice() {
        return super.getPrice() + 30.0; // Thêm giá dịch vụ giặt là
    }
    @Override
    public String getDescription() {
        return super.getDescription() + ", kèm dịch vụ giặt là";
    }
    @Override
    public double getCost() {
        return super.getCost() + 30000;
    }
} 