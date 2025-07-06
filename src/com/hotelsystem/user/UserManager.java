package com.hotelsystem.user;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static UserManager instance;
    private final List<User> users;
    private final List<UserHistory> userHistories;

    private UserManager() {
        users = new ArrayList<>();
        userHistories = new ArrayList<>();
    }
    
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }



    // Thêm user mới
    public void addUser(User user) {
        if (getUser(user.getId()) == null) {
            users.add(user);
            System.out.println("Đã thêm khách hàng: " + user.getName());
        } else {
            System.out.println("⚠️ ID đã tồn tại, không thêm.");
        }
    }

    // Lấy user theo ID
    public User getUser(int userId) {
        for (User user : users) {
            if (user.getId() == userId) return user;
        }
        return null;
    }

    // Cập nhật user theo ID
    public void updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == updatedUser.getId()) {
                users.set(i, updatedUser);
                System.out.println("Đã cập nhật: " + updatedUser.getName());
                return;
            }
        }
        System.out.println("Không tìm thấy khách hàng với ID: " + updatedUser.getId());
    }

    // Xóa user theo ID
    public void removeUser(int userId) {
        User toRemove = getUser(userId);
        if (toRemove != null) {
            users.remove(toRemove);
            System.out.println("Đã xóa khách hàng: " + toRemove.getName());
        } else {
            System.out.println("Không tìm thấy khách hàng với ID: " + userId);
        }
    }

    // Tìm user theo tên
    public List<User> findUsersByName(String name) {
        List<User> found = new ArrayList<>();
        for (User user : users) {
            if (user.getName().toLowerCase().contains(name.toLowerCase())) {
                found.add(user);
            }
        }
        return found;
    }

    // Tìm user theo email
    public User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) return user;
        }
        return null;
    }

    // Lịch sử
    public void addUserHistory(UserHistory history) {
        userHistories.add(history);
        System.out.println("Đã thêm lịch sử cho khách hàng: " + history.getUserId());
    }

    public List<UserHistory> getUserHistory(int userId) {
        List<UserHistory> result = new ArrayList<>();
        for (UserHistory h : userHistories) {
            if (h.getUserId() == userId) result.add(h);
        }
        return result;
    }

    public List<UserHistory> getAllUserHistories() {
        return new ArrayList<>(userHistories);
    }
    
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public void displayStatistics() {
        System.out.println("\n=== THỐNG KÊ KHÁCH HÀNG ===");
        System.out.println("Tổng số khách hàng: " + users.size());
        System.out.println("Tổng số lịch sử: " + userHistories.size());

        long totalDuration = 0;
        double totalRevenue = 0;
        for (UserHistory h : userHistories) {
            totalDuration += h.getDurationInHours();
            totalRevenue += h.getTotalCost();
        }

        System.out.println("Tổng thời gian lưu trú: " + totalDuration + " giờ");
        System.out.println("Tổng doanh thu: " + totalRevenue + " VND");

        if (!userHistories.isEmpty()) {
            System.out.println("Thời gian lưu trú trung bình: " + (totalDuration / userHistories.size()) + " giờ");
            System.out.println("Doanh thu trung bình: " + (totalRevenue / userHistories.size()) + " VND");
        }
    }

    public void displayAllUsers() {
        System.out.println("\n=== DANH SÁCH KHÁCH HÀNG ===");
        if (users.isEmpty()) {
            System.out.println("Không có khách hàng nào.");
            return;
        }

        for (User user : users) {
            System.out.println("ID: " + user.getId() +
                    ", Tên: " + user.getName() +
                    ", Email: " + user.getEmail() +
                    ", Phone: " + user.getPhone());
        }
    }

    public void displayUserHistory(int userId) {
        List<UserHistory> histories = getUserHistory(userId);
        User user = getUser(userId);

        System.out.println("\n=== LỊCH SỬ KHÁCH HÀNG ===");
        if (user != null) {
            System.out.println("Khách hàng: " + user.getName() + " (ID: " + user.getId() + ")");
        }

        if (histories.isEmpty()) {
            System.out.println("Không có lịch sử nào.");
            return;
        }

        for (UserHistory history : histories) {
            System.out.println("Phòng: " + history.getRoomNumber() +
                    ", Check-in: " + history.getCheckInTime() +
                    ", Check-out: " + history.getCheckOutTime() +
                    ", Chi phí: " + history.getTotalCost() + " VND");
        }
    }
}
