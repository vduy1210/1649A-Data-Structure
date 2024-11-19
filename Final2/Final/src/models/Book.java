package models;

public class Book {
    private String title;
    private String author;
    private int quantity;
    private double price;

    public Book(String title, String author, int quantity, double price) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Quantity: " + quantity + ", Price: $" + price;
    }
}