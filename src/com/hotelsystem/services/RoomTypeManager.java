package com.hotelsystem.services;

import com.hotelsystem.enums.RoomType;
import java.util.*;

/**
 * Quản lý các loại phòng
 * CRUD cơ bản
 */
public class RoomTypeManager {
	
    private Set<RoomType> activeRoomTypes = new HashSet<>(Arrays.asList(RoomType.values()));    // Danh sách loại phòng đang active (được phép sử dụng)

    private Map<RoomType, String> displayNames = new HashMap<>();// Map tên hiển thị cho từng loại phòng

    public RoomTypeManager() {
        for (RoomType type : RoomType.values()) {
            displayNames.put(type, type.name());
        }
    }

    /**
     * Hiển thị danh sách loại phòng đang active
     */
    public void showRoomTypes() {
        System.out.println("Các loại phòng hiện có:");
        for (RoomType type : activeRoomTypes) {
            System.out.println("- " + displayNames.get(type));
        }
    }

    /**
     * Sửa tên hiển thị loại phòng
     */
    public void editRoomTypeName(RoomType type, String newName) {
        if (activeRoomTypes.contains(type)) {
            displayNames.put(type, newName);
        }
    }

    /**
     * Xóa loại phòng
     */
    public void removeRoomType(RoomType type) {
        activeRoomTypes.remove(type);
    }
    

    /**
     * Lấy danh sách loại phòng đang active
     */
    public Set<RoomType> getActiveRoomTypes() {
        return activeRoomTypes;
    }

    /**
     * Lấy tên hiển thị của loại phòng
     */
    public String getDisplayName(RoomType type) {
        return displayNames.get(type);
    }
} 