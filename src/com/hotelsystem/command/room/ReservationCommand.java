package com.hotelsystem.command.room;

/**
 * Command interface for room reservation operations
 */
public interface ReservationCommand {
    void execute();
    void undo();
    String getDescription();
} 