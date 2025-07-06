# Refactor Hệ Thống Quản Lý Khách Sạn

## Tổng Quan

File `HotelManagementSystem.java` ban đầu có kích thước lớn (925 dòng) và chứa tất cả logic giao diện người dùng. Để cải thiện khả năng bảo trì và mở rộng, hệ thống đã được refactor thành các class riêng biệt theo chức năng.

## Cấu Trúc Mới

### 1. HotelSystemMain.java
- **Vai trò**: Class chính, điểm khởi đầu của ứng dụng
- **Chức năng**: 
  - Khởi tạo các manager và UI components
  - Hiển thị menu chính
  - Điều hướng đến các module khác nhau

### 2. UserManagementUI.java
- **Vai trò**: Quản lý giao diện cho module khách hàng
- **Chức năng**:
  - Thêm, sửa, xóa khách hàng
  - Tìm kiếm khách hàng
  - Xem lịch sử khách hàng
  - Thống kê khách hàng

### 3. RoomManagementUI.java
- **Vai trò**: Quản lý giao diện cho module phòng
- **Chức năng**:
  - Hiển thị danh sách phòng
  - Quản lý loại phòng
  - Xem hóa đơn
  - Thống kê phòng

### 4. BookingManagementUI.java
- **Vai trò**: Quản lý giao diện cho module đặt phòng
- **Chức năng**:
  - Đặt phòng, check-in, check-out
  - Hủy đặt phòng
  - Bảo trì phòng
  - Quản lý lệnh (Undo/Redo)

### 5. ReportUI.java
- **Vai trò**: Quản lý giao diện cho module báo cáo
- **Chức năng**:
  - Thống kê khách hàng
  - Thống kê phòng
  - Thống kê doanh thu
  - Báo cáo tổng hợp

## Lợi Ích Của Việc Refactor

### 1. Tính Module Hóa
- Mỗi module có class UI riêng biệt
- Dễ dàng thêm/sửa/xóa tính năng cho từng module
- Giảm sự phụ thuộc giữa các module

### 2. Khả Năng Bảo Trì
- Code ngắn gọn, dễ đọc hơn
- Mỗi class có trách nhiệm rõ ràng
- Dễ dàng debug và sửa lỗi

### 3. Khả Năng Mở Rộng
- Dễ dàng thêm module mới
- Có thể tái sử dụng các UI component
- Dễ dàng thêm tính năng mới cho từng module

### 4. Tách Biệt Trách Nhiệm
- **HotelSystemMain**: Điều phối và khởi tạo
- **UserManagementUI**: Quản lý khách hàng
- **RoomManagementUI**: Quản lý phòng
- **BookingManagementUI**: Quản lý đặt phòng
- **ReportUI**: Báo cáo và thống kê

## Cấu Trúc File

```
src/main/
├── HotelSystemMain.java          # Class chính
├── UserManagementUI.java         # UI quản lý khách hàng
├── RoomManagementUI.java         # UI quản lý phòng
├── BookingManagementUI.java      # UI quản lý đặt phòng
├── ReportUI.java                 # UI báo cáo
└── HotelManagementSystem.java    # File cũ (có thể xóa)
```

## Cách Sử Dụng

### Chạy Hệ Thống
```bash
# Windows
run.bat

# Linux/Mac
./run.sh
```

### Biên Dịch Thủ Công
```bash
javac -cp "src" src/main/*.java
java -cp "src" main.HotelSystemMain
```

## So Sánh Trước và Sau Refactor

### Trước Refactor
- **File duy nhất**: `HotelManagementSystem.java` (925 dòng)
- **Khó bảo trì**: Tất cả logic trong một file
- **Khó mở rộng**: Thêm tính năng mới phức tạp
- **Khó debug**: Khó tìm lỗi trong file lớn

### Sau Refactor
- **5 file riêng biệt**: Mỗi module một file
- **Dễ bảo trì**: Logic được tách biệt rõ ràng
- **Dễ mở rộng**: Thêm module mới đơn giản
- **Dễ debug**: Lỗi được cô lập trong từng module

## Thêm Module Mới

Để thêm module mới (ví dụ: Quản lý dịch vụ):

1. **Tạo ServiceUI mới**:
```java
public class ServiceManagementUI {
    public void showMenu() {
        // Logic menu
    }
}
```

2. **Thêm vào HotelSystemMain**:
```java
private static final ServiceManagementUI serviceUI = new ServiceManagementUI();

// Trong main menu
case 5:
    serviceUI.showMenu();
    break;
```

## Kết Luận

Việc refactor đã tạo ra một hệ thống:
- **Có cấu trúc rõ ràng**
- **Dễ bảo trì và mở rộng**
- **Tuân thủ nguyên tắc Single Responsibility**
- **Có khả năng tái sử dụng cao**

Hệ thống hiện tại sẵn sàng cho việc phát triển thêm các tính năng mới và dễ dàng bảo trì trong tương lai. 