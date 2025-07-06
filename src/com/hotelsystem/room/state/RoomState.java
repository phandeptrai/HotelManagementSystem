package com.hotelsystem.room.state;

import com.hotelsystem.room.Room;
import com.hotelsystem.services.strategy.PaymentStrategy;
import com.hotelsystem.user.User;

/**
 * State pattern interface for room states
 */
public interface RoomState {
    void checkIn(Room room, User user);
    void checkOut(Room room, User user);
    void reserve(Room room, User user);
    void cancelReservation(Room room, User user);
    void startMaintenance(Room room);
    void finishMaintenance(Room room);
    String getStateName();
} 