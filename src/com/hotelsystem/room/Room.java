package com.hotelsystem.room;

import com.hotelsystem.enums.RoomType;
import com.hotelsystem.observer.Observer;

import com.hotelsystem.observer.Subject;
import com.hotelsystem.room.state.AvailableState;
import com.hotelsystem.room.state.RoomState;

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

    private List<Observer> observers = new ArrayList<>();
    
	public Room(String roomNumber, double price) {
		this.roomNumber = roomNumber;
		this.price = price;
		this.currentState = new AvailableState(); // Default state

	}


    
    // State pattern methods
    public void checkIn() {
        currentState.checkIn(this);
        notifyObservers();
    }
    
    public void checkOut() {
        currentState.checkOut(this);
        notifyObservers();
    }
    
    public void reserve() {
        currentState.reserve(this);
        notifyObservers();
    }
    
    public void cancelReservation() {
        currentState.cancelReservation(this);
        notifyObservers();
    }
    
    public void startMaintenance() {
        currentState.startMaintenance(this);
        notifyObservers();
    }
    
    public void finishMaintenance() {
        currentState.finishMaintenance(this);
        notifyObservers();
    }
    
    public void setState(RoomState state) {
        this.currentState = state;
        notifyObservers();
    }
    
    public RoomState getCurrentState() {
        return currentState;
    }
    // obsser pattẻn
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
        for (Observer o : observers) {
            o.update(this);
        }
    }
	// Getter
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

	// Setter
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
