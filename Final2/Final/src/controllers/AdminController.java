package controllers;

import models.Book;
import models.Order;
import services.OrderService;
import storage.BookData;
import storage.OrderData;
import models.MyArrayList;

import java.util.Scanner;

public class AdminController {
    private BookData bookData;
    private OrderService orderService;

    public AdminController(BookData bookData, OrderData orderData) {
        this.bookData = bookData;
        this.orderService = new OrderService(bookData, orderData);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Search for an order by username");
            System.out.println("2. View all processed orders");
            System.out.println("3. View all books in inventory");
            System.out.println("4. Update book quantity");
            System.out.println("5. Add new book");
            System.out.println("6. Remove a book");
            System.out.println("7. Log out");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    searchOrderByUsername(scanner);
                    break;
                case 2:
                    viewAllProcessedOrders();
                    break;
                case 3:
                    viewAllBooksInInventory(scanner);
                    break;
                case 4:
                    updateBookQuantity(scanner);
                    break;
                case 5:
                    addNewBook(scanner);
                    break;
                case 6:
                    removeBook(scanner);
                    break;
                case 7:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void searchOrderByUsername(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        Order order = orderService.findOrderByUsername(username);
        if (order != null) {
            double totalPrice = 0;
            System.out.println("Order:");
            for (int j = 0; j < order.getBooks().size(); j++) {
                Book book = order.getBooks().get(j);
                double bookPrice = book.getQuantity() * book.getPrice();
                totalPrice += bookPrice;
                System.out.println(book + ", Total Price: $" + bookPrice);
            }
            System.out.println("Total Amount to Pay: $" + totalPrice);
        } else {
            System.out.println("Order not found for username: " + username);
        }
    }

    private void viewAllProcessedOrders() {
        MyArrayList<Order> orders = orderService.getAllOrders();
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i));
        }
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
    

    private void updateBookQuantity(Scanner scanner) {
        bookData.displayBooks();
        System.out.print("Enter book number: ");
        int bookNumber = scanner.nextInt();
        System.out.print("Enter new quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Book book = bookData.findBookByIndex(bookNumber - 1);
        if (book != null) {
            book.setQuantity(quantity);
            System.out.println("Book quantity updated successfully!");
        } else {
            System.out.println("Book not found!");
        }
    }

    private void addNewBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter book price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        Book newBook = new Book(title, author, quantity, price);
        bookData.addBook(newBook);
        System.out.println("New book added successfully!");
    }

    private void removeBook(Scanner scanner) {
        bookData.displayBooks();
        System.out.print("Enter book number to remove: ");
        int bookNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Book book = bookData.findBookByIndex(bookNumber - 1);
        if (book != null) {
            bookData.removeBook(book);
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Book not found!");
        }
    }
}