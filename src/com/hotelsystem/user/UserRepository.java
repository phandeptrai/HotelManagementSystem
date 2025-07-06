package com.hotelsystem.user;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
	private List<User> users = new ArrayList<>();

    public void addCustomer(User customer) {
    	users.add(customer);
    }

    public User findById(int id) {
        for (User c : users) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public List<User> getAllCustomers() {
        return users;
    }

    public void updateCustomer(User updatedCustomer) {
    	User existing = findById(updatedCustomer.getId());
        if (existing != null) {
            existing.setName(updatedCustomer.getName());
            existing.setPhone(updatedCustomer.getPhone());
            existing.setEmail(updatedCustomer.getEmail());
        }
    }
}
