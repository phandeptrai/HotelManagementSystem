package customermanagement;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer findById(int id) {
        for (Customer c : customers) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public void updateCustomer(Customer updatedCustomer) {
        Customer existing = findById(updatedCustomer.getId());
        if (existing != null) {
            existing.setName(updatedCustomer.getName());
            existing.setPhone(updatedCustomer.getPhone());
            existing.setEmail(updatedCustomer.getEmail());
        }
    }
}
