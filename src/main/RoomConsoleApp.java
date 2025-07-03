package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.room.BaseRoom;
import model.room.SingleRoom;
import model.room.decorator.BreakfastDecorator;
import model.room.decorator.LaundryDecorator;
import model.room.decorator.SpaDecorator;

public class RoomConsoleApp {
    private static final List<BaseRoom> rooms = new ArrayList<>();

    public static void start(Scanner scanner) {
        while (true) {
            System.out.println("===== QUẢN LÝ PHÒNG (CONSOLE) =====");
            System.out.println("1. Tạo phòng mới");
            System.out.println("2. Hiển thị danh sách phòng");
            System.out.println("0. Quay lại");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    System.out.print("Nhập số phòng: ");
                    String roomNumber = scanner.nextLine().trim();
                    BaseRoom room = new SingleRoom(roomNumber);

                    System.out.print("Thêm dịch vụ ăn sáng? (y/n): ");
                    if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                        room = new BreakfastDecorator(room);
                    }
                    System.out.print("Thêm dịch vụ giặt là? (y/n): ");
                    if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                        room = new LaundryDecorator(room);
                    }
                    System.out.print("Thêm dịch vụ spa? (y/n): ");
                    if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                        room = new SpaDecorator(room);
                    }

                    rooms.add(room);
                    System.out.println("Đã tạo phòng thành công!\n");
                    break;
                case 2:
                    System.out.println("===== DANH SÁCH PHÒNG =====");
                    if (rooms.isEmpty()) {
                        System.out.println("Chưa có phòng nào.");
                    } else {
                        for (int i = 0; i < rooms.size(); i++) {
                            System.out.println((i + 1) + ". " + rooms.get(i).getDescription());
                        }
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("Chức năng không hợp lệ!\n");
            }
        }
    }
} 