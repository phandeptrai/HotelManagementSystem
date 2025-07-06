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

	    public void addRoomType(RoomType type) {
	        activeRoomTypes.add(type);
	        if (!displayNames.containsKey(type)) {
	            displayNames.put(type, type.name());
	        }
	    
	}
    
    /**
     * Hiển thị tất cả loại phòng
     */
    public void displayAllRoomTypes() {
        System.out.println("\n=== DANH SÁCH LOẠI PHÒNG ===");
        if (activeRoomTypes.isEmpty()) {
            System.out.println("❌ Không có loại phòng nào!");
            return;
        }
        
        System.out.println("ID\tLoại phòng\t\tTên hiển thị");
        System.out.println("──\t──────────\t\t───────────");
        int id = 1;
        for (RoomType type : activeRoomTypes) {
            System.out.printf("%-2d\t%-10s\t\t%s\n", 
                            id++, 
                            type.name(), 
                            displayNames.get(type));
        }
    }
    
    /**
     * Cập nhật tên hiển thị của loại phòng theo ID
     */
    public void updateRoomTypeName(int id, String newName) {
        RoomType[] types = activeRoomTypes.toArray(new RoomType[0]);
        if (id > 0 && id <= types.length) {
            RoomType type = types[id - 1];
            displayNames.put(type, newName);
            System.out.println("✅ Đã cập nhật tên hiển thị cho " + type.name() + " thành: " + newName);
        } else {
            System.out.println("❌ ID không hợp lệ!");
        }
    }
    
    /**
     * Xóa loại phòng theo ID
     */
    public void removeRoomType(int id) {
        RoomType[] types = activeRoomTypes.toArray(new RoomType[0]);
        if (id > 0 && id <= types.length) {
            RoomType type = types[id - 1];
            activeRoomTypes.remove(type);
            System.out.println("✅ Đã xóa loại phòng: " + type.name());
        } else {
            System.out.println("❌ ID không hợp lệ!");
        }
    }
} 