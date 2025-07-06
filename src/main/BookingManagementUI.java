package main;

import java.util.Scanner;
import com.hotelsystem.user.*;
import com.hotelsystem.room.*;
import com.hotelsystem.services.*;
import com.hotelsystem.services.strategy.*;
import com.hotelsystem.builder.*;

public class BookingManagementUI {
    private static final Scanner scanner = new Scanner(System.in);
    private final BookingManager bookingManager;
    private final UserManager userManager;
    private final RoomManager roomManager;

    public BookingManagementUI(BookingManager bookingManager, UserManager userManager, RoomManager roomManager) {
        this.bookingManager = bookingManager;
        this.userManager = userManager;
        this.roomManager = roomManager;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║            QUẢN LÝ ĐẶT PHÒNG            ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║ 1. Đặt phòng                             ║");
            System.out.println("║ 2. Check-in                              ║");
            System.out.println("║ 3. Check-out                             ║");
            System.out.println("║ 4. Hủy đặt phòng                         ║");
            System.out.println("║ 5. Bảo trì phòng                         ║");
            System.out.println("║ 6. Xem danh sách booking                 ║");
            System.out.println("║ 7. Quản lý lệnh (Undo/Redo)             ║");
            System.out.println("║ 0. Quay lại                              ║");
            System.out.println("╚══════════════════════════════════════════╝");
            
            int choice = getIntInput("Chọn chức năng: ");
            
            switch (choice) {
                case 1:
                    bookRoom();
                    break;
                case 2:
                    checkIn();
                    break;
                case 3:
                    checkOut();
                    break;
                case 4:
                    cancelBooking();
                    break;
                case 5:
                    maintenanceMenu();
                    break;
                case 6:
                    bookingManager.displayBookings();
                    break;
                case 7:
                    commandManagementMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }

    private void bookRoom() {
        System.out.println("\n=== ĐẶT PHÒNG ===");
        
        // Hiển thị danh sách khách hàng
        System.out.println("Danh sách khách hàng:");
        displayUserList();
        
        // Hiển thị danh sách phòng
        System.out.println("\nDanh sách phòng:");
        displayAllRooms();
        
        int userId = getIntInput("Nhập ID khách hàng: ");
        System.out.print("Nhập số phòng: ");
        String roomNumber = scanner.nextLine();
        
        User user = userManager.getUser(userId);
        Room room = roomManager.getRoomByNumber(roomNumber);
        
        if (user == null || room == null) {
            System.out.println("❌ Không tìm thấy khách hàng hoặc phòng!");
            return;
        }
        
        // Sử dụng Command pattern thông qua BookingManager
        boolean success = bookingManager.reserveRoom(room, user);
        if (!success) {
            System.out.println("❌ Không thể đặt phòng!");
        }
    }

    private void checkIn() {
        System.out.println("\n=== CHECK-IN ===");
        
        // Hiển thị danh sách khách hàng
        System.out.println("Danh sách khách hàng:");
        displayUserList();
        
        // Hiển thị danh sách phòng
        System.out.println("\nDanh sách phòng:");
        displayAllRooms();
        
        // Hiển thị danh sách booking
        System.out.println("\nDanh sách booking hiện tại:");
        bookingManager.displayBookings();
        
        int userId = getIntInput("Nhập ID khách hàng: ");
        System.out.print("Nhập số phòng: ");
        String roomNumber = scanner.nextLine();
        
        User user = userManager.getUser(userId);
        Room room = roomManager.getRoomByNumber(roomNumber);
        
        if (user == null || room == null) {
            System.out.println("❌ Không tìm thấy khách hàng hoặc phòng!");
            return;
        }
        
        // Kiểm tra trạng thái phòng để quyết định method nào
        String roomState = room.getCurrentState().getStateName();
        boolean success = false;
        
        if (roomState.equals("Available")) {
            // Check-in phòng mới
            success = bookingManager.bookRoom(room, user);
        } else if (roomState.equals("Reserved")) {
            // Check-in phòng đã đặt
            success = bookingManager.checkInReservedRoom(room, user);
        } else {
            System.out.println("❌ Phòng không ở trạng thái có thể check-in!");
            System.out.println("Trạng thái hiện tại: " + roomState);
            return;
        }
        
        if (!success) {
            System.out.println("❌ Không thể check-in phòng!");
        }
    }

    private void checkOut() {
        System.out.println("\n=== CHECK-OUT ===");
        
        // Hiển thị danh sách khách hàng
        System.out.println("Danh sách khách hàng:");
        displayUserList();
        
        // Hiển thị danh sách phòng
        System.out.println("\nDanh sách phòng:");
        displayAllRooms();
        
        // Hiển thị danh sách booking
        System.out.println("\nDanh sách booking hiện tại:");
        bookingManager.displayBookings();
        
        int userId = getIntInput("Nhập ID khách hàng: ");
        System.out.print("Nhập số phòng: ");
        String roomNumber = scanner.nextLine();
        
        User user = userManager.getUser(userId);
        Room room = roomManager.getRoomByNumber(roomNumber);
        
        if (user == null || room == null) {
            System.out.println("❌ Không tìm thấy khách hàng hoặc phòng!");
            return;
        }
        
        // Thực hiện thanh toán trước khi checkout
        if (processPayment(room)) {
            bookingManager.checkOut(room, user);
        } else {
            System.out.println("❌ Thanh toán thất bại! Không thể checkout.");
        }
    }

    private void cancelBooking() {
        System.out.println("\n=== HỦY ĐẶT PHÒNG ===");
        
        // Hiển thị danh sách khách hàng
        System.out.println("Danh sách khách hàng:");
        displayUserList();
        
        // Hiển thị danh sách phòng
        System.out.println("\nDanh sách phòng:");
        displayAllRooms();
        
        int userId = getIntInput("Nhập ID khách hàng: ");
        System.out.print("Nhập số phòng: ");
        String roomNumber = scanner.nextLine();
        
        User user = userManager.getUser(userId);
        Room room = roomManager.getRoomByNumber(roomNumber);
        
        if (user == null || room == null) {
            System.out.println("❌ Không tìm thấy khách hàng hoặc phòng!");
            return;
        }
        
        boolean success = bookingManager.cancelReservation(room, user);
        if (!success) {
            System.out.println("❌ Không thể hủy đặt phòng!");
        }
    }

    private void maintenanceMenu() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║              BẢO TRÌ PHÒNG              ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║ 1. Bắt đầu bảo trì                       ║");
            System.out.println("║ 2. Kết thúc bảo trì                      ║");
            System.out.println("║ 0. Quay lại                              ║");
            System.out.println("╚══════════════════════════════════════════╝");
            
            int choice = getIntInput("Chọn chức năng: ");
            
            switch (choice) {
                case 1:
                    startMaintenance();
                    break;
                case 2:
                    finishMaintenance();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }

    private void startMaintenance() {
        System.out.println("\n=== BẮT ĐẦU BẢO TRÌ ===");
        
        // Hiển thị danh sách phòng
        System.out.println("Danh sách phòng:");
        displayAllRooms();
        
        System.out.print("Nhập số phòng cần bảo trì: ");
        String roomNumber = scanner.nextLine();
        
        Room room = roomManager.getRoomByNumber(roomNumber);
        if (room == null) {
            System.out.println("❌ Không tìm thấy phòng!");
            return;
        }
        
        // Tạo user admin cho bảo trì
        User adminUser = new User(999, "Admin", "0000000000", "admin@hotel.com");
        bookingManager.startMaintenance(room, adminUser);
    }

    private void finishMaintenance() {
        System.out.println("\n=== KẾT THÚC BẢO TRÌ ===");
        
        // Hiển thị danh sách phòng
        System.out.println("Danh sách phòng:");
        displayAllRooms();
        
        System.out.print("Nhập số phòng cần kết thúc bảo trì: ");
        String roomNumber = scanner.nextLine();
        
        Room room = roomManager.getRoomByNumber(roomNumber);
        if (room == null) {
            System.out.println("❌ Không tìm thấy phòng!");
            return;
        }
        
        // Tạo user admin cho bảo trì
        User adminUser = new User(999, "Admin", "0000000000", "admin@hotel.com");
        bookingManager.finishMaintenance(room, adminUser);
    }

    private void commandManagementMenu() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║           QUẢN LÝ LỆNH (UNDO/REDO)      ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║ 1. Hoàn tác lệnh cuối cùng (Undo)       ║");
            System.out.println("║ 2. Làm lại lệnh cuối cùng (Redo)        ║");
            System.out.println("║ 3. Xem lịch sử lệnh                     ║");
            System.out.println("║ 4. Xem lịch sử hoàn tác                 ║");
            System.out.println("║ 5. Xóa lịch sử lệnh                     ║");
            System.out.println("║ 6. Thống kê lệnh                        ║");
            System.out.println("║ 0. Quay lại                             ║");
            System.out.println("╚══════════════════════════════════════════╝");
            
            int choice = getIntInput("Chọn chức năng: ");
            
            switch (choice) {
                case 1:
                    if (bookingManager.hasCommandsToUndo()) {
                        bookingManager.undoLastCommand();
                    } else {
                        System.out.println("❌ Không có lệnh nào để hoàn tác!");
                    }
                    break;
                case 2:
                    if (bookingManager.hasCommandsToRedo()) {
                        bookingManager.redoLastCommand();
                    } else {
                        System.out.println("❌ Không có lệnh nào để làm lại!");
                    }
                    break;
                case 3:
                    bookingManager.showCommandHistory();
                    break;
                case 4:
                    bookingManager.showUndoHistory();
                    break;
                case 5:
                    bookingManager.clearCommandHistory();
                    break;
                case 6:
                    displayCommandStatistics();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }

    private void displayCommandStatistics() {
        System.out.println("\n=== THỐNG KÊ LỆNH ===");
        System.out.println("Tổng số lệnh đã thực hiện: " + bookingManager.getCommandCount());
        System.out.println("Số lệnh có thể làm lại: " + bookingManager.getUndoCount());
        System.out.println("Có lệnh để hoàn tác: " + (bookingManager.hasCommandsToUndo() ? "Có" : "Không"));
        System.out.println("Có lệnh để làm lại: " + (bookingManager.hasCommandsToRedo() ? "Có" : "Không"));
    }

    private void displayUserList() {
        System.out.println("\n=== DANH SÁCH KHÁCH HÀNG ===");
        var users = userManager.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("❌ Không có khách hàng nào!");
            return;
        }
        
        System.out.println("ID\tTên khách hàng\t\tSố điện thoại\tEmail");
        System.out.println("──\t──────────────\t\t─────────────\t─────");
        for (User user : users) {
            System.out.printf("%-2d\t%-16s\t%-13s\t%s\n", 
                            user.getId(), 
                            user.getName(), 
                            user.getPhone(), 
                            user.getEmail());
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
    
    private boolean processPayment(Room room) {
        System.out.println("\n=== THANH TOÁN ===");
        System.out.println("Phòng: " + room.getRoomNumber());
        System.out.println("Loại phòng: " + room.getRoomType());
        System.out.println("Giá phòng: " + String.format("%,.0f", room.getCost()) + " VND");
        
        // Tính tổng tiền
        double totalAmount = room.getCost();
        System.out.println("Tổng tiền: " + String.format("%,.0f", totalAmount) + " VND");
        
        // Chọn phương thức thanh toán
        String paymentMethod = selectPaymentMethod();
        PaymentStrategy paymentStrategy = getPaymentStrategy(paymentMethod);
        
        // Xác nhận thanh toán
        System.out.println("\n=== XÁC NHẬN THANH TOÁN ===");
        System.out.println("Tổng tiền: " + String.format("%,.0f", totalAmount) + " VND");
        System.out.println("Phương thức: " + paymentStrategy.getPaymentMethodName());
        
        System.out.print("Xác nhận thanh toán? (y/n): ");
        String confirm = scanner.nextLine();
        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("❌ Đã hủy thanh toán!");
            return false;
        }
        
        // Thực hiện thanh toán
        if (paymentStrategy.pay(totalAmount)) {
            System.out.println("✅ Thanh toán thành công!");
            
            // Tạo hóa đơn sử dụng Builder Pattern trực tiếp từ BookingManager
            Bill bill = bookingManager.createBillWithBuilder(room, totalAmount, paymentStrategy.getPaymentMethodName());
            
            return true;
        } else {
            System.out.println("❌ Thanh toán thất bại!");
            return false;
        }
    }
    
    private String selectPaymentMethod() {
        System.out.println("\nChọn phương thức thanh toán:");
        System.out.println("1. Tiền mặt");
        System.out.println("2. Thẻ tín dụng");
        System.out.println("3. Chuyển khoản");
        
        int choice = getIntInput("Lựa chọn: ");
        switch (choice) {
            case 1: return "Tiền mặt";
            case 2: return "Thẻ tín dụng";
            case 3: return "Chuyển khoản";
            default: return "Tiền mặt";
        }
    }
    
    private PaymentStrategy getPaymentStrategy(String method) {
        switch (method) {
            case "Tiền mặt": return new CashPayment();
            case "Thẻ tín dụng": return new CreditCardPayment();
            case "Chuyển khoản": return new BankTransferPayment();
            default: return new CashPayment();
        }
    }
} 