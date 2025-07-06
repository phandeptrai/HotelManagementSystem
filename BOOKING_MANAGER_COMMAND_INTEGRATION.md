# Tích Hợp Command Pattern Vào BookingManager

## Tổng Quan

`BookingManager` đã được cập nhật để sử dụng Command pattern thay vì thao tác trực tiếp với phòng. Điều này mang lại nhiều lợi ích về tính linh hoạt, khả năng mở rộng và quản lý lệnh.

## Thay Đổi Chính

### 1. Import Command Classes
```java
import com.hotelsystem.command.room.*;
```

### 2. Thêm ReservationInvoker
```java
private final ReservationInvoker invoker;

private BookingManager() {
    bookings = new ArrayList<>();
    invoker = new ReservationInvoker();
}
```

### 3. Cập Nhật Các Method Hiện Tại

#### bookRoom() - Đặt phòng
```java
// Trước
room.checkIn(user);

// Sau
CheckInCommand checkInCommand = new CheckInCommand(room, user);
invoker.executeCommand(checkInCommand);
```

#### checkOut() - Trả phòng
```java
// Trước
room.checkOut(user);

// Sau
CheckOutCommand checkOutCommand = new CheckOutCommand(room, user);
invoker.executeCommand(checkOutCommand);
```

### 4. Thêm Các Method Mới

#### reserveRoom() - Đặt phòng (Reserve)
```java
public boolean reserveRoom(Room room, User user) {
    if (!room.isAvailable()) {
        System.out.println("❌ Phòng không khả dụng!");
        return false;
    }

    // Sử dụng Command pattern
    ReserveRoomCommand reserveCommand = new ReserveRoomCommand(room, user);
    invoker.executeCommand(reserveCommand);
    
    // Tạo lịch sử booking
    UserHistory history = new UserHistory(
        user.getId(),
        room.getRoomNumber(),
        LocalDateTime.now(),
        null,
        room.getCost()
    );
    bookings.add(history);
    System.out.println("✅ Đã đặt phòng " + room.getRoomNumber() + " cho " + user.getName());
    return true;
}
```

#### cancelReservation() - Hủy đặt phòng
```java
public boolean cancelReservation(Room room, User user) {
    // Sử dụng Command pattern
    CancelReservationCommand cancelCommand = new CancelReservationCommand(room, user);
    invoker.executeCommand(cancelCommand);
    
    // Xóa booking khỏi danh sách
    bookings.removeIf(history -> 
        history.getUserId() == user.getId() && 
        history.getRoomNumber().equals(room.getRoomNumber()) &&
        history.getCheckOutTime() == null
    );
    
    System.out.println("✅ Đã hủy đặt phòng " + room.getRoomNumber() + " cho " + user.getName());
    return true;
}
```

#### startMaintenance() / finishMaintenance() - Bảo trì phòng
```java
public void startMaintenance(Room room, User user) {
    MaintenanceCommand maintenanceCommand = new MaintenanceCommand(room, user, true);
    invoker.executeCommand(maintenanceCommand);
    System.out.println("🔧 Đã bắt đầu bảo trì phòng " + room.getRoomNumber());
}

public void finishMaintenance(Room room, User user) {
    MaintenanceCommand maintenanceCommand = new MaintenanceCommand(room, user, false);
    invoker.executeCommand(maintenanceCommand);
    System.out.println("✅ Đã kết thúc bảo trì phòng " + room.getRoomNumber());
}
```

### 5. Thêm Undo/Redo Methods

#### Quản lý lệnh
```java
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
```

#### Thống kê lệnh
```java
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
```

## Cập Nhật HotelManagementSystem

### 1. Menu Quản Lý Đặt Phòng
- Thêm menu "Bảo trì phòng"
- Thêm menu "Quản lý lệnh (Undo/Redo)"

### 2. Method bookRoom()
- Đơn giản hóa để sử dụng Command pattern
- Loại bỏ logic phức tạp về thanh toán và dịch vụ

### 3. Thêm Các Menu Mới

#### maintenanceMenu()
```java
private static void maintenanceMenu() {
    // Menu bảo trì phòng
    // 1. Bắt đầu bảo trì
    // 2. Kết thúc bảo trì
}
```

#### commandManagementMenu()
```java
private static void commandManagementMenu() {
    // Menu quản lý lệnh
    // 1. Undo
    // 2. Redo
    // 3. Xem lịch sử lệnh
    // 4. Xem lịch sử hoàn tác
    // 5. Xóa lịch sử lệnh
    // 6. Thống kê lệnh
}
```

## Lợi Ích Của Việc Tích Hợp

### 1. Tính Linh Hoạt
- Dễ dàng thêm các lệnh mới
- Có thể thay đổi cách thực hiện lệnh mà không ảnh hưởng đến code gọi

### 2. Khả Năng Mở Rộng
- Thêm undo/redo cho tất cả các thao tác
- Lưu trữ lịch sử lệnh
- Thống kê và báo cáo lệnh

### 3. Quản Lý Lệnh
- Theo dõi lịch sử lệnh
- Hoàn tác và làm lại lệnh
- Xóa lịch sử khi cần

### 4. Tích Hợp Với Observer Pattern
- Tất cả các lệnh đều tự động thông báo thay đổi
- UI được cập nhật tự động

### 5. Tích Hợp Với State Pattern
- Các lệnh tương tác với State pattern
- Quản lý trạng thái phòng một cách nhất quán

## Cách Sử Dụng

### 1. Đặt Phòng
```java
bookingManager.reserveRoom(room, user);
```

### 2. Check-in
```java
bookingManager.bookRoom(room, user);
```

### 3. Check-out
```java
bookingManager.checkOut(room, user);
```

### 4. Hủy Đặt Phòng
```java
bookingManager.cancelReservation(room, user);
```

### 5. Bảo Trì
```java
bookingManager.startMaintenance(room, adminUser);
bookingManager.finishMaintenance(room, adminUser);
```

### 6. Quản Lý Lệnh
```java
// Hoàn tác
bookingManager.undoLastCommand();

// Làm lại
bookingManager.redoLastCommand();

// Xem lịch sử
bookingManager.showCommandHistory();

// Thống kê
int commandCount = bookingManager.getCommandCount();
```

## Kết Luận

Việc tích hợp Command pattern vào `BookingManager` đã tạo ra một hệ thống quản lý đặt phòng mạnh mẽ, linh hoạt và dễ mở rộng. Hệ thống hiện tại hỗ trợ đầy đủ các chức năng:

- Đặt phòng, check-in, check-out
- Hủy đặt phòng
- Bảo trì phòng
- Undo/Redo lệnh
- Theo dõi lịch sử lệnh
- Tích hợp với Observer và State pattern

Tất cả các thao tác đều được thực hiện thông qua Command pattern, đảm bảo tính nhất quán và khả năng mở rộng trong tương lai. 