package com.hotelsystem.testde;

import com.hotelsystem.room.Room;
import com.hotelsystem.room.SingleRoom;
import com.hotelsystem.user.User;
import com.hotelsystem.command.room.*;
import com.hotelsystem.services.RoomMockData;
import com.hotelsystem.services.UserMockData;

public class CommandStateDemo {
    
    public static void main(String[] args) {
        System.out.println("=== DEMO COMMAND PATTERN VỚI STATE PATTERN ===");
        
        // Khởi tạo dữ liệu
        Room room = new SingleRoom("Demo001");
        User user = new User(1, "Nguyễn Văn Demo", "0123456789", "demo@email.com");
        
        // Tạo invoker
        ReservationInvoker invoker = new ReservationInvoker();
        
        System.out.println("🏨 Phòng ban đầu: " + room.getDescription());
        System.out.println("👤 Khách hàng: " + user.getName());
        System.out.println();
        
        // Demo 1: Đặt phòng
        System.out.println("=== DEMO 1: ĐẶT PHÒNG ===");
        ReserveRoomCommand reserveCommand = new ReserveRoomCommand(room, user);
        invoker.executeCommand(reserveCommand);
        System.out.println("Trạng thái sau đặt phòng: " + room.getCurrentState().getStateName());
        System.out.println();
        
        // Demo 2: Check-in
        System.out.println("=== DEMO 2: CHECK-IN ===");
        CheckInCommand checkInCommand = new CheckInCommand(room, user);
        invoker.executeCommand(checkInCommand);
        System.out.println("Trạng thái sau check-in: " + room.getCurrentState().getStateName());
        System.out.println();
        
        // Demo 3: Check-out
        System.out.println("=== DEMO 3: CHECK-OUT ===");
        CheckOutCommand checkOutCommand = new CheckOutCommand(room, user);
        invoker.executeCommand(checkOutCommand);
        System.out.println("Trạng thái sau check-out: " + room.getCurrentState().getStateName());
        System.out.println();
        
        // Demo 4: Bảo trì
        System.out.println("=== DEMO 4: BẢO TRÌ ===");
        MaintenanceCommand maintenanceCommand = new MaintenanceCommand(room, user, true);
        invoker.executeCommand(maintenanceCommand);
        System.out.println("Trạng thái sau bảo trì: " + room.getCurrentState().getStateName());
        System.out.println();
        
        // Demo 5: Kết thúc bảo trì
        System.out.println("=== DEMO 5: KẾT THÚC BẢO TRÌ ===");
        MaintenanceCommand finishMaintenanceCommand = new MaintenanceCommand(room, user, false);
        invoker.executeCommand(finishMaintenanceCommand);
        System.out.println("Trạng thái sau kết thúc bảo trì: " + room.getCurrentState().getStateName());
        System.out.println();
        
        // Demo 6: Hiển thị lịch sử lệnh
        System.out.println("=== DEMO 6: LỊCH SỬ LỆNH ===");
        invoker.showCommandHistory();
        System.out.println();
        
        // Demo 7: Undo các lệnh
        System.out.println("=== DEMO 7: HOÀN TÁC LỆNH ===");
        System.out.println("Hoàn tác lệnh cuối cùng...");
        invoker.undo();
        System.out.println("Trạng thái sau hoàn tác: " + room.getCurrentState().getStateName());
        System.out.println();
        
        System.out.println("Hoàn tác lệnh thứ 2...");
        invoker.undo();
        System.out.println("Trạng thái sau hoàn tác: " + room.getCurrentState().getStateName());
        System.out.println();
        
        // Demo 8: Redo các lệnh
        System.out.println("=== DEMO 8: LÀM LẠI LỆNH ===");
        System.out.println("Làm lại lệnh...");
        invoker.redo();
        System.out.println("Trạng thái sau làm lại: " + room.getCurrentState().getStateName());
        System.out.println();
        
        // Demo 9: Hiển thị lịch sử hoàn tác
        System.out.println("=== DEMO 9: LỊCH SỬ HOÀN TÁC ===");
        invoker.showUndoHistory();
        System.out.println();
        
        // Demo 10: Test với nhiều khách hàng
        System.out.println("=== DEMO 10: TEST VỚI NHIỀU KHÁCH HÀNG ===");
        User user2 = new User(2, "Trần Thị Demo", "0987654321", "demo2@email.com");
        
        ReserveRoomCommand reserveCommand2 = new ReserveRoomCommand(room, user2);
        invoker.executeCommand(reserveCommand2);
        System.out.println("Đặt phòng cho khách thứ 2: " + user2.getName());
        System.out.println("Trạng thái: " + room.getCurrentState().getStateName());
        System.out.println();
        
        // Demo 11: Hủy đặt phòng
        System.out.println("=== DEMO 11: HỦY ĐẶT PHÒNG ===");
        CancelReservationCommand cancelCommand = new CancelReservationCommand(room, user2);
        invoker.executeCommand(cancelCommand);
        System.out.println("Trạng thái sau hủy đặt phòng: " + room.getCurrentState().getStateName());
        System.out.println();
        
        // Demo 12: Thống kê cuối cùng
        System.out.println("=== DEMO 12: THỐNG KÊ CUỐI CÙNG ===");
        System.out.println("Tổng số lệnh đã thực hiện: " + invoker.getCommandCount());
        System.out.println("Số lệnh có thể làm lại: " + invoker.getUndoCount());
        System.out.println("Trạng thái phòng cuối: " + room.getCurrentState().getStateName());
        System.out.println("Khách hiện tại: " + (room.getCurrentUser() != null ? room.getCurrentUser().getName() : "Không có"));
        
        System.out.println("\n=== DEMO HOÀN THÀNH ===");
    }
} 