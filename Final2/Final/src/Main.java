import controllers.AdminController;
import controllers.CustomerController;
import models.MyArrayList;
import models.User;
import storage.BookData;
import storage.OrderData;

import java.util.Scanner;

public class Main {
    private BookData bookData;
    private MyArrayList<User> users;
    private OrderData orderData;

    public Main() {
        bookData = new BookData();
        users = new MyArrayList<>();
        orderData = new OrderData();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select Role:");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    AdminController adminController = new AdminController(bookData, orderData);
                    adminController.start();
                    break;
                case 2:
                    CustomerController customerController = new CustomerController(bookData, users, orderData);
                    customerController.start();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}