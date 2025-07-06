package main;

import java.util.Scanner;
import com.hotelsystem.user.*;
import com.hotelsystem.room.*;
import com.hotelsystem.services.*;

public class ReportUI {
    private static final Scanner scanner = new Scanner(System.in);
    private final UserManager userManager;
    private final RoomManager roomManager;
    private final BookingManager bookingManager;
    private final BillService billService;

    public ReportUI(UserManager userManager, RoomManager roomManager, 
                   BookingManager bookingManager, BillService billService) {
        this.userManager = userManager;
        this.roomManager = roomManager;
        this.bookingManager = bookingManager;
        this.billService = billService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║                BÁO CÁO                  ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║ 1. Thống kê khách hàng                  ║");
            System.out.println("║ 2. Thống kê phòng                       ║");
            System.out.println("║ 3. Thống kê doanh thu                   ║");
            System.out.println("║ 4. Báo cáo tổng hợp                     ║");
            System.out.println("║ 0. Quay lại                             ║");
            System.out.println("╚══════════════════════════════════════════╝");
            
            int choice = getIntInput("Chọn chức năng: ");
            
            switch (choice) {
                case 1:
                    userManager.displayStatistics();
                    break;
                case 2:
                    displayRoomStatistics();
                    break;
                case 3:
                    displayRevenueStatistics();
                    break;
                case 4:
                    displayComprehensiveReport();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }

    private void displayRoomStatistics() {
        System.out.println("\n=== THỐNG KÊ PHÒNG ===");
        
        var rooms = roomManager.getRooms();
        if (rooms.isEmpty()) {
            System.out.println("❌ Không có phòng nào!");
            return;
        }
        
        int totalRooms = rooms.size();
        int availableRooms = 0;
        int occupiedRooms = 0;
        int reservedRooms = 0;
        int maintenanceRooms = 0;
        
        for (Room room : rooms) {
            String status = room.getCurrentState().getStateName();
            switch (status) {
                case "Available": availableRooms++; break;
                case "Occupied": occupiedRooms++; break;
                case "Reserved": reservedRooms++; break;
                case "Maintenance": maintenanceRooms++; break;
            }
        }
        
        System.out.println("Tổng số phòng: " + totalRooms);
        System.out.println("Phòng trống: " + availableRooms + " (" + 
                         String.format("%.1f", (double)availableRooms/totalRooms*100) + "%)");
        System.out.println("Phòng đang sử dụng: " + occupiedRooms + " (" + 
                         String.format("%.1f", (double)occupiedRooms/totalRooms*100) + "%)");
        System.out.println("Phòng đã đặt: " + reservedRooms + " (" + 
                         String.format("%.1f", (double)reservedRooms/totalRooms*100) + "%)");
        System.out.println("Phòng bảo trì: " + maintenanceRooms + " (" + 
                         String.format("%.1f", (double)maintenanceRooms/totalRooms*100) + "%)");
        
        // Thống kê theo loại phòng
        System.out.println("\n--- Thống kê theo loại phòng ---");
        for (var type : com.hotelsystem.enums.RoomType.values()) {
            var roomsByType = roomManager.getRoomsByType(type);
            if (!roomsByType.isEmpty()) {
                System.out.println(type + ": " + roomsByType.size() + " phòng");
            }
        }
    }

    private void displayRevenueStatistics() {
        System.out.println("\n=== THỐNG KÊ DOANH THU ===");
        
        var bills = billService.getAllBills();
        if (bills.isEmpty()) {
            System.out.println("❌ Không có hóa đơn nào!");
            return;
        }
        
        double totalRevenue = 0;
        int totalBills = bills.size();
        
        for (var bill : bills) {
            totalRevenue += bill.getTotalAmount();
        }
        
        System.out.println("Tổng số hóa đơn: " + totalBills);
        System.out.println("Tổng doanh thu: " + String.format("%,.0f", totalRevenue) + " VND");
        System.out.println("Doanh thu trung bình: " + String.format("%,.0f", totalRevenue/totalBills) + " VND");
        
        // Thống kê theo phương thức thanh toán
        System.out.println("\n--- Thống kê theo phương thức thanh toán ---");
        // TODO: Implement payment method statistics
    }

    private void displayComprehensiveReport() {
        System.out.println("\n=== BÁO CÁO TỔNG HỢP ===");
        System.out.println("=== HỆ THỐNG QUẢN LÝ KHÁCH SẠN ===");
        System.out.println("Ngày báo cáo: " + java.time.LocalDateTime.now());
        System.out.println();
        
        // Thống kê khách hàng
        System.out.println("📊 THỐNG KÊ KHÁCH HÀNG");
        System.out.println("────────────────────────");
        userManager.displayStatistics();
        System.out.println();
        
        // Thống kê phòng
        System.out.println("🏨 THỐNG KÊ PHÒNG");
        System.out.println("──────────────────");
        displayRoomStatistics();
        System.out.println();
        
        // Thống kê booking
        System.out.println("📋 THỐNG KÊ BOOKING");
        System.out.println("────────────────────");
        var bookings = bookingManager.getBookings();
        System.out.println("Tổng số booking: " + bookings.size());
        int activeBookings = 0;
        for (var booking : bookings) {
            if (booking.getCheckOutTime() == null) {
                activeBookings++;
            }
        }
        System.out.println("Booking đang hoạt động: " + activeBookings);
        System.out.println();
        
        // Thống kê doanh thu
        System.out.println("💰 THỐNG KÊ DOANH THU");
        System.out.println("─────────────────────");
        displayRevenueStatistics();
        System.out.println();
        
        System.out.println("=== KẾT THÚC BÁO CÁO ===");
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