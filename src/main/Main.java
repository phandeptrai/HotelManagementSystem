package main;

import java.util.Scanner;

import enums.RoomType;
import services.RoomTypeManager;
import command.RoomTypeCommand;
import command.EditRoomTypeCommand;
import command.RemoveRoomTypeCommand;
import command.RoomTypeInvoker;

public class Main {
	public static void main(String[] args) {
		RoomTypeManager roomTypeManager = new RoomTypeManager();
		RoomTypeInvoker invoker = new RoomTypeInvoker();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("===== QUẢN LÝ LOẠI PHÒNG =====");
			System.out.println("1. Xem danh sách loại phòng");
			System.out.println("2. Sửa tên hiển thị loại phòng");
			System.out.println("3. Xóa loại phòng");
			System.out.println("0. Thoát");
			System.out.print("Chọn chức năng: ");
			int choice = Integer.parseInt(scanner.nextLine());

			if (choice == 0)
				break;

			switch (choice) {
			case 1:
				roomTypeManager.showRoomTypes();
				break;
			case 2:
				roomTypeManager.showRoomTypes();
				System.out.print("Nhập loại phòng muốn sửa (ví dụ: SINGLE): ");
				RoomType typeEdit = RoomType.valueOf(scanner.nextLine().trim().toUpperCase());
				System.out.print("Nhập tên hiển thị mới: ");
				String newName = scanner.nextLine();
				RoomTypeCommand editCommand = new EditRoomTypeCommand(roomTypeManager, typeEdit, newName);
				invoker.setCommand(editCommand);
				invoker.run();
				break;
			case 3:
				roomTypeManager.showRoomTypes();
				System.out.print("Nhập loại phòng muốn xóa (ví dụ: SINGLE): ");
				RoomType typeRemove = RoomType.valueOf(scanner.nextLine().trim().toUpperCase());
				RoomTypeCommand removeCommand = new RemoveRoomTypeCommand(roomTypeManager, typeRemove);
				invoker.setCommand(removeCommand);
				invoker.run();
				break;
			default:
				System.out.println("Chức năng không hợp lệ!");
			}
			System.out.println();
		}
		scanner.close();
	}
}