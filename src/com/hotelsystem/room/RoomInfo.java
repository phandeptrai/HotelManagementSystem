package com.hotelsystem.room;

public class RoomInfo {
    private Room room;
    private boolean paid;

    public RoomInfo(Room room, boolean paid) {
        this.room = room;
        this.paid = paid;
    }

    public Room getRoom() {
        return room;
    }

    public boolean isPaid() {
        return paid;
    }
} 