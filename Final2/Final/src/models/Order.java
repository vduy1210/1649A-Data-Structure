package models;

public class Order {
    private String customerName;
    private MyArrayList<Book> books;

    public Order(String customerName, MyArrayList<Book> books) {
        this.customerName = customerName;
        this.books = books;
    }

    public String getCustomerName() {
        return customerName;
    }

    public MyArrayList<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer: ").append(customerName).append(", Books: ");
        for (int i = 0; i < books.size(); i++) {
            sb.append("\n  ").append(books.get(i).toString());
        }
        return sb.toString();
    }
}