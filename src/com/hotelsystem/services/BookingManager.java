package com.hotelsystem.services;

import com.hotelsystem.room.Room;
import com.hotelsystem.user.User;
import com.hotelsystem.user.UserHistory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private static BookingManager instance;
    private final List<UserHistory> bookings;

    private BookingManager() {
        bookings = new ArrayList<>();
    }

    public static BookingManager getInstance() {
        if (instance == null) {
            instance = new BookingManager();
        }
        return instance;
    }

    // Đặt phòng (check-in)
    public boolean bookRoom(Room room, User user) {
        if (!room.isAvailable()) {
            System.out.println("❌ Phòng không khả dụng!");
            return false;
        }

        room.checkIn(user); // Gọi State + Observer
        UserHistory history = new UserHistory(
            user.getId(),
            room.getRoomNumber(),
            LocalDateTime.now(),
            null, // check-out sau này mới có
            room.getCost()
        );
        bookings.add(history);
        System.out.println("✅ Đã đặt phòng " + room.getRoomNumber() + " cho " + user.getName());
        return true;
    }

    // Trả phòng (check-out)
    public void checkOut(Room room, User user) {
        room.checkOut(user); // Cập nhật state
        for (UserHistory history : bookings) {
            if (history.getUserId() == user.getId()
                && history.getRoomNumber().equals(room.getRoomNumber())
                && history.getCheckOutTime() == null) {

                history.setCheckOutTime(LocalDateTime.now());
                System.out.println("🏁 " + user.getName() + " đã trả phòng " + room.getRoomNumber());
                return;
            }
        }
        System.out.println("⚠️ Không tìm thấy lịch sử để check-out.");
    }

    // Lấy danh sách booking hiện tại
    public List<UserHistory> getBookings() {
        return bookings;
    }

    // In danh sách tất cả booking
    public void displayBookings() {
        System.out.println("\n=== DANH SÁCH BOOKING ===");
        for (UserHistory history : bookings) {
            System.out.println("UserID: " + history.getUserId() +
                               ", Room: " + history.getRoomNumber() +
                               ", Check-in: " + history.getCheckInTime() +
                               ", Check-out: " + (history.getCheckOutTime() != null ? history.getCheckOutTime() : "Chưa trả"));
        }
    }
}
