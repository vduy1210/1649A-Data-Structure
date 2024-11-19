package models;

import storage.BookData;

public class DataStore {
    private MyArrayList<Book> booksList;
    private MyArrayList<User> userList;
    private MyArrayList<Order> orderList;

    public DataStore() {
        booksList = new BookData().getBooksList();
        userList = new MyArrayList<>();
        orderList = new MyArrayList<>();
    }

    // Methods to add data
    public void addBook(Book book) {
        booksList.add(book);
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }

    // Methods to get data
    public MyArrayList<Book> getBooksList() {
        return booksList;
    }

    public MyArrayList<User> getUserList() {
        return userList;
    }

    public MyArrayList<Order> getOrderList() {
        return orderList;
    }

    // Methods to find data
    public User findUserByUsername(String username) {
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public Order[] findOrdersByUsername(String username) {
        MyArrayList<Order> result = new MyArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            if (order.getCustomerName().equalsIgnoreCase(username)) {
                result.add(order);
            }
        }
        return result.toArray(new Order[0]);
    }

    public Order findOrderByNumber(int orderNumber) {
        if (orderNumber >= 0 && orderNumber < orderList.size()) {
            return orderList.get(orderNumber);
        }
        return null;
    }

    // Method to find a book by title
    public Book findBookByTitle(String title) {
        for (int i = 0; i < booksList.size(); i++) {
            Book book = booksList.get(i);
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Method to update book quantity
    public boolean updateBookQuantity(String title, int quantity) {
        for (int i = 0; i < booksList.size(); i++) {
            Book book = booksList.get(i);
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.getQuantity() >= quantity) {
                    book.setQuantity(book.getQuantity() - quantity);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}