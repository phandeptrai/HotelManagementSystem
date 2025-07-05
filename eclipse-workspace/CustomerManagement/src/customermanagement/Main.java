package customermanagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomerRepository repo = new CustomerRepository();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Quan ly thong tin khach hang ===");
            System.out.println("1. Xem danh sach khach hang");
            System.out.println("2. Them khach hang moi");
            System.out.println("3. Cap nhat thong tin khach hang");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    for (Customer c : repo.getAllCustomers()) {
                        System.out.println(c);
                    }
                }
                case 2 -> {
                    System.out.print("Nhap ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhap ten: ");
                    String name = sc.nextLine();
                    System.out.print("Nhap SDT: ");
                    String phone = sc.nextLine();
                    System.out.print("Nhap email: ");
                    String email = sc.nextLine();
                    repo.addCustomer(new Customer(id, name, phone, email));
                    System.out.println("Them thanh cong!");
                }
                case 3 -> {
                    System.out.print("Nhap ID khach hang can cap nhat: ");
                    int id = Integer.parseInt(sc.nextLine());
                    Customer customer = repo.findById(id);
                    if (customer == null) {
                        System.out.println("Khong tim thay khach hang!");
                    } else {
                        System.out.print("Nhap ten moi (bo qua neu khong doi): ");
                        String name = sc.nextLine();
                        if (!name.isEmpty()) customer.setName(name);

                        System.out.print("Nhap SDT moi (bo qua neu khong doi): ");
                        String phone = sc.nextLine();
                        if (!phone.isEmpty()) customer.setPhone(phone);

                        System.out.print("Nhap email moi (bo qua neu khong doi): ");
                        String email = sc.nextLine();
                        if (!email.isEmpty()) customer.setEmail(email);

                        repo.updateCustomer(customer);
                        System.out.println("Cap nhat thanh cong!");
                    }
                }
                case 0 -> System.out.println("Tam biet!");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }
}

