package com.hotelsystem.room.state;

import com.hotelsystem.room.Room;

/**
 * State pattern interface for room states
 */
public interface RoomState {
    void checkIn(Room room);
    void checkOut(Room room);
    void reserve(Room room);
    void cancelReservation(Room room);
    void startMaintenance(Room room);
    void finishMaintenance(Room room);
    String getStateName();
} 