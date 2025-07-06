package main;

import java.util.Scanner;
import com.hotelsystem.user.*;
import com.hotelsystem.services.*;
import com.hotelsystem.services.strategy.BankTransferPayment;
import com.hotelsystem.services.strategy.CashPayment;
import com.hotelsystem.services.strategy.CreditCardPayment;
import com.hotelsystem.services.strategy.PaymentStrategy;
import com.hotelsystem.room.*;
import com.hotelsystem.enums.*;
import com.hotelsystem.decorator.*;
import com.hotelsystem.builder.*;
import java.time.LocalDateTime;

public class HotelManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserManager userManager = UserMockData.createMockUserManager();
    private static final RoomManager roomManager = RoomMockData.createMockRoomManager();
    private static final BookingManager bookingManager = BookingManager.getInstance();
    private static final BillService billService = new BillService();
    private static final RoomTypeManager roomTypeManager = new RoomTypeManager();

    public static void main(String[] args) {
        System.out.println("=== HỆ THỐNG QUẢN LÝ KHÁCH SẠN ===");
        System.out.println("Khởi động hệ thống...");
        
        while (true) {
            displayMainMenu();
            int choice = getIntInput("Chọn chức năng: ");
            
            switch (choice) {
                case 1:
                    userManagementMenu();
                    break;
                case 2:
                    roomManagementMenu();
                    break;
                case 3:
                    bookingManagementMenu();
                    break;
                case 4:
                    reportMenu();
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

    private static void userManagementMenu() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║           QUẢN LÝ KHÁCH HÀNG            ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║ 1. Thêm khách hàng mới                  ║");
            System.out.println("║ 2. Xem danh sách khách hàng             ║");
            System.out.println("║ 3. Tìm kiếm khách hàng                  ║");
            System.out.println("║ 4. Cập nhật thông tin khách hàng        ║");
            System.out.println("║ 5. Xóa khách hàng                       ║");
            System.out.println("║ 6. Xem lịch sử khách hàng               ║");
            System.out.println("║ 7. Thống kê khách hàng                  ║");
            System.out.println("║ 0. Quay lại                             ║");
            System.out.println("╚══════════════════════════════════════════╝");
            
            int choice = getIntInput("Chọn chức năng: ");
            
            switch (choice) {
                case 1:
                    addNewUser();
                    break;
                case 2:
                    userManager.displayAllUsers();
                    break;
                case 3:
                    searchUser();
                    break;
                case 4:
                    updateUser();
                    break;
                case 5:
                    removeUser();
                    break;
                case 6:
                    viewUserHistory();
                    break;
                case 7:
                    userManager.displayStatistics();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void roomManagementMenu() {
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

    private static void bookingManagementMenu() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║            QUẢN LÝ ĐẶT PHÒNG            ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║ 1. Đặt phòng                             ║");
            System.out.println("║ 2. Check-in                              ║");
            System.out.println("║ 3. Check-out                             ║");
            System.out.println("║ 4. Hủy đặt phòng                         ║");
            System.out.println("║ 5. Xem danh sách booking                 ║");
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
                    bookingManager.displayBookings();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void reportMenu() {
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

    // ========== USER MANAGEMENT METHODS ==========
    
    private static void addNewUser() {
        System.out.println("\n=== THÊM KHÁCH HÀNG MỚI ===");
        
        // Hiển thị danh sách khách hàng hiện tại
        System.out.println("Danh sách khách hàng hiện tại:");
        displayUserList();
        
        int id = getIntInput("Nhập ID khách hàng: ");
        System.out.print("Nhập tên khách hàng: ");
        String name = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phone = scanner.nextLine();
        System.out.print("Nhập email: ");
        String email = scanner.nextLine();
        
        User newUser = new User(id, name, phone, email);
        userManager.addUser(newUser);
    }

    private static void searchUser() {
        System.out.println("\n=== TÌM KIẾM KHÁCH HÀNG ===");
        System.out.println("1. Tìm theo tên");
        System.out.println("2. Tìm theo email");
        
        int choice = getIntInput("Chọn cách tìm: ");
        
        switch (choice) {
            case 1:
                System.out.print("Nhập tên cần tìm: ");
                String name = scanner.nextLine();
                var foundByName = userManager.findUsersByName(name);
                if (foundByName.isEmpty()) {
                    System.out.println("❌ Không tìm thấy khách hàng nào!");
                } else {
                    System.out.println("✅ Tìm thấy " + foundByName.size() + " khách hàng:");
                    for (User user : foundByName) {
                        System.out.println(user);
                    }
                }
                break;
            case 2:
                System.out.print("Nhập email cần tìm: ");
                String email = scanner.nextLine();
                User foundByEmail = userManager.findUserByEmail(email);
                if (foundByEmail == null) {
                    System.out.println("❌ Không tìm thấy khách hàng!");
                } else {
                    System.out.println("✅ Tìm thấy: " + foundByEmail);
                }
                break;
            default:
                System.out.println("❌ Lựa chọn không hợp lệ!");
        }
    }

    private static void updateUser() {
        System.out.println("\n=== CẬP NHẬT THÔNG TIN KHÁCH HÀNG ===");
        
        // Hiển thị danh sách khách hàng
        System.out.println("Danh sách khách hàng:");
        displayUserList();
        
        int id = getIntInput("Nhập ID khách hàng cần cập nhật: ");
        
        User existingUser = userManager.getUser(id);
        if (existingUser == null) {
            System.out.println("❌ Không tìm thấy khách hàng với ID: " + id);
            return;
        }
        
        System.out.println("Thông tin hiện tại: " + existingUser);
        System.out.print("Nhập tên mới (Enter để giữ nguyên): ");
        String name = scanner.nextLine();
        if (!name.trim().isEmpty()) {
            existingUser.setName(name);
        }
        
        System.out.print("Nhập số điện thoại mới (Enter để giữ nguyên): ");
        String phone = scanner.nextLine();
        if (!phone.trim().isEmpty()) {
            existingUser.setPhone(phone);
        }
        
        System.out.print("Nhập email mới (Enter để giữ nguyên): ");
        String email = scanner.nextLine();
        if (!email.trim().isEmpty()) {
            existingUser.setEmail(email);
        }
        
        userManager.updateUser(existingUser);
    }

    private static void removeUser() {
        System.out.println("\n=== XÓA KHÁCH HÀNG ===");
        
        // Hiển thị danh sách khách hàng
        System.out.println("Danh sách khách hàng:");
        displayUserList();
        
        int id = getIntInput("Nhập ID khách hàng cần xóa: ");
        userManager.removeUser(id);
    }

    private static void viewUserHistory() {
        System.out.println("\n=== XEM LỊCH SỬ KHÁCH HÀNG ===");
        
        // Hiển thị danh sách khách hàng
        System.out.println("Danh sách khách hàng:");
        displayUserList();
        
        int id = getIntInput("Nhập ID khách hàng: ");
        userManager.displayUserHistory(id);
    }

    // ========== ROOM MANAGEMENT METHODS ==========
    
    private static void displayAllRooms() {
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
    
    private static void displayUserList() {
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

    private static void displayAvailableRooms() {
        System.out.println("\n=== DANH SÁCH PHÒNG TRỐNG ===");
        System.out.println("Loại phòng\tPhòng trống/Tổng số\tChi tiết");
        System.out.println("──────────\t─────────────────\t────────");
        
        for (RoomType type : RoomType.values()) {
            int available = roomManager.countAvailableRooms(type);
            int total = roomManager.getRoomsByType(type).size();
            
            System.out.printf("%-10s\t%d/%d phòng\t\t", type, available, total);
            
            if (available > 0) {
                var availableRooms = roomManager.getAvailableRooms(type);
                System.out.print("Phòng: ");
                for (int i = 0; i < availableRooms.size(); i++) {
                    if (i > 0) System.out.print(", ");
                    System.out.print(availableRooms.get(i).getRoomNumber());
                }
            } else {
                System.out.print("Không có phòng trống");
            }
            System.out.println();
        }
    }

    private static void displayRoomsByType() {
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
        System.out.println("=== PHÒNG " + selectedType + " ===");
        for (Room room : rooms) {
            System.out.println("Phòng " + room.getRoomNumber() + 
                             " - " + room.getCurrentState().getStateName() + 
                             " - " + room.getCost() + " VND");
        }
    }

    private static void displayBills() {
        System.out.println("\n=== DANH SÁCH HÓA ĐƠN ===");
        var bills = billService.getAllBills();
        if (bills.isEmpty()) {
            System.out.println("❌ Chưa có hóa đơn nào!");
            return;
        }
        
        for (Bill bill : bills) {
            System.out.println(bill);
        }
    }

    private static void roomTypeManagementMenu() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║           QUẢN LÝ LOẠI PHÒNG            ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║ 1. Xem danh sách loại phòng             ║");
            System.out.println("║ 2. Sửa tên loại phòng                   ║");
            System.out.println("║ 3. Thêm loại phòng                      ║");
            System.out.println("║ 4. Xóa loại phòng                       ║");
            System.out.println("║ 0. Quay lại                             ║");
            System.out.println("╚══════════════════════════════════════════╝");
            
            int choice = getIntInput("Chọn chức năng: ");
            
            switch (choice) {
                case 1:
                    roomTypeManager.showRoomTypes();
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

    private static void editRoomTypeName() {
        System.out.println("\n=== SỬA TÊN LOẠI PHÒNG ===");
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
        
        System.out.print("Nhập tên mới: ");
        String newName = scanner.nextLine();
        roomTypeManager.editRoomTypeName(selectedType, newName);
        System.out.println("✅ Đã cập nhật tên loại phòng!");
    }

    private static void addRoomType() {
        System.out.println("\n=== THÊM LOẠI PHÒNG ===");
        System.out.println("Chức năng này sẽ được mở rộng trong tương lai!");
    }

    private static void removeRoomType() {
        System.out.println("\n=== XÓA LOẠI PHÒNG ===");
        System.out.println("1. SINGLE");
        System.out.println("2. DOUBLE");
        System.out.println("3. SUITE");
        
        int choice = getIntInput("Chọn loại phòng cần xóa: ");
        RoomType selectedType = null;
        
        switch (choice) {
            case 1: selectedType = RoomType.SINGLE; break;
            case 2: selectedType = RoomType.DOUBLE; break;
            case 3: selectedType = RoomType.SUITE; break;
            default:
                System.out.println("❌ Lựa chọn không hợp lệ!");
                return;
        }
        
        roomTypeManager.removeRoomType(selectedType);
        System.out.println("✅ Đã xóa loại phòng!");
    }

    // ========== BOOKING MANAGEMENT METHODS ==========
    
    private static void bookRoom() {
        System.out.println("\n=== ĐẶT PHÒNG ===");
        
        // Chọn khách hàng
        System.out.println("Danh sách khách hàng:");
        displayUserList();
        int userId = getIntInput("Nhập ID khách hàng: ");
        User user = userManager.getUser(userId);
        if (user == null) {
            System.out.println("❌ Không tìm thấy khách hàng!");
            return;
        }
        
        // Chọn loại phòng
        System.out.println("Chọn loại phòng:");
        System.out.println("1. SINGLE");
        System.out.println("2. DOUBLE");
        System.out.println("3. SUITE");
        int typeChoice = getIntInput("Lựa chọn: ");
        
        RoomType selectedType = null;
        switch (typeChoice) {
            case 1: selectedType = RoomType.SINGLE; break;
            case 2: selectedType = RoomType.DOUBLE; break;
            case 3: selectedType = RoomType.SUITE; break;
            default:
                System.out.println("❌ Lựa chọn không hợp lệ!");
                return;
        }
        
        // Kiểm tra phòng trống
        if (roomManager.countAvailableRooms(selectedType) == 0) {
            System.out.println("❌ Không còn phòng trống loại này!");
            return;
        }
        
        // Đặt phòng
        Room selectedRoom = roomManager.bookRoom(selectedType);
        if (selectedRoom == null) {
            System.out.println("❌ Đặt phòng thất bại!");
            return;
        }
        
        // Thêm dịch vụ
        Room decoratedRoom = addServices(selectedRoom);
        
        // Thanh toán
        double totalAmount = decoratedRoom.getCost();
        System.out.println("Tổng tiền: " + totalAmount + " VND");
        
        String paymentMethod = selectPaymentMethod();
        PaymentStrategy paymentStrategy = getPaymentStrategy(paymentMethod);
        
        if (paymentStrategy.pay(totalAmount)) {
            System.out.println("✅ Đặt phòng thành công!");
            System.out.println("Số phòng: " + selectedRoom.getRoomNumber());
            
            // Tạo hóa đơn
            RoomInfo roomInfo = new RoomInfo(selectedRoom, true);
            billService.createBill(roomInfo, totalAmount, paymentMethod);
            
            // Tạo lịch sử
            UserHistory history = new UserHistory(userId, selectedRoom.getRoomNumber());
            history.setCheckInTime(LocalDateTime.now());
            history.setTotalCost(totalAmount);
            userManager.addUserHistory(history);
        } else {
            System.out.println("❌ Thanh toán thất bại!");
            selectedRoom.checkOut(null); // Trả lại trạng thái phòng
        }
    }

    private static Room addServices(Room room) {
        Room decoratedRoom = room;
        
        System.out.print("Thêm dịch vụ ăn sáng? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            decoratedRoom = new BreakfastDecorator(decoratedRoom);
        }
        
        System.out.print("Thêm dịch vụ giặt là? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            decoratedRoom = new LaundryDecorator(decoratedRoom);
        }
        
        System.out.print("Thêm dịch vụ spa? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            decoratedRoom = new SpaDecorator(decoratedRoom);
        }
        
        return decoratedRoom;
    }

    private static String selectPaymentMethod() {
        System.out.println("Chọn phương thức thanh toán:");
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

    private static PaymentStrategy getPaymentStrategy(String method) {
        switch (method) {
            case "Tiền mặt": return new CashPayment();
            case "Thẻ tín dụng": return new CreditCardPayment();
            case "Chuyển khoản": return new BankTransferPayment();
            default: return new CashPayment();
        }
    }

    private static void checkIn() {
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
        
        bookingManager.bookRoom(room, user);
    }

    private static void checkOut() {
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
        
        bookingManager.checkOut(room, user);
    }

    private static void cancelBooking() {
        System.out.println("\n=== HỦY ĐẶT PHÒNG ===");
        System.out.println("Chức năng này sẽ được mở rộng trong tương lai!");
    }

    // ========== REPORT METHODS ==========
    
    private static void displayRoomStatistics() {
        System.out.println("\n=== THỐNG KÊ PHÒNG ===");
        var rooms = roomManager.getRooms();
        
        System.out.println("Tổng số phòng: " + rooms.size());
        
        for (RoomType type : RoomType.values()) {
            int total = roomManager.getRoomsByType(type).size();
            int available = roomManager.countAvailableRooms(type);
            int occupied = 0, reserved = 0, maintenance = 0;
            
            for (Room room : roomManager.getRoomsByType(type)) {
                String state = room.getCurrentState().getStateName();
                switch (state) {
                    case "Occupied": occupied++; break;
                    case "Reserved": reserved++; break;
                    case "Maintenance": maintenance++; break;
                }
            }
            
            System.out.println("\n" + type + ":");
            System.out.println("  - Tổng: " + total + " phòng");
            System.out.println("  - Trống: " + available + " phòng");
            System.out.println("  - Đang sử dụng: " + occupied + " phòng");
            System.out.println("  - Đã đặt: " + reserved + " phòng");
            System.out.println("  - Bảo trì: " + maintenance + " phòng");
        }
    }

    private static void displayRevenueStatistics() {
        System.out.println("\n=== THỐNG KÊ DOANH THU ===");
        var bills = billService.getAllBills();
        
        if (bills.isEmpty()) {
            System.out.println("❌ Chưa có hóa đơn nào!");
            return;
        }
        
        double totalRevenue = 0;
        int totalBills = bills.size();
        
        for (Bill bill : bills) {
            totalRevenue += bill.getTotalAmount();
        }
        
        System.out.println("Tổng số hóa đơn: " + totalBills);
        System.out.println("Tổng doanh thu: " + totalRevenue + " VND");
        System.out.println("Doanh thu trung bình: " + (totalRevenue / totalBills) + " VND");
    }

    private static void displayComprehensiveReport() {
        System.out.println("\n=== BÁO CÁO TỔNG HỢP ===");
        
        // Thống kê khách hàng
        userManager.displayStatistics();
        
        // Thống kê phòng
        displayRoomStatistics();
        
        // Thống kê doanh thu
        displayRevenueStatistics();
        
        // Thống kê booking
        System.out.println("\n=== THỐNG KÊ BOOKING ===");
        var bookings = bookingManager.getBookings();
        System.out.println("Tổng số booking: " + bookings.size());
        
        int activeBookings = 0;
        for (var booking : bookings) {
            if (booking.getCheckOutTime() == null) {
                activeBookings++;
            }
        }
        System.out.println("Booking đang hoạt động: " + activeBookings);
    }

    // ========== UTILITY METHODS ==========
    
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