package interfaces;

import models.Book;

public interface BookManager {
    Book[] getBooksList();
    void addBook(Book book);
    void removeBook(Book book);
    Book findBookByTitle(String title);
    Book findBookByIndex(int index);
    boolean updateBookQuantity(String title, int quantity);
    void sortBooksByTitle();
    void shuffleBooks();
    void displayBooks();
}