//package com.hotelsystem.command.room;
//
//import com.hotelsystem.room.Room;
//import com.hotelsystem.observer.EmailNotificationObserver;
//
///**
// * Command for checking in to a room
// */
//public class CheckInCommand implements ReservationCommand {
//    private Room room;
//    private String guestName;
//    private String guestEmail;
//  //  private RoomStatusObserver previousState;
//    
//    public CheckInCommand(Room room, String guestName, String guestEmail) {
//        this.room = room;
//        this.guestName = guestName;
//        this.guestEmail = guestEmail;
//    }
//    
//    @Override
//    public void execute() {
//        // Store previous state for undo
//       // previousState = room.getCurrentState();
//        
//        // Add email notification observer
//        EmailNotificationObserver observer = new EmailNotificationObserver(guestEmail);
//        room.addObserver(observer);
//        
//        // Execute check-in
//        room.checkIn();
//        
//        System.out.println("Đã thực hiện lệnh check-in: " + getDescription());
//    }
//    
//    @Override
//    public void undo() {
//        // Remove the email observer
//        room.removeObserver(new EmailNotificationObserver(guestEmail));
//        
//        // Check out
//        room.checkOut();
//        
//        System.out.println("Đã hủy lệnh check-in: " + getDescription());
//    }
//    
//    @Override
//    public String getDescription() {
//        return "Check-in phòng " + room.getRoomNumber() + " cho " + guestName + " (" + guestEmail + ")";
//    }
//    
//    public Room getRoom() {
//        return room;
//    }
//    
//    public String getGuestName() {
//        return guestName;
//    }
//    
//    public String getGuestEmail() {
//        return guestEmail;
//    }
//} 