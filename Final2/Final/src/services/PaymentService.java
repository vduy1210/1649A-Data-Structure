package services;

import models.MyArrayList;
import models.Book;

public class PaymentService {
    public void payByCash(MyArrayList<Book> cart) {
        System.out.println("Payment by cash is successful.");
        cart.clear();
    }

    public void payByCreditCard(MyArrayList<Book> cart, String cardNumber, String expirationDate, String cvv) {
        System.out.println("Payment by credit card is successful.");
        cart.clear();
    }
}