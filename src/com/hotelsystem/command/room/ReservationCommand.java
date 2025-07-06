package com.hotelsystem.command.room;

import com.hotelsystem.user.User;

/**
 * Command interface for room reservation operations
 */
public interface ReservationCommand {
    void execute();
    void undo();
    String getDescription();
    User getUser();
    void setUser(User user);
} 