package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import decorator.BreakfastDecorator;
import decorator.LaundryDecorator;
import decorator.SpaDecorator;
import room.BaseRoom;
import room.RoomInfo;
import room.SingleRoom;

public class RoomConsoleApp {
    private static final List<RoomInfo> rooms = new ArrayList<>();

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

                    // Hiển thị tổng tiền và thanh toán
                    double totalAmount = room.getCost();
                    System.out.println("Tổng tiền phòng: " + totalAmount + " VND");
                    System.out.println("Chọn phương thức thanh toán:");
                    System.out.println("1. Tiền mặt");
                    System.out.println("2. Thẻ tín dụng");
                    System.out.println("3. Chuyển khoản");
                    System.out.print("Lựa chọn: ");
                    int paymentChoice = Integer.parseInt(scanner.nextLine());

                    services.strategy.PaymentStrategy paymentStrategy;
                    switch (paymentChoice) {
                        case 1:
                            paymentStrategy = new services.strategy.CashPayment();
                            break;
                        case 2:
                            paymentStrategy = new services.strategy.CreditCardPayment();
                            break;
                        case 3:
                            paymentStrategy = new services.strategy.BankTransferPayment();
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ, mặc định thanh toán tiền mặt.");
                            paymentStrategy = new services.strategy.CashPayment();
                    }
                    boolean success = paymentStrategy.pay(totalAmount);
                    if (success) {
                        rooms.add(new RoomInfo(room, true));
                        System.out.println("Thanh toán thành công!");
                    } else {
                        System.out.println("Thanh toán thất bại. Vui lòng thử lại.");
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.println("===== DANH SÁCH PHÒNG =====");
                    if (rooms.isEmpty()) {
                        System.out.println("Chưa có phòng nào.");
                    } else {
                        for (int i = 0; i < rooms.size(); i++) {
                            RoomInfo info = rooms.get(i);
                            String desc = info.getRoom().getDescription();
                            if (info.isPaid()) {
                                desc += " [ĐÃ THANH TOÁN]";
                            }
                            System.out.println((i + 1) + ". " + desc);
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