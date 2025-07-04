package main;

import java.util.Scanner;
import services.RoomTypeManager;
import services.BillService;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		RoomTypeManager roomTypeManager = new RoomTypeManager();
		BillService billService = new BillService();
		while (true) {
			System.out.println("===== MENU CHÍNH =====");
			System.out.println("1. Quản lý hóa đơn");
			System.out.println("2. Quản lý phòng");
			System.out.println("3. Quản lý loại phòng");
			System.out.println("4. Quản lý dịch vụ");
			System.out.println("0. Thoát");
			System.out.print("Chọn chức năng: ");
			int choice = Integer.parseInt(scanner.nextLine());
			if (choice == 0) break;
			switch (choice) {
			case 1:
				manageBills(scanner, billService);
				break;
			case 2:
				manageRooms(scanner);
				break;
			case 3:
				manageRoomTypes(scanner, roomTypeManager);
				break;
			case 4:
				manageServices(scanner);
				break;
			default:
				System.out.println("Chức năng không hợp lệ!");
			}
			System.out.println();
		}
		scanner.close();
	}

	private static void manageBills(Scanner scanner, BillService billService) {
		// Khung menu quản lý hóa đơn
		System.out.println("===== QUẢN LÝ HÓA ĐƠN =====");
		System.out.println("1. Hiển thị danh sách hóa đơn");
		System.out.println("0. Quay lại");
		System.out.print("Chọn chức năng: ");
		int choice = Integer.parseInt(scanner.nextLine());
		if (choice == 1) {
			var allBills = billService.getAllBills();
			if (allBills.isEmpty()) {
				System.out.println("Chưa có hóa đơn nào.");
			} else {
				for (var bill : allBills) {
					System.out.println(bill);
				}
			}
		}
	}

	private static void manageRooms(Scanner scanner) {
		// Khung menu quản lý phòng
		RoomConsoleApp.start(scanner);
	}

	private static void manageRoomTypes(Scanner scanner, RoomTypeManager roomTypeManager) {
		// Khung menu quản lý loại phòng (có thể dùng lại logic cũ hoặc tách ra)
		while (true) {
			System.out.println("===== QUẢN LÝ LOẠI PHÒNG =====");
			System.out.println("1. Xem danh sách loại phòng");
			System.out.println("2. Sửa tên hiển thị loại phòng");
			System.out.println("3. Xóa loại phòng");
			System.out.println("0. Quay lại");
			System.out.print("Chọn chức năng: ");
			int choice = Integer.parseInt(scanner.nextLine());
			if (choice == 0) break;
			switch (choice) {
			case 1:
				roomTypeManager.showRoomTypes();
				break;
			case 2:
				roomTypeManager.showRoomTypes();
				System.out.print("Nhập loại phòng muốn sửa (ví dụ: SINGLE): ");
				var typeEdit = enums.RoomType.valueOf(scanner.nextLine().trim().toUpperCase());
				System.out.print("Nhập tên hiển thị mới: ");
				String newName = scanner.nextLine();
				roomTypeManager.editRoomTypeName(typeEdit, newName);
				System.out.println("Đã sửa tên hiển thị.");
				break;
			case 3:
				roomTypeManager.showRoomTypes();
				System.out.print("Nhập loại phòng muốn xóa (ví dụ: SINGLE): ");
				var typeRemove = enums.RoomType.valueOf(scanner.nextLine().trim().toUpperCase());
				roomTypeManager.removeRoomType(typeRemove);
				System.out.println("Đã xóa loại phòng.");
				break;
			default:
				System.out.println("Chức năng không hợp lệ!");
			}
		}
	}

	private static void manageServices(Scanner scanner) {
		// Khung menu quản lý dịch vụ (bổ sung logic sau)
		System.out.println("===== QUẢN LÝ DỊCH VỤ =====");
		System.out.println("1. Hiển thị danh sách dịch vụ");
		System.out.println("0. Quay lại");
		System.out.print("Chọn chức năng: ");
		int choice = Integer.parseInt(scanner.nextLine());
		if (choice == 1) {
			System.out.println("- BREAKFAST\n- LAUNDRY\n- SPA");
		}
	}
}