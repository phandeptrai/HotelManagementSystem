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

//    public Room bookRoom(Room room) {
//        for (Room room : rooms) {
//            if (room.getRoomType() == type && room.getStatus() == RoomStatus.AVAILABLE) {
//                room.bookRoom();
//                return room;
//            }
//        }
//        return null; // Không còn phòng trống loại này
//    }
} 