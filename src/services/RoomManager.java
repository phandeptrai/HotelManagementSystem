package services;

import room.BaseRoom;
import enums.RoomType;
import enums.RoomStatus;
import java.util.*;
import java.util.stream.Collectors;

public class RoomManager {
    private final List<BaseRoom> rooms = new ArrayList<>();

    public void addRoom(BaseRoom room) {
        rooms.add(room);
    }

    public List<BaseRoom> getRooms() {
        return rooms;
    }

    public List<BaseRoom> getRoomsByType(RoomType type) {
        return rooms.stream()
            .filter(r -> r.getRoomType() == type)
            .collect(Collectors.toList());
    }

    public List<BaseRoom> getAvailableRooms(RoomType type) {
        return rooms.stream()
            .filter(r -> r.getRoomType() == type && r.getStatus() == RoomStatus.AVAILABLE)
            .collect(Collectors.toList());
    }

    public int countAvailableRooms(RoomType type) {
        return (int) rooms.stream()
            .filter(r -> r.getRoomType() == type && r.getStatus() == RoomStatus.AVAILABLE)
            .count();
    }

    public BaseRoom bookRoom(RoomType type) {
        for (BaseRoom room : rooms) {
            if (room.getRoomType() == type && room.getStatus() == RoomStatus.AVAILABLE) {
                room.bookRoom();
                return room;
            }
        }
        return null; // Không còn phòng trống loại này
    }
} 