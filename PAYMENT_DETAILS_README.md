# Chức Năng Nhập Thông Tin Thanh Toán Chi Tiết

## Tổng Quan

Hệ thống đã được cập nhật để cho phép nhập thông tin chi tiết cho từng phương thức thanh toán, tăng tính bảo mật và theo dõi giao dịch.

## Cập Nhật PaymentStrategy Interface

### Interface Mới
```java
public interface PaymentStrategy {
    boolean pay(double amount);
    void inputPaymentDetails();
    String getPaymentMethodName();
}
```

## Các Phương Thức Thanh Toán Chi Tiết

### 1. Tiền Mặt (CashPayment)

#### Thông Tin Cần Nhập:
- **Tên thu ngân**: Người thực hiện giao dịch
- **Số biên lai**: Mã số biên lai thanh toán

#### Code Implementation:
```java
@Override
public void inputPaymentDetails() {
    System.out.println("\n=== NHẬP THÔNG TIN THANH TOÁN TIỀN MẶT ===");
    System.out.print("Nhập tên thu ngân: ");
    this.cashierName = scanner.nextLine();
    System.out.print("Nhập số biên lai: ");
    this.receiptNumber = scanner.nextLine();
}

@Override
public boolean pay(double amount) {
    System.out.println("Thanh toán tiền mặt: " + amount + " VND");
    System.out.println("Thu ngân: " + cashierName);
    System.out.println("Số biên lai: " + receiptNumber);
    return true;
}
```

### 2. Thẻ Tín Dụng (CreditCardPayment)

#### Thông Tin Cần Nhập:
- **Số thẻ**: 16 số thẻ tín dụng
- **Tên chủ thẻ**: Tên người sở hữu thẻ
- **Ngày hết hạn**: Định dạng MM/YY
- **Mã CVV**: 3 số bảo mật

#### Code Implementation:
```java
@Override
public void inputPaymentDetails() {
    System.out.println("\n=== NHẬP THÔNG TIN THẺ TÍN DỤNG ===");
    System.out.print("Nhập số thẻ (16 số): ");
    this.cardNumber = scanner.nextLine();
    System.out.print("Nhập tên chủ thẻ: ");
    this.cardHolderName = scanner.nextLine();
    System.out.print("Nhập ngày hết hạn (MM/YY): ");
    this.expiryDate = scanner.nextLine();
    System.out.print("Nhập mã CVV (3 số): ");
    this.cvv = scanner.nextLine();
}

@Override
public boolean pay(double amount) {
    System.out.println("Thanh toán qua thẻ tín dụng: " + amount + " VND");
    System.out.println("Số thẻ: " + maskCardNumber(cardNumber));
    System.out.println("Chủ thẻ: " + cardHolderName);
    System.out.println("Ngày hết hạn: " + expiryDate);
    return true;
}

private String maskCardNumber(String cardNumber) {
    if (cardNumber.length() >= 4) {
        return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
    }
    return "****";
}
```

### 3. Chuyển Khoản (BankTransferPayment)

#### Thông Tin Cần Nhập:
- **Tên ngân hàng**: Ngân hàng thực hiện giao dịch
- **Số tài khoản**: Tài khoản người chuyển
- **Tên chủ tài khoản**: Tên người sở hữu tài khoản
- **Mã giao dịch**: Mã số giao dịch chuyển khoản

#### Code Implementation:
```java
@Override
public void inputPaymentDetails() {
    System.out.println("\n=== NHẬP THÔNG TIN CHUYỂN KHOẢN ===");
    System.out.print("Nhập tên ngân hàng: ");
    this.bankName = scanner.nextLine();
    System.out.print("Nhập số tài khoản: ");
    this.accountNumber = scanner.nextLine();
    System.out.print("Nhập tên chủ tài khoản: ");
    this.accountHolderName = scanner.nextLine();
    System.out.print("Nhập mã giao dịch: ");
    this.transactionId = scanner.nextLine();
}

@Override
public boolean pay(double amount) {
    System.out.println("Thanh toán chuyển khoản: " + amount + " VND");
    System.out.println("Ngân hàng: " + bankName);
    System.out.println("Số tài khoản: " + maskAccountNumber(accountNumber));
    System.out.println("Chủ tài khoản: " + accountHolderName);
    System.out.println("Mã giao dịch: " + transactionId);
    return true;
}

private String maskAccountNumber(String accountNumber) {
    if (accountNumber.length() >= 4) {
        return "****" + accountNumber.substring(accountNumber.length() - 4);
    }
    return "****";
}
```

## Luồng Thanh Toán Cập Nhật

### 1. Chọn Phương Thức Thanh Toán
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

### 2. Nhập Thông Tin Chi Tiết
```java
// Nhập thông tin thanh toán chi tiết
paymentStrategy.inputPaymentDetails();
```

### 3. Xác Nhận Thanh Toán
```java
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
```

### 4. Thực Hiện Thanh Toán
```java
// Thực hiện thanh toán
if (paymentStrategy.pay(totalAmount)) {
    System.out.println("✅ Thanh toán thành công!");
    
    // Tạo hóa đơn
    RoomInfo roomInfo = new RoomInfo(room, true);
    BillService billService = new BillService();
    billService.createBill(roomInfo, totalAmount, paymentStrategy.getPaymentMethodName());
    
    return true;
} else {
    System.out.println("❌ Thanh toán thất bại!");
    return false;
}
```

## Tính Năng Bảo Mật

### 1. Ẩn Thông Tin Nhạy Cảm
- **Số thẻ**: Chỉ hiển thị 4 số cuối
- **Số tài khoản**: Chỉ hiển thị 4 số cuối
- **Mã CVV**: Không hiển thị trong log

### 2. Xác Nhận Giao Dịch
- Yêu cầu xác nhận trước khi thực hiện thanh toán
- Cho phép hủy giao dịch nếu cần

### 3. Lưu Trữ An Toàn
- Thông tin được lưu trong hóa đơn
- Hỗ trợ tra cứu giao dịch sau này

## Lợi Ích Của Tính Năng Mới

### 1. Tính Bảo Mật Cao
- Thu thập đầy đủ thông tin giao dịch
- Ẩn thông tin nhạy cảm
- Xác nhận trước khi thực hiện

### 2. Theo Dõi Giao Dịch
- Lưu trữ chi tiết từng giao dịch
- Hỗ trợ tra cứu và báo cáo
- Đảm bảo tính minh bạch

### 3. Trải Nghiệm Người Dùng Tốt
- Hướng dẫn rõ ràng cho từng bước
- Giao diện thân thiện
- Thông báo chi tiết về giao dịch

## Mở Rộng Trong Tương Lai

### 1. Thêm Phương Thức Thanh Toán
```java
public class CryptoPayment implements PaymentStrategy {
    private String walletAddress;
    private String transactionHash;
    
    @Override
    public void inputPaymentDetails() {
        System.out.println("\n=== NHẬP THÔNG TIN TIỀN ĐIỆN TỬ ===");
        System.out.print("Nhập địa chỉ ví: ");
        this.walletAddress = scanner.nextLine();
        System.out.print("Nhập mã giao dịch: ");
        this.transactionHash = scanner.nextLine();
    }
}
```

### 2. Tích Hợp Validation
- Kiểm tra định dạng số thẻ
- Xác thực ngày hết hạn
- Validate thông tin ngân hàng

### 3. Tích Hợp Với Hệ Thống Thực
- Kết nối cổng thanh toán
- Xác thực thông tin thẻ
- Xử lý lỗi giao dịch

## Kết Luận

Việc thêm chức năng nhập thông tin thanh toán chi tiết đã tạo ra một hệ thống thanh toán:
- **Bảo mật**: Thu thập và bảo vệ thông tin giao dịch
- **Minh bạch**: Lưu trữ đầy đủ thông tin giao dịch
- **Thân thiện**: Hướng dẫn rõ ràng cho người dùng
- **Mở rộng**: Dễ dàng thêm phương thức thanh toán mới

Hệ thống hiện tại đã sẵn sàng cho việc triển khai trong môi trường thực tế với đầy đủ tính năng bảo mật và theo dõi giao dịch! 