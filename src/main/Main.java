package main;

import java.util.Scanner;

import command.AddServiceCommand;
import command.EditRoomTypeCommand;
import command.RemoveRoomTypeCommand;
import command.RoomTypeCommand;
import command.RoomTypeInvoker;
import enums.RoomType;
import room.BaseRoom;
import room.SingleRoom;
import services.RoomTypeManager;

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
			System.out.println("4. Thêm dịch vụ cho phòng (BREAKFAST/LAUNDRY/SPA)");
			System.out.println("5. Quản lý phòng chi tiết");
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
			case 4:
				System.out.print("Nhập số phòng: ");
				String roomNumber = scanner.nextLine().trim();
				BaseRoom room = new SingleRoom(roomNumber);
				System.out.print("Nhập dịch vụ muốn thêm (BREAKFAST/LAUNDRY/SPA): ");
				String service = scanner.nextLine().trim().toUpperCase();
				AddServiceCommand addServiceCmd = new AddServiceCommand(room, service);
				invoker.setCommand(addServiceCmd);
				invoker.run();
				BaseRoom decoratedRoom = addServiceCmd.getDecoratedRoom();
				System.out.println("Đã thêm dịch vụ cho phòng. Thông tin phòng sau khi thêm dịch vụ: " + decoratedRoom.getDescription());
				break;
			case 5:
				RoomConsoleApp.start(scanner);
				break;
			default:
				System.out.println("Chức năng không hợp lệ!");
			}
			System.out.println();
		}
		scanner.close();
	}
}