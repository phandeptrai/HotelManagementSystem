package com.hotelsystem.room;

import com.hotelsystem.enums.RoomType;
import com.hotelsystem.observer.Observer;
import com.hotelsystem.observer.Subject;
import com.hotelsystem.room.state.AvailableState;
import com.hotelsystem.room.state.RoomState;
import com.hotelsystem.user.User;

import java.util.ArrayList;
import java.util.List;

import com.hotelsystem.enums.RoomStatus;

/**
 * Abstract Class cơ sở cho các loại phòng cụ thể với State pattern và GoF Observer pattern.
 */
public abstract class Room implements Subject{
	protected String roomNumber;
	protected double price;
	private RoomState currentState;
	private User currentUser; // Thông tin khách hàng hiện tại

    private List<Observer> observers = new ArrayList<>();
    
	public Room(String roomNumber, double price) {
		this.roomNumber = roomNumber;
		this.price = price;
		this.currentState = new AvailableState(); // Default state
		this.currentUser = null;
	}

    // State pattern methods với thông tin User
    public void checkIn(User user) {
        // Lưu thông tin user trước khi thay đổi state
        this.currentUser = user;
        
        // Gọi state method (sẽ thay đổi state và notify observers)
        currentState.checkIn(this, user);
    }
    
    public void checkOut(User user) {
        // Gọi state method trước
        currentState.checkOut(this, user);
        
        // Sau đó cập nhật thông tin user
        if (this.currentUser != null && user != null && 
            this.currentUser.getId() == user.getId()) {
            this.currentUser = null;
        }
    }
    
    public void reserve(User user) {
        currentState.reserve(this, user);
    }
    
    public void cancelReservation(User user) {
        currentState.cancelReservation(this, user);
    }
    
    public void startMaintenance() {
        currentState.startMaintenance(this);
    }
    
    public void finishMaintenance() {
        currentState.finishMaintenance(this);
    }
    
    public void setState(RoomState state) {
        this.currentState = state;
        notifyObservers();
    }
    
    public RoomState getCurrentState() {
        return currentState;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    // Observer pattern
	@Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    // Getters
    public String getRoomNumber() {
        return roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return "#" + roomNumber + " (" + currentState.getStateName() + ")";
    }
    
    public boolean isAvailable() {
        return currentState.getStateName().equals("Available");
    }

    // Setters
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return getDescription();
    }
    
    public abstract RoomType getRoomType();

    public abstract double getCost();
}

