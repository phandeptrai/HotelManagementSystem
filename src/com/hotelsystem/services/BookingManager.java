package com.hotelsystem.services;

import com.hotelsystem.room.Room;
import com.hotelsystem.room.RoomInfo;
import com.hotelsystem.user.User;
import com.hotelsystem.user.UserHistory;
import com.hotelsystem.command.room.*;
import com.hotelsystem.services.strategy.*;
import com.hotelsystem.builder.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private static BookingManager instance;
    private final List<UserHistory> bookings;
    private final ReservationInvoker invoker;

    private BookingManager() {
        bookings = new ArrayList<>();
        invoker = new ReservationInvoker();
    }

    public static BookingManager getInstance() {
        if (instance == null) {
            instance = new BookingManager();
        }
        return instance;
    }

    // Đặt phòng (check-in từ trạng thái Available)
    public boolean bookRoom(Room room, User user) {
        // Kiểm tra trạng thái phòng trước
        String roomState = room.getCurrentState().getStateName();
        if (!roomState.equals("Available")) {
            System.out.println("❌ Phòng " + room.getRoomNumber() + " không khả dụng! Trạng thái: " + roomState);
            return false;
        }

        // Sử dụng Command pattern
        CheckInCommand checkInCommand = new CheckInCommand(room, user);
        invoker.executeCommand(checkInCommand);
        
        // Tạo lịch sử với thời gian check-in (đặt phòng và check-in cùng lúc)
        UserHistory history = new UserHistory(
            user.getId(),
            room.getRoomNumber(),
            LocalDateTime.now(), // Thời gian đặt phòng (cùng lúc với check-in)
            LocalDateTime.now(), // Thời gian check-in
            null,               // Thời gian check-out (chưa có)
            room.getCost()
        );
        bookings.add(history);
        System.out.println("✅ Đã đặt phòng và check-in " + room.getRoomNumber() + " cho " + user.getName());
        return true;
    }
    
    // Check-in phòng đã đặt (từ trạng thái Reserved)
    public boolean checkInReservedRoom(Room room, User user) {
        if (!room.getCurrentState().getStateName().equals("Reserved")) {
            System.out.println("❌ Phòng không ở trạng thái đã đặt!");
            return false;
        }

        // Sử dụng Command pattern
        CheckInCommand checkInCommand = new CheckInCommand(room, user);
        invoker.executeCommand(checkInCommand);
        
        // Cập nhật lịch sử booking - tìm booking đã đặt và cập nhật thời gian check-in
        boolean found = false;
        for (UserHistory history : bookings) {
            if (history.getUserId() == user.getId()
                && history.getRoomNumber().equals(room.getRoomNumber())
                && history.getCheckOutTime() == null
                && history.getCheckInTime() == null) { // Chỉ cập nhật nếu chưa check-in
                history.setCheckInTime(LocalDateTime.now());
                found = true;
                System.out.println("✅ Đã cập nhật thời gian check-in cho booking đã đặt");
                break;
            }
        }
        
        if (!found) {
            System.out.println("⚠️ Không tìm thấy booking để cập nhật check-in");
        }
        
        System.out.println("✅ Đã check-in phòng " + room.getRoomNumber() + " cho " + user.getName());
        return true;
    }

    // Trả phòng (check-out)
    public void checkOut(Room room, User user) {
        // Sử dụng Command pattern
        CheckOutCommand checkOutCommand = new CheckOutCommand(room, user);
        invoker.executeCommand(checkOutCommand);
        
        // Cập nhật lịch sử booking - tìm booking đã check-in
        for (UserHistory history : bookings) {
            if (history.getUserId() == user.getId()
                && history.getRoomNumber().equals(room.getRoomNumber())
                && history.getCheckInTime() != null  // Đã check-in
                && history.getCheckOutTime() == null) { // Chưa check-out

                history.setCheckOutTime(LocalDateTime.now());
                System.out.println("🏁 " + user.getName() + " đã trả phòng " + room.getRoomNumber());
                return;
            }
        }
        System.out.println("⚠️ Không tìm thấy lịch sử check-in để check-out.");
    }
    
    // Trả phòng với thanh toán
    public boolean checkOutWithPayment(Room room, User user, double amount, PaymentStrategy paymentStrategy) {
        // Thực hiện thanh toán
        if (paymentStrategy.pay(amount)) {
            // Sau khi thanh toán thành công, thực hiện checkout
            checkOut(room, user);
            
            // Tạo hóa đơn
            RoomInfo roomInfo = new RoomInfo(room, true);
            BillService billService = new BillService();
            billService.createBill(roomInfo, amount, paymentStrategy.getPaymentMethodName());
            
            return true;
        } else {
            System.out.println("❌ Thanh toán thất bại! Không thể checkout.");
            return false;
        }
    }
    
    private boolean processPayment(double amount, String paymentMethod) {
        PaymentStrategy paymentStrategy = getPaymentStrategy(paymentMethod);
        return paymentStrategy.pay(amount);
    }
    
    private PaymentStrategy getPaymentStrategy(String method) {
        switch (method) {
            case "Tiền mặt": return new CashPayment();
            case "Thẻ tín dụng": return new CreditCardPayment();
            case "Chuyển khoản": return new BankTransferPayment();
            default: return new CashPayment();
        }
    }

    // Lấy danh sách booking hiện tại
    public List<UserHistory> getBookings() {
        return bookings;
    }

    // In danh sách tất cả booking
    public void displayBookings() {
        System.out.println("\n=== DANH SÁCH BOOKING ===");
        for (UserHistory history : bookings) {
            System.out.println("UserID: " + history.getUserId() +
                               ", Room: " + history.getRoomNumber() +
                               ", Đặt phòng: " + (history.getReservationTime() != null ? history.getReservationTime() : "N/A") +
                               ", Check-in: " + (history.getCheckInTime() != null ? history.getCheckInTime() : "Chưa check-in") +
                               ", Check-out: " + (history.getCheckOutTime() != null ? history.getCheckOutTime() : "Chưa trả"));
        }
    }
    
    // ========== COMMAND PATTERN METHODS ==========
    
    // Đặt phòng (reserve)
    public boolean reserveRoom(Room room, User user) {
        // Kiểm tra trạng thái phòng trước
        String roomState = room.getCurrentState().getStateName();
        if (!roomState.equals("Available")) {
            System.out.println("❌ Phòng " + room.getRoomNumber() + " không khả dụng! Trạng thái: " + roomState);
            return false;
        }

        // Sử dụng Command pattern
        ReserveRoomCommand reserveCommand = new ReserveRoomCommand(room, user);
        invoker.executeCommand(reserveCommand);
        
        // Tạo lịch sử với thời gian đặt phòng
        UserHistory history = new UserHistory(
            user.getId(),
            room.getRoomNumber(),
            LocalDateTime.now(), // Thời gian đặt phòng
            null,               // Thời gian check-in (chưa có)
            null,               // Thời gian check-out (chưa có)
            room.getCost()
        );
        bookings.add(history);
        System.out.println("✅ Đã đặt phòng " + room.getRoomNumber() + " cho " + user.getName());
        return true;
    }
    
    // Hủy đặt phòng
    public boolean cancelReservation(Room room, User user) {
        // Kiểm tra trạng thái phòng trước
        String roomState = room.getCurrentState().getStateName();
        if (roomState.equals("Occupied")) {
            System.out.println("❌ Phòng " + room.getRoomNumber() + " đang có khách, không thể hủy đặt phòng!");
            return false;
        }
        
        if (roomState.equals("Available")) {
            System.out.println("❌ Phòng " + room.getRoomNumber() + " chưa được đặt, không thể hủy!");
            return false;
        }
        
        // Chỉ hủy khi phòng ở trạng thái Reserved
        if (roomState.equals("Reserved")) {
            // Sử dụng Command pattern
            CancelReservationCommand cancelCommand = new CancelReservationCommand(room, user);
            invoker.executeCommand(cancelCommand);
            
            // Xóa booking khỏi danh sách - chỉ xóa những booking chưa check-in
            bookings.removeIf(history -> 
                history.getUserId() == user.getId() && 
                history.getRoomNumber().equals(room.getRoomNumber()) &&
                history.getCheckInTime() == null &&  // Chưa check-in
                history.getCheckOutTime() == null    // Chưa check-out
            );
            
            System.out.println("✅ Đã hủy đặt phòng " + room.getRoomNumber() + " cho " + user.getName());
            return true;
        }
        
        System.out.println("❌ Không thể hủy đặt phòng ở trạng thái: " + roomState);
        return false;
    }
    
    // Bắt đầu bảo trì
    public void startMaintenance(Room room, User user) {
        // Sử dụng Command pattern
        MaintenanceCommand maintenanceCommand = new MaintenanceCommand(room, user, true);
        invoker.executeCommand(maintenanceCommand);
        System.out.println("🔧 Đã bắt đầu bảo trì phòng " + room.getRoomNumber());
    }
    
    // Kết thúc bảo trì
    public void finishMaintenance(Room room, User user) {
        // Sử dụng Command pattern
        MaintenanceCommand maintenanceCommand = new MaintenanceCommand(room, user, false);
        invoker.executeCommand(maintenanceCommand);
        System.out.println("✅ Đã kết thúc bảo trì phòng " + room.getRoomNumber());
    }
    
    // ========== UNDO/REDO METHODS ==========
    
    // Hoàn tác lệnh cuối cùng
    public void undoLastCommand() {
        invoker.undo();
    }
    
    // Làm lại lệnh cuối cùng
    public void redoLastCommand() {
        invoker.redo();
    }
    
    // Hiển thị lịch sử lệnh
    public void showCommandHistory() {
        invoker.showCommandHistory();
    }
    
    // Hiển thị lịch sử hoàn tác
    public void showUndoHistory() {
        invoker.showUndoHistory();
    }
    
    // Xóa lịch sử lệnh
    public void clearCommandHistory() {
        invoker.clearHistory();
    }
    
    // Lấy số lượng lệnh đã thực hiện
    public int getCommandCount() {
        return invoker.getCommandCount();
    }
    
    // Lấy số lượng lệnh có thể làm lại
    public int getUndoCount() {
        return invoker.getUndoCount();
    }
    
    // Kiểm tra có lệnh để hoàn tác không
    public boolean hasCommandsToUndo() {
        return invoker.hasCommands();
    }
    
    // Kiểm tra có lệnh để làm lại không
    public boolean hasCommandsToRedo() {
        return invoker.hasUndoCommands();
    }
}
