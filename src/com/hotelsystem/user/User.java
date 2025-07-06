package com.hotelsystem.user;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String name;
    private String phone;
    private String email;
    private LocalDateTime checkInTime;

    public User(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public LocalDateTime getCheckInTime() { return checkInTime; }

    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setCheckInTime(LocalDateTime checkInTime) { this.checkInTime = checkInTime; }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', phone='" + phone + "', email='" + email + "'}";
    }
 }