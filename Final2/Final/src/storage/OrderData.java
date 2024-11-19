package storage;

import models.Order;
import models.MyQueue;

public class OrderData {
    private MyQueue<Order> orderQueue;

    public OrderData() {
        orderQueue = new MyQueue<>();
    }

    public void addOrder(Order order) {
        orderQueue.enqueue(order);
    }

    public MyQueue<Order> getOrderQueue() {
        return orderQueue;
    }

    public Order findOrderByUsername(String username) {
        for (int i = 0; i < orderQueue.size(); i++) {
            Order order = orderQueue.getElements().get(i);
            if (order.getCustomerName().equalsIgnoreCase(username)) {
                return order;
            }
        }
        return null;
    }

    public Order findOrderByNumber(int orderNumber) {
        if (orderNumber >= 0 && orderNumber < orderQueue.size()) {
            return orderQueue.getElements().get(orderNumber);
        }
        return null;
    }
}