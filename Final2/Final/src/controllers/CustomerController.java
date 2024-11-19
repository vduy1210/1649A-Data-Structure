package controllers;

import models.Book;
import models.Order;
import models.User;
import models.MyArrayList;
import models.MyStack;
import services.OrderService;
import services.PaymentService;
import storage.BookData;
import storage.OrderData;

import java.util.Scanner;

public class CustomerController {
    private BookData bookData;
    private MyArrayList<User> users;
    private OrderService orderService;
    private PaymentService paymentService;
    private MyArrayList<Book> cart;
    private MyStack<String> actionHistory;
    private String username;

    public CustomerController(BookData bookData, MyArrayList<User> users, OrderData orderData) {
        this.bookData = bookData;
        this.users = users;
        this.orderService = new OrderService(bookData, orderData);
        this.paymentService = new PaymentService();
        this.cart = new MyArrayList<>();
        this.actionHistory = new MyStack<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        username = scanner.nextLine();
        User user = findUserByUsername(username);
        if (user == null) {
            System.out.print("User not found. Enter your address to create a new user: ");
            String address = scanner.nextLine();
            user = new User(username, address);
            users.add(user);
            System.out.println("User created successfully!");
        }

        while (true) {
            System.out.println("Customer Menu:");
            System.out.println("1. Place Order");
            System.out.println("2. View all Books in inventory");
            System.out.println("3. View my cart");
            System.out.println("4. Checkout");
            System.out.println("5. View History Action");
            System.out.println("6. Log out");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    placeOrder(scanner);
                    break;
                case 2:
                    viewAllBooksInInventory(scanner);
                    break;
                case 3:
                    viewMyCart();
                    break;
                case 4:
                    checkout(scanner);
                    break;
                case 5:
                    viewHistoryAction(scanner);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void placeOrder(Scanner scanner) {
        while (true) {
            bookData.displayBooks();
            System.out.print("Enter book number (or '0' to finish): ");
            int bookNumber = scanner.nextInt();
            if (bookNumber == 0) {
                break;
            }
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            Book book = bookData.findBookByIndex(bookNumber - 1);
            if (book != null && book.getQuantity() >= quantity) {
                cart.add(new Book(book.getTitle(), book.getAuthor(), quantity, book.getPrice()));
                actionHistory.push("Added " + quantity + " of " + book.getTitle() + " to cart");
            } else {
                System.out.println("Book not available or insufficient quantity.");
            }
        }
        orderService.placeOrder(username, cart);
        System.out.println("Order placed successfully!");
        // Không xóa giỏ hàng sau khi đặt hàng
    }

    public void viewAllBooksInInventory(Scanner scanner) {
        System.out.println("Sort books by title? (yes/no): ");
        String sortChoice = scanner.nextLine();
    
        // Lấy danh sách sách từ BookData (bản gốc)
        MyArrayList<Book> originalList = bookData.getBooksList();
    
        // Tạo một bản sao danh sách để xử lý
        MyArrayList<Book> booksList = new MyArrayList<>();
        for (int i = 0; i < originalList.size(); i++) {
            booksList.add(originalList.get(i)); // Sao chép từng phần tử
        }
    
        if (sortChoice.equalsIgnoreCase("yes")) {
            // Sắp xếp bản sao danh sách
            for (int i = 1; i < booksList.size(); i++) {
                Book key = booksList.get(i);
                int j = i - 1;
    
                // Di chuyển các phần tử lớn hơn key về sau
                while (j >= 0 && booksList.get(j).getTitle().compareTo(key.getTitle()) > 0) {
                    booksList.set(j + 1, booksList.get(j));
                    j = j - 1;
                }
                booksList.set(j + 1, key);
            }
            System.out.println("Books sorted by title:");
        } else if (sortChoice.equalsIgnoreCase("no")) {
            // Không sắp xếp, sử dụng danh sách sao chép
            System.out.println("Books without sorting:");
        } else {
            System.out.println("Invalid choice. Showing unsorted books by default:");
        }
    
        // Hiển thị danh sách (sao chép)
        for (int i = 0; i < booksList.size(); i++) {
            Book book = booksList.get(i);
            System.out.println(book);
        }
    }
    
    private void viewMyCart() {
        double totalPrice = 0;
        System.out.println("Your Cart:");
        for (int i = 0; i < cart.size(); i++) {
            Book book = cart.get(i);
            double bookPrice = book.getQuantity() * book.getPrice();
            totalPrice += bookPrice;
            System.out.println(book + ", Total Price: $" + bookPrice);
        }
        System.out.println("Total Amount to Pay: $" + totalPrice);
    }

    private void checkout(Scanner scanner) {
        System.out.println("Choose payment method:");
        System.out.println("1. Pay by Cash");
        System.out.println("2. Pay by Credit Card");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                paymentService.payByCash(cart);
                actionHistory.push("Paid by cash");
                break;
            case 2:
                System.out.print("Enter credit card number: ");
                String cardNumber = scanner.nextLine();
                System.out.print("Enter expiration date (MM/YY): ");
                String expirationDate = scanner.nextLine();
                System.out.print("Enter CVV: ");
                String cvv = scanner.nextLine();
                paymentService.payByCreditCard(cart, cardNumber, expirationDate, cvv);
                actionHistory.push("Paid by credit card");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void viewHistoryAction(Scanner scanner) {
        while (true) {
            System.out.println("History Action Menu:");
            System.out.println("1. View last action");
            System.out.println("2. Delete an action by number");
            System.out.println("3. View all actions");
            System.out.println("4. Back to main menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewLastAction();
                    break;
                case 2:
                    deleteActionByNumber(scanner);
                    break;
                case 3:
                    viewAllActions();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewLastAction() {
        if (!actionHistory.isEmpty()) {
            String lastAction = actionHistory.peek();
            System.out.println("Last action: " + lastAction);
        } else {
            System.out.println("No actions in history.");
        }
    }

    private void deleteActionByNumber(Scanner scanner) {
        if (!actionHistory.isEmpty()) {
            viewAllActions();
            System.out.print("Enter the number of the action to delete: ");
            int actionNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (actionNumber > 0 && actionNumber <= actionHistory.size()) {
                String action = actionHistory.remove(actionNumber - 1);
                System.out.println("Deleted action: " + action);
            } else {
                System.out.println("Invalid action number.");
            }
        } else {
            System.out.println("No actions to delete.");
        }
    }

    private void viewAllActions() {
        if (!actionHistory.isEmpty()) {
            System.out.println("All actions in history:");
            for (int i = 0; i < actionHistory.size(); i++) {
                System.out.println((i + 1) + ". " + actionHistory.get(i));
            }
        } else {
            System.out.println("No actions in history.");
        }
    }

    private User findUserByUsername(String username) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }
}