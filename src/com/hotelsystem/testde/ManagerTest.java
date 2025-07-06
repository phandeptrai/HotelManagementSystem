package com.hotelsystem.testde;

import com.hotelsystem.user.User;
import com.hotelsystem.user.UserHistory;
import com.hotelsystem.user.UserManager;
import com.hotelsystem.services.*;
import com.hotelsystem.services.strategy.*;
import com.hotelsystem.room.Room;
import com.hotelsystem.room.RoomInfo;
import com.hotelsystem.builder.Bill;
import com.hotelsystem.builder.BillBuilder;
import com.hotelsystem.enums.RoomType;
import com.hotelsystem.enums.RoomStatus;
import java.time.LocalDateTime;

public class ManagerTest {
    
    public static void main(String[] args) {
        System.out.println("=== KIỂM TRA CÁC MANAGER ===");
        
        // Test UserManager
        testUserManager();
        
        // Test RoomManager
        testRoomManager();
        
        // Test BookingManager
        testBookingManager();
        
        // Test RoomTypeManager
        testRoomTypeManager();
        
        // Test BillService
        testBillService();
        
        // Test BookingManager với Builder Pattern
        testBookingManagerWithBuilder();
        
        // Test tạo Bill trực tiếp với Builder
        testDirectBuilderUsage();
        
        // Test BillBuilderUtil
        testBillBuilderUtil();
        
        System.out.println("\n=== HOÀN THÀNH KIỂM TRA ===");
    }
    
    private static void testUserManager() {
        System.out.println("\n--- Test UserManager ---");
        UserManager userManager = UserManager.getInstance();
        
        // Test thêm user
        User user1 = new User(1, "Test User 1", "0123456789", "test1@email.com");
        User user2 = new User(2, "Test User 2", "0987654321", "test2@email.com");
        
        userManager.addUser(user1);
        userManager.addUser(user2);
        
        // Test thêm user trùng ID
        User duplicateUser = new User(1, "Duplicate User", "0000000000", "duplicate@email.com");
        userManager.addUser(duplicateUser); // Sẽ báo lỗi
        
        // Test getUser
        User foundUser = userManager.getUser(1);
        System.out.println("Tìm user ID 1: " + (foundUser != null ? foundUser.getName() : "Không tìm thấy"));
        
        // Test updateUser
        User updatedUser = new User(1, "Updated User 1", "0123456789", "updated1@email.com");
        userManager.updateUser(updatedUser);
        
        // Test findUsersByName
        var foundByName = userManager.findUsersByName("Test");
        System.out.println("Tìm user có tên chứa 'Test': " + foundByName.size() + " kết quả");
        
        // Test addUserHistory
        UserHistory history = new UserHistory(1, "101");
        history.setCheckInTime(LocalDateTime.now());
        history.setTotalCost(500000);
        userManager.addUserHistory(history);
        
        // Test displayAllUsers
        userManager.displayAllUsers();
        
        // Test displayStatistics
        userManager.displayStatistics();
        
        // Test removeUser
        userManager.removeUser(2);
        System.out.println("Sau khi xóa user ID 2:");
        userManager.displayAllUsers();
    }
    
    private static void testRoomManager() {
        System.out.println("\n--- Test RoomManager ---");
        RoomManager roomManager = RoomMockData.createMockRoomManager();
        
        // Test getRooms
        System.out.println("Tổng số phòng: " + roomManager.getRooms().size());
        
        // Test getRoomsByType
        var singleRooms = roomManager.getRoomsByType(RoomType.SINGLE);
        var doubleRooms = roomManager.getRoomsByType(RoomType.DOUBLE);
        var suiteRooms = roomManager.getRoomsByType(RoomType.SUITE);
        
        System.out.println("Phòng SINGLE: " + singleRooms.size());
        System.out.println("Phòng DOUBLE: " + doubleRooms.size());
        System.out.println("Phòng SUITE: " + suiteRooms.size());
        
        // Test countAvailableRooms
        int availableSingle = roomManager.countAvailableRooms(RoomType.SINGLE);
        int availableDouble = roomManager.countAvailableRooms(RoomType.DOUBLE);
        int availableSuite = roomManager.countAvailableRooms(RoomType.SUITE);
        
        System.out.println("Phòng SINGLE trống: " + availableSingle);
        System.out.println("Phòng DOUBLE trống: " + availableDouble);
        System.out.println("Phòng SUITE trống: " + availableSuite);
        
        // Test getAvailableRooms
        var availableRooms = roomManager.getAvailableRooms(RoomType.SINGLE);
        System.out.println("Danh sách phòng SINGLE trống: " + availableRooms.size());
    }
    
    private static void testBookingManager() {
        System.out.println("\n--- Test BookingManager ---");
        BookingManager bookingManager = BookingManager.getInstance();
        RoomManager roomManager = RoomMockData.createMockRoomManager();
        UserManager userManager = UserMockData.createMockUserManager();
        
        // Lấy phòng và user để test
        Room testRoom = roomManager.getRooms().get(0);
        User testUser = userManager.getUser(1);
        
        if (testRoom != null && testUser != null) {
            System.out.println("Test phòng: " + testRoom.getRoomNumber());
            System.out.println("Test user: " + testUser.getName());
            
            // Test bookRoom
            boolean bookingSuccess = bookingManager.bookRoom(testRoom, testUser);
            System.out.println("Đặt phòng thành công: " + bookingSuccess);
            
            // Test displayBookings
            bookingManager.displayBookings();
            
            // Test checkOut
            bookingManager.checkOut(testRoom, testUser);
            
            // Test displayBookings sau check-out
            bookingManager.displayBookings();
        } else {
            System.out.println("Không có phòng hoặc user để test");
        }
    }
    
    private static void testRoomTypeManager() {
        System.out.println("\n--- Test RoomTypeManager ---");
        RoomTypeManager roomTypeManager = new RoomTypeManager();
        
        // Test showRoomTypes
        roomTypeManager.showRoomTypes();
        
        // Test editRoomTypeName
        roomTypeManager.editRoomTypeName(RoomType.SINGLE, "Phòng Đơn");
        roomTypeManager.editRoomTypeName(RoomType.DOUBLE, "Phòng Đôi");
        roomTypeManager.editRoomTypeName(RoomType.SUITE, "Phòng Suite");
        
        System.out.println("Sau khi sửa tên:");
        roomTypeManager.showRoomTypes();
        
        // Test getDisplayName
        String singleName = roomTypeManager.getDisplayName(RoomType.SINGLE);
        System.out.println("Tên hiển thị SINGLE: " + singleName);
        
        // Test removeRoomType
        roomTypeManager.removeRoomType(RoomType.SUITE);
        System.out.println("Sau khi xóa SUITE:");
        roomTypeManager.showRoomTypes();
        
        // Test addRoomType
        roomTypeManager.addRoomType(RoomType.SUITE);
        System.out.println("Sau khi thêm lại SUITE:");
        roomTypeManager.showRoomTypes();
    }
    
    private static void testBillService() {
        System.out.println("\n--- Test BillService với Builder Pattern ---");
        BillService billService = new BillService();
        
        // Tạo RoomInfo mock với room thật
        RoomManager roomManager = RoomMockData.createMockRoomManager();
        Room testRoom = roomManager.getRooms().get(0);
        RoomInfo mockRoomInfo = new RoomInfo(testRoom, true);
        
        // Test createBill (sử dụng Builder bên trong)
        Bill bill1 = billService.createBill(mockRoomInfo, 500000, "Tiền mặt");
        Bill bill2 = billService.createBill(mockRoomInfo, 800000, "Thẻ tín dụng");
        
        System.out.println("Đã tạo hóa đơn 1: " + bill1.getBillId());
        System.out.println("Đã tạo hóa đơn 2: " + bill2.getBillId());
        
        // Test getAllBills
        var allBills = billService.getAllBills();
        System.out.println("Tổng số hóa đơn: " + allBills.size());
        
        for (Bill bill : allBills) {
            System.out.println("Hóa đơn: " + bill.getBillId() + 
                             ", Số tiền: " + bill.getTotalAmount() + 
                             ", Phương thức: " + bill.getPaymentMethod());
        }
    }
    
    private static void testBookingManagerWithBuilder() {
        System.out.println("\n--- Test BookingManager với Builder Pattern ---");
        BookingManager bookingManager = BookingManager.getInstance();
        RoomManager roomManager = RoomMockData.createMockRoomManager();
        Room testRoom = roomManager.getRooms().get(0);
        
        // Test tạo Bill trực tiếp với Builder
        Bill bill1 = bookingManager.createBillWithBuilder(testRoom, 500000, "Tiền mặt");
        Bill bill2 = bookingManager.createBillWithBuilder(testRoom, 800000, "Thẻ tín dụng");
        
        System.out.println("Đã tạo hóa đơn với Builder 1: " + bill1.getBillId());
        System.out.println("Đã tạo hóa đơn với Builder 2: " + bill2.getBillId());
        
        // Test checkout với payment
        UserManager userManager = UserMockData.createMockUserManager();
        User testUser = userManager.getUser(1);
        
        if (testUser != null) {
            // Đặt phòng trước
            bookingManager.bookRoom(testRoom, testUser);
            
            // Test checkout với payment
            PaymentStrategy cashPayment = new CashPayment();
            boolean checkoutSuccess = bookingManager.checkOutWithPayment(testRoom, testUser, 500000, cashPayment);
            System.out.println("Checkout với payment thành công: " + checkoutSuccess);
        }
    }
    
    private static void testDirectBuilderUsage() {
        System.out.println("\n--- Test tạo Bill trực tiếp với Builder ---");
        
        // Tạo RoomInfo mock
        RoomManager roomManager = RoomMockData.createMockRoomManager();
        Room testRoom = roomManager.getRooms().get(0);
        RoomInfo mockRoomInfo = new RoomInfo(testRoom, true);
        
        // Test 1: Tạo Bill trực tiếp với BillBuilder
        System.out.println("1. Tạo Bill trực tiếp với BillBuilder:");
        Bill directBill = new BillBuilder()
            .setBillId(java.util.UUID.randomUUID().toString())
            .setRoomInfo(mockRoomInfo)
            .setTotalAmount(600000)
            .setPaymentMethod("Chuyển khoản")
            .setPaid(false)  // Chưa thanh toán
            .setPaymentTime(null)  // Chưa có thời gian thanh toán
            .build();
        
        System.out.println("   - Bill ID: " + directBill.getBillId());
        System.out.println("   - Số tiền: " + String.format("%,.0f", directBill.getTotalAmount()) + " VND");
        System.out.println("   - Phương thức: " + directBill.getPaymentMethod());
        System.out.println("   - Đã thanh toán: " + directBill.isPaid());
        
        // Test 2: Tạo Bill với BillService.createBillWithBuilder
        System.out.println("\n2. Tạo Bill với BillService.createBillWithBuilder:");
        BillService billService = new BillService();
        Bill serviceBill = billService.createBillWithBuilder(mockRoomInfo, 700000, "Thẻ tín dụng");
        
        System.out.println("   - Bill ID: " + serviceBill.getBillId());
        System.out.println("   - Số tiền: " + String.format("%,.0f", serviceBill.getTotalAmount()) + " VND");
        System.out.println("   - Phương thức: " + serviceBill.getPaymentMethod());
        System.out.println("   - Đã thanh toán: " + serviceBill.isPaid());
        
        // Test 3: Tạo Bill với BillService.createAndSaveBillWithBuilder
        System.out.println("\n3. Tạo Bill với BillService.createAndSaveBillWithBuilder:");
        Bill savedBill = billService.createAndSaveBillWithBuilder(mockRoomInfo, 800000, "Tiền mặt");
        
        System.out.println("   - Bill ID: " + savedBill.getBillId());
        System.out.println("   - Số tiền: " + String.format("%,.0f", savedBill.getTotalAmount()) + " VND");
        System.out.println("   - Phương thức: " + savedBill.getPaymentMethod());
        System.out.println("   - Tổng số hóa đơn trong BillService: " + billService.getAllBills().size());
    }
    
    private static void testBillBuilderUtil() {
        System.out.println("\n--- Test BillBuilderUtil ---");
        
        // Tạo RoomInfo mock
        RoomManager roomManager = RoomMockData.createMockRoomManager();
        Room testRoom = roomManager.getRooms().get(0);
        RoomInfo mockRoomInfo = new RoomInfo(testRoom, true);
        
        // Test 1: Tạo Bill đã thanh toán
        System.out.println("1. Tạo Bill đã thanh toán:");
        Bill paidBill = com.hotelsystem.utils.BillBuilderUtil.createPaidBill(mockRoomInfo, 900000, "Tiền mặt");
        System.out.println("   - Bill ID: " + paidBill.getBillId());
        System.out.println("   - Đã thanh toán: " + paidBill.isPaid());
        System.out.println("   - Thời gian thanh toán: " + paidBill.getPaymentTime());
        
        // Test 2: Tạo Bill chưa thanh toán
        System.out.println("\n2. Tạo Bill chưa thanh toán:");
        Bill unpaidBill = com.hotelsystem.utils.BillBuilderUtil.createUnpaidBill(mockRoomInfo, 1000000, "Thẻ tín dụng");
        System.out.println("   - Bill ID: " + unpaidBill.getBillId());
        System.out.println("   - Đã thanh toán: " + unpaidBill.isPaid());
        System.out.println("   - Thời gian thanh toán: " + unpaidBill.getPaymentTime());
        
        // Test 3: Tạo Bill từ Room object
        System.out.println("\n3. Tạo Bill từ Room object:");
        Bill roomBill = com.hotelsystem.utils.BillBuilderUtil.createBillFromRoom(testRoom, 1100000, "Chuyển khoản");
        System.out.println("   - Bill ID: " + roomBill.getBillId());
        System.out.println("   - Phòng: " + roomBill.getRoomInfo().getRoom().getRoomNumber());
        System.out.println("   - Số tiền: " + String.format("%,.0f", roomBill.getTotalAmount()) + " VND");
        
        // Test 4: Tạo Bill với ID tùy chỉnh
        System.out.println("\n4. Tạo Bill với ID tùy chỉnh:");
        Bill customIdBill = com.hotelsystem.utils.BillBuilderUtil.createBillWithCustomId(
            "BILL-2024-001", mockRoomInfo, 1200000, "Tiền mặt");
        System.out.println("   - Bill ID: " + customIdBill.getBillId());
        System.out.println("   - Số tiền: " + String.format("%,.0f", customIdBill.getTotalAmount()) + " VND");
    }
} 