package com.hotelsystem.user;

import java.time.LocalDateTime;

/**
 * Class quản lý lịch sử khách hàng
 */
public class UserHistory {
	private int userId;
    private String roomNumber;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private double totalCost;

    
    public UserHistory(int userId, String roomNumber) {
        this.userId = userId;
        this.roomNumber = roomNumber;
    }
    
    public UserHistory(int id, String roomNumber, LocalDateTime checkin, LocalDateTime checkout, double totalCost) {
		this.userId = id;
		this.roomNumber = roomNumber;
		this.checkInTime = checkin;
		this.checkOutTime = checkout;
		this.totalCost = totalCost;
	}
	// Getters and Setters
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getRoomNumber() {
        return roomNumber;
    }
    
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }
    
    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }
    
    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }
    
    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
    
    public double getTotalCost() {
        return totalCost;
    }
    
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
    
    public long getDurationInHours() {
        if (checkInTime != null && checkOutTime != null) {
            return java.time.Duration.between(checkInTime, checkOutTime).toHours();
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return "UserHistory{" +
                "userId=" + userId +
                ", roomNumber='" + roomNumber + '\'' +
                ", checkInTime=" + checkInTime +
                ", checkOutTime=" + checkOutTime +
                ", totalCost=" + totalCost +
                '}';
    }
} 