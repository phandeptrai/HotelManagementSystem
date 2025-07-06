package com.hotelsystem.services;

import com.hotelsystem.room.Room;
import com.hotelsystem.enums.RoomType;
import com.hotelsystem.enums.RoomStatus;
import java.util.*;
import java.util.stream.Collectors;

public class RoomManager {
    private final List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Room> getRoomsByType(RoomType type) {
        return rooms.stream()
            .filter(r -> r.getRoomType() == type)
            .collect(Collectors.toList());
    }

    public List<Room> getAvailableRooms(RoomType type) {
        return rooms.stream()
            .filter(r -> r.getRoomType() == type && r.isAvailable())
            .collect(Collectors.toList());
    }

    public int countAvailableRooms(RoomType type) {
        return (int) rooms.stream()
            .filter(r -> r.getRoomType() == type && r.isAvailable())
            .count();
    }
    
    // Đặt phòng theo loại
    public Room bookRoom(RoomType type) {
        List<Room> availableRooms = getAvailableRooms(type);
        if (!availableRooms.isEmpty()) {
            Room room = availableRooms.get(0);
            room.reserve(null); // Đặt phòng
            return room;
        }
        return null;
    }
    
    // Lấy phòng theo số phòng
    public Room getRoomByNumber(String roomNumber) {
        return rooms.stream()
            .filter(r -> r.getRoomNumber().equals(roomNumber))
            .findFirst()
            .orElse(null);
    }
} 