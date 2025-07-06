# Command Pattern với State Pattern trong Hệ Thống Quản Lý Khách Sạn

## 🎯 Tổng Quan

Command pattern được tích hợp với State pattern để quản lý các thao tác đặt phòng một cách có cấu trúc và có thể hoàn tác (undo/redo).

## 🏗️ Cấu Trúc Command Pattern

### 1. Interface ReservationCommand
```java
public interface ReservationCommand {
    void execute();
    void undo();
    String getDescription();
    User getUser();
    void setUser(User user);
}
```

### 2. Các Command Classes

#### **CheckInCommand**
- **Mục đích**: Thực hiện check-in phòng
- **State tương tác**: Available → Occupied
- **Observer**: UI Display, Email Notification

#### **CheckOutCommand**
- **Mục đích**: Thực hiện check-out phòng
- **State tương tác**: Occupied → Available
- **Observer**: UI Display, Email Notification

#### **ReserveRoomCommand**
- **Mục đích**: Đặt phòng
- **State tương tác**: Available → Reserved
- **Observer**: UI Display, Email Notification

#### **CancelReservationCommand**
- **Mục đích**: Hủy đặt phòng
- **State tương tác**: Reserved → Available
- **Observer**: UI Display, Email Notification

#### **MaintenanceCommand**
- **Mục đích**: Bắt đầu/kết thúc bảo trì phòng
- **State tương tác**: Any → Maintenance / Maintenance → Available
- **Observer**: UI Display, Email Notification

### 3. ReservationInvoker
```java
public class ReservationInvoker {
    private Stack<ReservationCommand> commandHistory = new Stack<>();
    private Stack<ReservationCommand> undoHistory = new Stack<>();
    
    public void executeCommand(ReservationCommand command);
    public void undo();
    public void redo();
    public void showCommandHistory();
    public void showUndoHistory();
}
```

## 🔄 Tương Tác với State Pattern

### Luồng Hoạt Động:

1. **Command được tạo** với Room và User
2. **Lưu trạng thái trước** để có thể undo
3. **Đăng ký Observer** (UI Display, Email Notification)
4. **Thực hiện command** thông qua State pattern
5. **Lưu vào history** để có thể undo/redo

### Ví Dụ CheckInCommand:
```java
@Override
public void execute() {
    // Store previous state for undo
    previousState = room.getCurrentState();
    
    // Register observers
    Observer uiDisplay = new UiDisplay();
    Observer emailObserver = new EmailNotificationObserver();
    room.registerObserver(uiDisplay);
    room.registerObserver(emailObserver);
    
    // Execute check-in using State pattern
    room.checkIn(user);
    
    System.out.println("✅ Đã thực hiện lệnh check-in: " + getDescription());
}
```

## 🎮 Tính Năng Undo/Redo

### Undo Operation:
```java
public void undo() {
    if (!commandHistory.isEmpty()) {
        ReservationCommand command = commandHistory.pop();
        command.undo();
        undoHistory.push(command);
        System.out.println("🔄 Đã hoàn tác lệnh: " + command.getDescription());
    }
}
```

### Redo Operation:
```java
public void redo() {
    if (!undoHistory.isEmpty()) {
        ReservationCommand command = undoHistory.pop();
        command.execute();
        commandHistory.push(command);
        System.out.println("🔄 Đã làm lại lệnh: " + command.getDescription());
    }
}
```

## 📊 Lịch Sử Command

### Hiển Thị Lịch Sử:
```java
public void showCommandHistory() {
    System.out.println("\n=== LỊCH SỬ LỆNH ===");
    for (int i = 0; i < commandHistory.size(); i++) {
        ReservationCommand cmd = commandHistory.get(i);
        User user = cmd.getUser();
        String userInfo = user != null ? " - " + user.getName() : "";
        System.out.println((i + 1) + ". " + cmd.getDescription() + userInfo);
    }
}
```

### Ví Dụ Output:
```
=== LỊCH SỬ LỆNH ===
📋 Danh sách lệnh đã thực hiện:
1. Đặt phòng Demo001 cho Nguyễn Văn Demo - Nguyễn Văn Demo
2. Check-in phòng Demo001 cho Nguyễn Văn Demo - Nguyễn Văn Demo
3. Check-out phòng Demo001 cho Nguyễn Văn Demo - Nguyễn Văn Demo
4. Bắt đầu bảo trì phòng Demo001 - Nguyễn Văn Demo
===================
```

## 🎯 Lợi Ích

### 1. **Tách Biệt Trách Nhiệm**
- **Command**: Chứa logic thực hiện và hoàn tác
- **State**: Quản lý trạng thái phòng
- **Invoker**: Quản lý lịch sử và thực thi

### 2. **Undo/Redo Hoàn Chỉnh**
- Lưu trữ trạng thái trước khi thực hiện
- Khôi phục trạng thái khi undo
- Theo dõi lịch sử thực hiện

### 3. **Tích Hợp Observer Pattern**
- Tự động đăng ký observer khi thực hiện command
- Thông báo thay đổi trạng thái
- Email notification cho khách hàng

### 4. **Mở Rộng Dễ Dàng**
- Thêm command mới không ảnh hưởng code hiện tại
- Thêm observer mới không cần sửa command
- Thêm state mới không cần sửa invoker

## 🚀 Cách Sử Dụng

### 1. Tạo Command:
```java
Room room = new SingleRoom("101");
User user = new User(1, "Nguyễn Văn A", "0123456789", "a@email.com");
CheckInCommand checkInCommand = new CheckInCommand(room, user);
```

### 2. Thực Hiện Command:
```java
ReservationInvoker invoker = new ReservationInvoker();
invoker.executeCommand(checkInCommand);
```

### 3. Hoàn Tác:
```java
invoker.undo();
```

### 4. Làm Lại:
```java
invoker.redo();
```

### 5. Xem Lịch Sử:
```java
invoker.showCommandHistory();
invoker.showUndoHistory();
```

## 🧪 Demo

Chạy `CommandStateDemo` để xem demo hoàn chỉnh:

```bash
javac -cp "src" src/com/hotelsystem/testde/CommandStateDemo.java
java -cp "src" com.hotelsystem.testde.CommandStateDemo
```

### Demo bao gồm:
1. ✅ Đặt phòng
2. ✅ Check-in
3. ✅ Check-out
4. ✅ Bảo trì
5. ✅ Kết thúc bảo trì
6. ✅ Hiển thị lịch sử
7. ✅ Undo/Redo
8. ✅ Test với nhiều khách hàng
9. ✅ Hủy đặt phòng

## 🔧 Tích Hợp với Hệ Thống

### Trong HotelManagementSystem:
```java
// Thay vì gọi trực tiếp
room.checkIn(user);

// Sử dụng Command pattern
CheckInCommand checkInCommand = new CheckInCommand(room, user);
invoker.executeCommand(checkInCommand);
```

### Lợi Ích:
- **Lịch sử đầy đủ**: Theo dõi tất cả thao tác
- **Hoàn tác an toàn**: Có thể undo bất kỳ lúc nào
- **Audit trail**: Lưu trữ thông tin chi tiết
- **User experience**: Giao diện thân thiện

## 📈 Kết Luận

Command pattern với State pattern tạo ra một hệ thống quản lý phòng mạnh mẽ với:

- **Tính linh hoạt**: Dễ dàng thêm command mới
- **Tính an toàn**: Undo/redo hoàn chỉnh
- **Tính mở rộng**: Tích hợp với Observer pattern
- **Tính bảo trì**: Code có cấu trúc rõ ràng

Hệ thống này đảm bảo mọi thao tác đều được ghi lại và có thể hoàn tác, tạo ra trải nghiệm người dùng tốt nhất! 🎉 