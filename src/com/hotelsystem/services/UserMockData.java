package com.hotelsystem.services;

import java.util.ArrayList;
import java.util.List;

import com.hotelsystem.user.User;
import com.hotelsystem.user.UserManager;

public class UserMockData {
	
	public static UserManager createMockUserManager() {
		UserManager userManager = UserManager.getInstance();
        List<User> users = new ArrayList<>();

        users.add(new User(1, "Nguyễn Văn A", "0901111222", "a@gmail.com"));
        users.add(new User(2, "Trần Thị B", "0902222333", "b@gmail.com"));
        users.add(new User(3, "Lê Văn C", "0903333444", "c@gmail.com"));
        users.add(new User(4, "Phạm Thị D", "0904444555", "d@gmail.com"));
        users.add(new User(5, "Đỗ Văn E", "0905555666", "e@gmail.com"));
        users.add(new User(6, "Ngô Thị F", "0906666777", "f@gmail.com"));
        users.add(new User(7, "Hoàng Văn G", "0907777888", "g@gmail.com"));
        users.add(new User(8, "Vũ Thị H", "0908888999", "h@gmail.com"));
        users.add(new User(9, "Đặng Văn I", "0909999000", "i@gmail.com"));
        users.add(new User(10, "Bùi Thị K", "0910000111", "k@gmail.com"));
        
        for (User user : users) {
			userManager.addUser(user);
		}

        return userManager;
    }
}
