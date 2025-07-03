package room;

public class RoomInfo {
    private BaseRoom room;
    private boolean paid;

    public RoomInfo(BaseRoom room, boolean paid) {
        this.room = room;
        this.paid = paid;
    }

    public BaseRoom getRoom() {
        return room;
    }

    public boolean isPaid() {
        return paid;
    }
} 