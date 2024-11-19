package storage;

import models.Book;
import models.MyArrayList;

public class BookData {
    private MyArrayList<Book> booksList;

    public BookData() {
        booksList = new MyArrayList<>();
        initializeBooks();
    }

    private void initializeBooks() {
        booksList.add(new Book("Java Programming", "Author A", 10, 50.0));
        booksList.add(new Book("Data Structures and Algorithms", "Author B", 5, 60.0));
        booksList.add(new Book("Clean Code", "Robert C. Martin", 8, 45.0));
        booksList.add(new Book("The Pragmatic Programmer", "Andrew Hunt", 6, 55.0));
        booksList.add(new Book("Design Patterns", "Erich Gamma", 4, 70.0));
        booksList.add(new Book("Introduction to Algorithms", "Thomas H. Cormen", 7, 65.0));
        booksList.add(new Book("Effective Java", "Joshua Bloch", 12, 40.0));
        booksList.add(new Book("Artificial Intelligence: A Modern Approach", "Stuart Russell", 9, 75.0));
        booksList.add(new Book("Operating System Concepts", "Abraham Silberschatz", 5, 80.0));
        booksList.add(new Book("Database System Concepts", "Henry F. Korth", 6, 85.0));
        booksList.add(new Book("Computer Networks", "Andrew S. Tanenbaum", 10, 90.0));
        booksList.add(new Book("Python Crash Course", "Eric Matthes", 15, 35.0));
        booksList.add(new Book("Head First Design Patterns", "Eric Freeman", 7, 50.0));
        booksList.add(new Book("You Don't Know JS", "Kyle Simpson", 8, 30.0));
        booksList.add(new Book("Eloquent JavaScript", "Marijn Haverbeke", 10, 25.0));
        booksList.add(new Book("The Art of Computer Programming", "Donald Knuth", 3, 100.0));
        booksList.add(new Book("Refactoring", "Martin Fowler", 9, 60.0));
        booksList.add(new Book("Structure and Interpretation of Computer Programs", "Harold Abelson", 4, 55.0));
        booksList.add(new Book("Code Complete", "Steve McConnell", 6, 45.0));
        booksList.add(new Book("Algorithms Unlocked", "Thomas H. Cormen", 8, 40.0));
    }

    public MyArrayList<Book> getBooksList() {
        return booksList;
    }

    public void addBook(Book book) {
        booksList.add(book);
    }

    public void removeBook(Book book) {
        int index = booksList.indexOf(book);
        if (index != -1) {
            booksList.remove(index);
        }
    }

    public Book findBookByTitle(String title) {
        for (int i = 0; i < booksList.size(); i++) {
            Book book = booksList.get(i);
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public Book findBookByIndex(int index) {
        if (index >= 0 && index < booksList.size()) {
            return booksList.get(index);
        }
        return null;
    }

    public boolean updateBookQuantity(String title, int quantity) {
        Book book = findBookByTitle(title);
        if (book != null && book.getQuantity() >= quantity) {
            book.setQuantity(book.getQuantity() - quantity);
            return true;
        }
        return false;
    }

    public void displayBooks() {
        for (int i = 0; i < booksList.size(); i++) {
            System.out.println((i + 1) + ". " + booksList.get(i));
        }
    }
}