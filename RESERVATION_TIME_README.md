# Cải Tiến Quản Lý Thời Gian Đặt Phòng

## Vấn Đề Đã Giải Quyết

### 1. Thời Gian Không Phân Biệt
**Vấn đề:** Trước đây, khi đặt phòng (reserve), chúng ta lưu thời gian check-in ngay lập tức, không phân biệt giữa thời gian đặt phòng và thời gian check-in.

**Giải pháp:** Thêm field `reservationTime` để phân biệt rõ ràng:
- `reservationTime`: Thời gian đặt phòng
- `checkInTime`: Thời gian check-in thực tế
- `checkOutTime`: Thời gian check-out

## Các Thay Đổi Chính

### 1. UserHistory.java

#### Thêm Field Mới
```java
private LocalDateTime reservationTime; // Thời gian đặt phòng
private LocalDateTime checkInTime;     // Thời gian check-in
private LocalDateTime checkOutTime;    // Thời gian check-out
```

#### Constructor Mới
```java
public UserHistory(int id, String roomNumber, LocalDateTime reservationTime, 
                  LocalDateTime checkin, LocalDateTime checkout, double totalCost) {
    this.userId = id;
    this.roomNumber = roomNumber;
    this.reservationTime = reservationTime;
    this.checkInTime = checkin;
    this.checkOutTime = checkout;
    this.totalCost = totalCost;
}
```

#### Getter/Setter Mới
```java
public LocalDateTime getReservationTime() {
    return reservationTime;
}

public void setReservationTime(LocalDateTime reservationTime) {
    this.reservationTime = reservationTime;
}
```

### 2. BookingManager.java

#### Method `reserveRoom()` - Đặt Phòng
```java
// Tạo lịch sử với thời gian đặt phòng
UserHistory history = new UserHistory(
    user.getId(),
    room.getRoomNumber(),
    LocalDateTime.now(), // Thời gian đặt phòng
    null,               // Thời gian check-in (chưa có)
    null,               // Thời gian check-out (chưa có)
    room.getCost()
);
```

#### Method `bookRoom()` - Check-in Trực Tiếp
```java
// Tạo lịch sử với thời gian check-in (đặt phòng và check-in cùng lúc)
UserHistory history = new UserHistory(
    user.getId(),
    room.getRoomNumber(),
    LocalDateTime.now(), // Thời gian đặt phòng (cùng lúc với check-in)
    LocalDateTime.now(), // Thời gian check-in
    null,               // Thời gian check-out (chưa có)
    room.getCost()
);
```

#### Method `checkInReservedRoom()` - Check-in Phòng Đã Đặt
```java
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
```

#### Method `checkOut()` - Cập Nhật Logic
```java
// Cập nhật lịch sử booking - tìm booking đã check-in
for (UserHistory history : bookings) {
    if (history.getUserId() == user.getId()
        && history.getRoomNumber().equals(room.getRoomNumber())
        && history.getCheckInTime() != null  // Đã check-in
        && history.getCheckOutTime() == null) { // Chưa check-out
        history.setCheckOutTime(LocalDateTime.now());
        // ...
    }
}
```

#### Method `cancelReservation()` - Cập Nhật Logic
```java
// Xóa booking khỏi danh sách - chỉ xóa những booking chưa check-in
bookings.removeIf(history -> 
    history.getUserId() == user.getId() && 
    history.getRoomNumber().equals(room.getRoomNumber()) &&
    history.getCheckInTime() == null &&  // Chưa check-in
    history.getCheckOutTime() == null    // Chưa check-out
);
```

#### Method `displayBookings()` - Hiển Thị Đầy Đủ
```java
System.out.println("UserID: " + history.getUserId() +
                   ", Room: " + history.getRoomNumber() +
                   ", Đặt phòng: " + (history.getReservationTime() != null ? history.getReservationTime() : "N/A") +
                   ", Check-in: " + (history.getCheckInTime() != null ? history.getCheckInTime() : "Chưa check-in") +
                   ", Check-out: " + (history.getCheckOutTime() != null ? history.getCheckOutTime() : "Chưa trả"));
```

## Luồng Hoạt Động Mới

### 1. Đặt Phòng (Reserve)
1. User đặt phòng → `reserveRoom()`
2. Tạo `UserHistory` với:
   - `reservationTime`: Thời gian hiện tại
   - `checkInTime`: null (chưa check-in)
   - `checkOutTime`: null (chưa check-out)
3. Phòng chuyển sang trạng thái "Reserved"

### 2. Check-in Phòng Đã Đặt
1. User check-in phòng đã đặt → `checkInReservedRoom()`
2. Tìm booking có `checkInTime` = null
3. Cập nhật `checkInTime` = thời gian hiện tại
4. Phòng chuyển sang trạng thái "Occupied"

### 3. Check-in Trực Tiếp
1. User check-in phòng trống → `bookRoom()`
2. Tạo `UserHistory` với:
   - `reservationTime`: Thời gian hiện tại
   - `checkInTime`: Thời gian hiện tại (cùng lúc)
   - `checkOutTime`: null
3. Phòng chuyển sang trạng thái "Occupied"

### 4. Check-out
1. User check-out → `checkOut()`
2. Tìm booking có `checkInTime` ≠ null và `checkOutTime` = null
3. Cập nhật `checkOutTime` = thời gian hiện tại
4. Phòng chuyển sang trạng thái "Available"

### 5. Hủy Đặt Phòng
1. User hủy đặt phòng → `cancelReservation()`
2. Chỉ xóa booking có `checkInTime` = null (chưa check-in)
3. Phòng chuyển sang trạng thái "Available"

## Lợi Ích

### 1. Theo Dõi Chính Xác
- Phân biệt rõ thời gian đặt phòng và check-in
- Có thể tính thời gian chờ từ đặt phòng đến check-in
- Theo dõi được booking chưa check-in

### 2. Báo Cáo Chi Tiết
- Hiển thị đầy đủ thông tin thời gian
- Có thể phân tích hiệu suất đặt phòng
- Theo dõi được tỷ lệ đặt phòng vs check-in

### 3. Quản Lý Tốt Hơn
- Không thể check-out phòng chưa check-in
- Không thể hủy phòng đã check-in
- Logic nhất quán và an toàn

## Ví Dụ Sử Dụng

### Scenario 1: Đặt Phòng Trước
```
1. User đặt phòng Single001 → reservationTime: 2024-01-15 10:00
2. User check-in sau 2 giờ → checkInTime: 2024-01-15 12:00
3. User check-out → checkOutTime: 2024-01-16 10:00
```

### Scenario 2: Check-in Trực Tiếp
```
1. User check-in phòng Double001 → reservationTime: 2024-01-15 14:00, checkInTime: 2024-01-15 14:00
2. User check-out → checkOutTime: 2024-01-16 12:00
```

### Scenario 3: Hủy Đặt Phòng
```
1. User đặt phòng Suite001 → reservationTime: 2024-01-15 16:00
2. User hủy đặt phòng → booking bị xóa
```

## Kết Luận

Những cải tiến này giúp hệ thống:
- Quản lý thời gian chính xác và chi tiết hơn
- Phân biệt rõ các giai đoạn của booking
- Cung cấp báo cáo và phân tích tốt hơn
- Đảm bảo logic nghiệp vụ chính xác 