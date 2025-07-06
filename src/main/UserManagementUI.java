package main;

import java.util.Scanner;
import com.hotelsystem.user.*;

public class UserManagementUI {
    private static final Scanner scanner = new Scanner(System.in);
    private final UserManager userManager;

    public UserManagementUI(UserManager userManager) {
        this.userManager = userManager;
    }

    public void showMenu() {
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

    private void addNewUser() {
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

    private void searchUser() {
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

    private void updateUser() {
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

    private void removeUser() {
        System.out.println("\n=== XÓA KHÁCH HÀNG ===");
        
        // Hiển thị danh sách khách hàng
        System.out.println("Danh sách khách hàng:");
        displayUserList();
        
        int id = getIntInput("Nhập ID khách hàng cần xóa: ");
        userManager.removeUser(id);
    }

    private void viewUserHistory() {
        System.out.println("\n=== XEM LỊCH SỬ KHÁCH HÀNG ===");
        
        // Hiển thị danh sách khách hàng
        System.out.println("Danh sách khách hàng:");
        displayUserList();
        
        int id = getIntInput("Nhập ID khách hàng: ");
        userManager.displayUserHistory(id);
    }

    private void displayUserList() {
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

    private int getIntInput(String prompt) {
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