//package main;
//
//import java.util.Scanner;
//import com.hotelsystem.decorator.BreakfastDecorator;
//import com.hotelsystem.decorator.LaundryDecorator;
//import com.hotelsystem.decorator.SpaDecorator;
//import com.hotelsystem.room.Room;
//import com.hotelsystem.room.SingleRoom;
//import com.hotelsystem.services.BillService;
//import com.hotelsystem.services.RoomManager;
//import com.hotelsystem.services.RoomMockData;
//import com.hotelsystem.enums.RoomType;
//import com.hotelsystem.enums.RoomStatus;
//
//public class RoomConsoleApp {
//    private static final RoomManager roomManager = RoomMockData.createMockRoomManager();
//    private static final BillService billService = new BillService();
//
//    public static void start(Scanner scanner) {
//        while (true) {
//            System.out.println("===== QUẢN LÝ PHÒNG (CONSOLE) =====");
//            System.out.println("1. Đặt phòng");
//            System.out.println("2. Hiển thị danh sách phòng");
//            System.out.println("3. Hiển thị danh sách phòng trống");
//            System.out.println("4. ...");
//            System.out.println("5. Hiển thị danh sách hóa đơn");
//            System.out.println("0. Quay lại");
//            System.out.print("Chọn chức năng: ");
//            int choice = Integer.parseInt(scanner.nextLine());
//
//            if (choice == 0) break;
//
//            switch (choice) {
//                case 1:
//                    // Đặt phòng
//                    System.out.println("Chọn loại phòng muốn đặt: 1. SINGLE 2. DOUBLE 3. SUITE");
//                    int typeChoice = Integer.parseInt(scanner.nextLine());
//                    RoomType type = RoomType.SINGLE;
//                    if (typeChoice == 2) type = RoomType.DOUBLE;
//                    else if (typeChoice == 3) type = RoomType.SUITE;
//                    if (roomManager.countAvailableRooms(type) == 0) {
//                        System.out.println("Không còn phòng trống loại này!");
//                        break;
//                    }
//                    Room room = roomManager.bookRoom(type);
//                    if (room == null) {
//                        System.out.println("Đặt phòng thất bại!");
//                        break;
//                    }
//                    System.out.print("Thêm dịch vụ ăn sáng? (y/n): ");
//                    if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
//                        room = new BreakfastDecorator(room);
//                    }
//                    System.out.print("Thêm dịch vụ giặt là? (y/n): ");
//                    if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
//                        room = new LaundryDecorator(room);
//                    }
//                    System.out.print("Thêm dịch vụ spa? (y/n): ");
//                    if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
//                        room = new SpaDecorator(room);
//                    }
//                    double totalAmount = room.getCost();
//                    System.out.println("Tổng tiền phòng: " + totalAmount + " VND");
//                    System.out.println("Chọn phương thức thanh toán:");
//                    System.out.println("1. Tiền mặt");
//                    System.out.println("2. Thẻ tín dụng");
//                    System.out.println("3. Chuyển khoản");
//                    System.out.print("Lựa chọn: ");
//                    int paymentChoice = Integer.parseInt(scanner.nextLine());
//                    services.strategy.PaymentStrategy paymentStrategy;
//                    switch (paymentChoice) {
//                        case 1:
//                            paymentStrategy = new services.strategy.CashPayment();
//                            break;
//                        case 2:
//                            paymentStrategy = new services.strategy.CreditCardPayment();
//                            break;
//                        case 3:
//                            paymentStrategy = new services.strategy.BankTransferPayment();
//                            break;
//                        default:
//                            System.out.println("Lựa chọn không hợp lệ, mặc định thanh toán tiền mặt.");
//                            paymentStrategy = new services.strategy.CashPayment();
//                    }
//                    boolean success = paymentStrategy.pay(totalAmount);
//                    if (success) {
//                        System.out.println("Đặt phòng thành công! Số phòng: " + room.getRoomNumber());
//                        String paymentMethod;
//                        switch (paymentChoice) {
//                            case 1: paymentMethod = "Tiền mặt"; break;
//                            case 2: paymentMethod = "Thẻ tín dụng"; break;
//                            case 3: paymentMethod = "Chuyển khoản"; break;
//                            default: paymentMethod = "Tiền mặt";
//                        }
//                        billService.createBill(new room.RoomInfo(room, true), totalAmount, paymentMethod);
//                    } else {
//                        System.out.println("Thanh toán thất bại. Vui lòng thử lại.");
//                        room.checkOut(); // Trả lại trạng thái phòng nếu thanh toán fail
//                    }
//                    System.out.println();
//                    break;
//                case 2:
//                    System.out.println("===== DANH SÁCH PHÒNG =====");
//                    if (roomManager.getRooms().isEmpty()) {
//                        System.out.println("Chưa có phòng nào.");
//                    } else {
//                        for (Room r : roomManager.getRooms()) {
//                            String desc = r.getDescription();
//                            desc += " [" + r.getStatus() + "]";
//                            System.out.println(r.getRoomNumber() + ": " + desc);
//                        }
//                    }
//                    System.out.println();
//                    break;
//                case 3:
//                    System.out.println("===== DANH SÁCH PHÒNG TRỐNG =====");
//                    boolean hasAvailable = false;
//                    for (RoomType t : RoomType.values()) {
//                        int available = roomManager.countAvailableRooms(t);
//                        int total = roomManager.getRoomsByType(t).size();
//                        if (total > 0) {
//                            System.out.println(t + ": " + available + "/" + total + " phòng trống");
//                            if (available > 0) hasAvailable = true;
//                        }
//                    }
//                    if (!hasAvailable) {
//                        System.out.println("Không còn phòng trống.");
//                    }
//                    System.out.println();
//                    break;
//                case 4:
//                    System.out.println("Chức năng đang phát triển.");
//                    System.out.println();
//                    break;
//                case 5:
//                    System.out.println("===== DANH SÁCH HÓA ĐƠN =====");
//                    var allBills = billService.getAllBills();
//                    if (allBills.isEmpty()) {
//                        System.out.println("Chưa có hóa đơn nào.");
//                    } else {
//                        for (var bill : allBills) {
//                            System.out.println(bill);
//                        }
//                    }
//                    System.out.println();
//                    break;
//                default:
//                    System.out.println("Chức năng không hợp lệ!\n");
//            }
//        }
//    }
//} 