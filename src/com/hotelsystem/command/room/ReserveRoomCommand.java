//package com.hotelsystem.command.room;
//
//import com.hotelsystem.room.Room;
//
//import com.hotelsystem.observer.ReservationNotificationObserver;
//
///**
// * Command for reserving a room
// */
//public class ReserveRoomCommand implements ReservationCommand {
//    private Room room;
//    private String customerName;
//    private String customerEmail;
//   // private RoomStatusObserver previousState;
//    
//    public ReserveRoomCommand(Room room, String customerName, String customerEmail) {
//        this.room = room;
//        this.customerName = customerName;
//        this.customerEmail = customerEmail;
//    }
//    
//    @Override
//    public void execute() {
//        // Store previous state for undo
//       // previousState = room.getCurrentState();
//        
//        // Add reservation observer
//        ReservationNotificationObserver observer = 
//            new ReservationNotificationObserver(customerEmail, customerName);
//        room.addObserver(observer);
//        
//        // Execute reservation
//        room.reserve();
//        
//        System.out.println("Đã thực hiện lệnh đặt phòng: " + getDescription());
//    }
//    
//    @Override
//    public void undo() {
//        // Remove the reservation observer
//        room.removeObserver(new ReservationNotificationObserver(customerEmail, customerName));
//        
//        // Cancel reservation
//        room.cancelReservation();
//        
//        System.out.println("Đã hủy lệnh đặt phòng: " + getDescription());
//    }
//    
//    @Override
//    public String getDescription() {
//        return "Đặt phòng " + room.getRoomNumber() + " cho " + customerName + " (" + customerEmail + ")";
//    }
//    
//    public Room getRoom() {
//        return room;
//    }
//    
//    public String getCustomerName() {
//        return customerName;
//    }
//    
//    public String getCustomerEmail() {
//        return customerEmail;
//    }
//} 