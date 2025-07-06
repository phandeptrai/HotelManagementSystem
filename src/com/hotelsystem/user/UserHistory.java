package com.hotelsystem.user;

import java.time.LocalDateTime;

/**
 * Class quản lý lịch sử khách hàng
 */
public class UserHistory {
	private int userId;
    private String roomNumber;
    private LocalDateTime reservationTime; // Thời gian đặt phòng
    private LocalDateTime checkInTime;     // Thời gian check-in
    private LocalDateTime checkOutTime;    // Thời gian check-out
    private double totalCost;

    
    public UserHistory(int userId, String roomNumber) {
        this.userId = userId;
        this.roomNumber = roomNumber;
    }
    
    public UserHistory(int id, String roomNumber, LocalDateTime reservationTime, LocalDateTime checkin, LocalDateTime checkout, double totalCost) {
		this.userId = id;
		this.roomNumber = roomNumber;
		this.reservationTime = reservationTime;
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
    
    public LocalDateTime getReservationTime() {
        return reservationTime;
    }
    
    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
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
                ", reservationTime=" + reservationTime +
                ", checkInTime=" + checkInTime +
                ", checkOutTime=" + checkOutTime +
                ", totalCost=" + totalCost +
                '}';
    }
} 