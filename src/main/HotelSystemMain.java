package main;

import java.util.Scanner;
import com.hotelsystem.user.*;
import com.hotelsystem.services.*;
import com.hotelsystem.observer.UiDisplay;
import com.hotelsystem.observer.Observer;

public class HotelSystemMain {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserManager userManager = UserMockData.createMockUserManager();
    private static final RoomManager roomManager = RoomMockData.createMockRoomManager();
    private static final BookingManager bookingManager = BookingManager.getInstance();
    private static final BillService billService = new BillService();
    private static final RoomTypeManager roomTypeManager = new RoomTypeManager();

    // UI Components
    private static final UserManagementUI userUI = new UserManagementUI(userManager);
    private static final RoomManagementUI roomUI = new RoomManagementUI(roomManager, billService, roomTypeManager);
    private static final BookingManagementUI bookingUI = new BookingManagementUI(bookingManager, userManager, roomManager);
    private static final ReportUI reportUI = new ReportUI(userManager, roomManager, bookingManager, billService);

    public static void main(String[] args) {
        System.out.println("=== HỆ THỐNG QUẢN LÝ KHÁCH SẠN ===");
        System.out.println("Khởi động hệ thống...");
        
        // Register global observer for all rooms
        Observer uiDisplay = new UiDisplay();
        for (com.hotelsystem.room.Room room : roomManager.getRooms()) {
            room.registerObserver(uiDisplay);
        }
        
        while (true) {
            displayMainMenu();
            int choice = getIntInput("Chọn chức năng: ");
            
            switch (choice) {
                case 1:
                    userUI.showMenu();
                    break;
                case 2:
                    roomUI.showMenu();
                    break;
                case 3:
                    bookingUI.showMenu();
                    break;
                case 4:
                    reportUI.showMenu();
                    break;
                case 0:
                    System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║     HỆ THỐNG QUẢN LÝ KHÁCH SẠN         ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ 1. Quản lý khách hàng                   ║");
        System.out.println("║ 2. Quản lý phòng                        ║");
        System.out.println("║ 3. Quản lý đặt phòng                    ║");
        System.out.println("║ 4. Xem báo cáo                          ║");
        System.out.println("║ 0. Thoát                                ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    private static int getIntInput(String prompt) {
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