package main;

import java.util.Scanner;
import com.hotelsystem.room.*;
import com.hotelsystem.services.*;
import com.hotelsystem.enums.*;

public class RoomManagementUI {
    private static final Scanner scanner = new Scanner(System.in);
    private final RoomManager roomManager;
    private final BillService billService;
    private final RoomTypeManager roomTypeManager;

    public RoomManagementUI(RoomManager roomManager, BillService billService, RoomTypeManager roomTypeManager) {
        this.roomManager = roomManager;
        this.billService = billService;
        this.roomTypeManager = roomTypeManager;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║              QUẢN LÝ PHÒNG              ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║ 1. Xem danh sách tất cả phòng            ║");
            System.out.println("║ 2. Xem phòng trống                       ║");
            System.out.println("║ 3. Xem phòng theo loại                   ║");
            System.out.println("║ 4. Xem hóa đơn                           ║");
            System.out.println("║ 5. Quản lý loại phòng                    ║");
            System.out.println("║ 0. Quay lại                              ║");
            System.out.println("╚══════════════════════════════════════════╝");
            
            int choice = getIntInput("Chọn chức năng: ");
            
            switch (choice) {
                case 1:
                    displayAllRooms();
                    break;
                case 2:
                    displayAvailableRooms();
                    break;
                case 3:
                    displayRoomsByType();
                    break;
                case 4:
                    displayBills();
                    break;
                case 5:
                    roomTypeManagementMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }

    private void displayAllRooms() {
        System.out.println("\n=== DANH SÁCH TẤT CẢ PHÒNG ===");
        var rooms = roomManager.getRooms();
        if (rooms.isEmpty()) {
            System.out.println("❌ Không có phòng nào!");
            return;
        }
        
        System.out.println("Số phòng\tLoại phòng\tTrạng thái\t\tGiá (VND)");
        System.out.println("────────\t──────────\t──────────\t\t─────────");
        for (Room room : rooms) {
            String status = room.getCurrentState().getStateName();
            String statusDisplay = status.equals("Available") ? "Trống" :
                                 status.equals("Occupied") ? "Đang sử dụng" :
                                 status.equals("Reserved") ? "Đã đặt" :
                                 status.equals("Maintenance") ? "Bảo trì" : status;
            
            System.out.printf("%-8s\t%-10s\t%-16s\t%,.0f\n", 
                            room.getRoomNumber(), 
                            room.getRoomType(), 
                            statusDisplay, 
                            room.getCost());
        }
    }

    private void displayAvailableRooms() {
        System.out.println("\n=== DANH SÁCH PHÒNG TRỐNG ===");
        System.out.println("Loại phòng\tPhòng trống/Tổng số\tChi tiết");
        System.out.println("──────────\t─────────────────\t────────");
        
        for (RoomType type : RoomType.values()) {
            int available = roomManager.countAvailableRooms(type);
            int total = roomManager.countRoomsByType(type);
            System.out.printf("%-10s\t%d/%d\t\t\t", type, available, total);
            
            if (available > 0) {
                var availableRooms = roomManager.getAvailableRooms(type);
                System.out.print("Phòng: ");
                for (int i = 0; i < Math.min(availableRooms.size(), 5); i++) {
                    System.out.print(availableRooms.get(i).getRoomNumber());
                    if (i < Math.min(availableRooms.size(), 5) - 1) System.out.print(", ");
                }
                if (availableRooms.size() > 5) System.out.print("...");
            } else {
                System.out.print("Không có phòng trống");
            }
            System.out.println();
        }
    }

    private void displayRoomsByType() {
        System.out.println("\n=== XEM PHÒNG THEO LOẠI ===");
        System.out.println("1. SINGLE");
        System.out.println("2. DOUBLE");
        System.out.println("3. SUITE");
        
        int choice = getIntInput("Chọn loại phòng: ");
        RoomType selectedType = null;
        
        switch (choice) {
            case 1: selectedType = RoomType.SINGLE; break;
            case 2: selectedType = RoomType.DOUBLE; break;
            case 3: selectedType = RoomType.SUITE; break;
            default:
                System.out.println("❌ Lựa chọn không hợp lệ!");
                return;
        }
        
        var rooms = roomManager.getRoomsByType(selectedType);
        if (rooms.isEmpty()) {
            System.out.println("❌ Không có phòng loại " + selectedType);
            return;
        }
        
        System.out.println("\nDanh sách phòng " + selectedType + ":");
        System.out.println("Số phòng\tTrạng thái\t\tGiá (VND)");
        System.out.println("────────\t──────────\t\t─────────");
        for (Room room : rooms) {
            String status = room.getCurrentState().getStateName();
            String statusDisplay = status.equals("Available") ? "Trống" :
                                 status.equals("Occupied") ? "Đang sử dụng" :
                                 status.equals("Reserved") ? "Đã đặt" :
                                 status.equals("Maintenance") ? "Bảo trì" : status;
            
            System.out.printf("%-8s\t%-16s\t%,.0f\n", 
                            room.getRoomNumber(), 
                            statusDisplay, 
                            room.getCost());
        }
    }

    private void displayBills() {
        System.out.println("\n=== DANH SÁCH HÓA ĐƠN ===");
        billService.displayAllBills();
    }

    private void roomTypeManagementMenu() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║           QUẢN LÝ LOẠI PHÒNG            ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║ 1. Xem danh sách loại phòng             ║");
            System.out.println("║ 2. Sửa tên loại phòng                   ║");
            System.out.println("║ 3. Thêm loại phòng mới                  ║");
            System.out.println("║ 4. Xóa loại phòng                       ║");
            System.out.println("║ 0. Quay lại                             ║");
            System.out.println("╚══════════════════════════════════════════╝");
            
            int choice = getIntInput("Chọn chức năng: ");
            
            switch (choice) {
                case 1:
                    roomTypeManager.displayAllRoomTypes();
                    break;
                case 2:
                    editRoomTypeName();
                    break;
                case 3:
                    addRoomType();
                    break;
                case 4:
                    removeRoomType();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }

    private void editRoomTypeName() {
        System.out.println("\n=== SỬA TÊN LOẠI PHÒNG ===");
        roomTypeManager.displayAllRoomTypes();
        
        int id = getIntInput("Nhập ID loại phòng cần sửa: ");
        System.out.print("Nhập tên mới: ");
        String newName = scanner.nextLine();
        
        roomTypeManager.updateRoomTypeName(id, newName);
    }

    private void addRoomType() {
        System.out.println("\n=== THÊM LOẠI PHÒNG MỚI ===");
        System.out.println("Chức năng này sẽ được mở rộng trong tương lai!");
    }

    private void removeRoomType() {
        System.out.println("\n=== XÓA LOẠI PHÒNG ===");
        roomTypeManager.displayAllRoomTypes();
        
        int id = getIntInput("Nhập ID loại phòng cần xóa: ");
        roomTypeManager.removeRoomType(id);
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Vui lòng nhập số!");
            }
        }
    }
} 