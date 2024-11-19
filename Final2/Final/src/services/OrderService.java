package services;

import models.Book;
import models.Order;
import models.MyArrayList;
import storage.BookData;
import storage.OrderData;

public class OrderService {
    private BookData bookData;
    private OrderData orderData;

    public OrderService(BookData bookData, OrderData orderData) {
        this.bookData = bookData;
        this.orderData = orderData;
    }

    public void placeOrder(String username, MyArrayList<Book> books) {
        Order order = new Order(username, books);
        orderData.addOrder(order);
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            bookData.updateBookQuantity(book.getTitle(), book.getQuantity());
        }
    }

    public Order findOrderByUsername(String username) {
        return orderData.findOrderByUsername(username);
    }

    public MyArrayList<Order> getAllOrders() {
        return orderData.getOrderQueue().getElements();
    }
}