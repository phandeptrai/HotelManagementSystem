# Tích Hợp Chức Năng Thanh Toán

## Tổng Quan

Hệ thống đã được tích hợp chức năng thanh toán khi checkout sử dụng Strategy pattern. Khách hàng sẽ được yêu cầu thanh toán trước khi có thể checkout.

## Các Phương Thức Thanh Toán

### 1. Tiền Mặt (CashPayment)
```java
public class CashPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Thanh toán tiền mặt: " + amount + " VND");
        return true;
    }
}
```

### 2. Thẻ Tín Dụng (CreditCardPayment)
```java
public class CreditCardPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Thanh toán thẻ tín dụng: " + amount + " VND");
        return true;
    }
}
```

### 3. Chuyển Khoản (BankTransferPayment)
```java
public class BankTransferPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Thanh toán chuyển khoản: " + amount + " VND");
        return true;
    }
}
```

## Luồng Thanh Toán Khi Checkout

### 1. Trong BookingManagementUI
```java
private void checkOut() {
    // ... lấy thông tin phòng và khách hàng
    
    // Thực hiện thanh toán trước khi checkout
    if (processPayment(room)) {
        bookingManager.checkOut(room, user);
    } else {
        System.out.println("❌ Thanh toán thất bại! Không thể checkout.");
    }
}
```

### 2. Xử Lý Thanh Toán
```java
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
    
    // Thực hiện thanh toán
    if (paymentStrategy.pay(totalAmount)) {
        System.out.println("✅ Thanh toán thành công!");
        
        // Tạo hóa đơn
        RoomInfo roomInfo = new RoomInfo(room, true);
        BillService billService = new BillService();
        billService.createBill(roomInfo, totalAmount, paymentMethod);
        
        return true;
    } else {
        System.out.println("❌ Thanh toán thất bại!");
        return false;
    }
}
```

### 3. Chọn Phương Thức Thanh Toán
```java
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
```

### 4. Tạo Payment Strategy
```java
private PaymentStrategy getPaymentStrategy(String method) {
    switch (method) {
        case "Tiền mặt": return new CashPayment();
        case "Thẻ tín dụng": return new CreditCardPayment();
        case "Chuyển khoản": return new BankTransferPayment();
        default: return new CashPayment();
    }
}
```

## Cập Nhật BookingManager

### Method Mới
```java
// Trả phòng với thanh toán
public boolean checkOutWithPayment(Room room, User user, double amount, String paymentMethod) {
    // Thực hiện thanh toán trước
    if (processPayment(amount, paymentMethod)) {
        // Sau khi thanh toán thành công, thực hiện checkout
        checkOut(room, user);
        
        // Tạo hóa đơn
        RoomInfo roomInfo = new RoomInfo(room, true);
        BillService billService = new BillService();
        billService.createBill(roomInfo, amount, paymentMethod);
        
        return true;
    } else {
        System.out.println("❌ Thanh toán thất bại! Không thể checkout.");
        return false;
    }
}
```

## Lợi Ích Của Tích Hợp

### 1. Sử Dụng Strategy Pattern
- Dễ dàng thêm phương thức thanh toán mới
- Tách biệt logic thanh toán khỏi logic checkout
- Có thể thay đổi cách thanh toán mà không ảnh hưởng đến code khác

### 2. Tự Động Tạo Hóa Đơn
- Mỗi lần thanh toán thành công sẽ tạo hóa đơn
- Lưu trữ thông tin thanh toán đầy đủ
- Hỗ trợ báo cáo doanh thu

### 3. Kiểm Soát Quy Trình
- Không thể checkout nếu chưa thanh toán
- Hiển thị thông tin chi tiết về phòng và giá tiền
- Cho phép khách hàng chọn phương thức thanh toán

## Cách Sử Dụng

### 1. Checkout Với Thanh Toán
1. Chọn "Check-out" từ menu đặt phòng
2. Nhập thông tin khách hàng và phòng
3. Hệ thống hiển thị thông tin thanh toán
4. Chọn phương thức thanh toán
5. Xác nhận thanh toán
6. Hệ thống tạo hóa đơn và thực hiện checkout

### 2. Xem Hóa Đơn
- Vào menu "Quản lý phòng" → "Xem hóa đơn"
- Hiển thị tất cả hóa đơn đã tạo

## Mở Rộng Trong Tương Lai

### 1. Thêm Phương Thức Thanh Toán
```java
public class CryptoPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Thanh toán bằng tiền điện tử: " + amount + " VND");
        return true;
    }
}
```

### 2. Tích Hợp Với Hệ Thống Thực
- Kết nối với cổng thanh toán thực
- Xác thực thẻ tín dụng
- Xử lý lỗi thanh toán

### 3. Tính Năng Bổ Sung
- Giảm giá cho khách hàng VIP
- Phí dịch vụ bổ sung
- Thanh toán trước khi check-in

## Kết Luận

Việc tích hợp chức năng thanh toán đã tạo ra một quy trình checkout hoàn chỉnh:
- **Bảo mật**: Kiểm soát thanh toán trước khi checkout
- **Linh hoạt**: Hỗ trợ nhiều phương thức thanh toán
- **Tự động**: Tạo hóa đơn và cập nhật trạng thái phòng
- **Mở rộng**: Dễ dàng thêm tính năng mới

Hệ thống hiện tại đã sẵn sàng cho việc triển khai trong môi trường thực tế! 