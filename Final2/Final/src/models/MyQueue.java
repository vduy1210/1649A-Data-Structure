package models;

public class MyQueue<T> {
    private MyArrayList<T> elements;

    public MyQueue() {
        elements = new MyArrayList<>();
    }

    public void enqueue(T element) {
        elements.add(element);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T element = elements.get(0);
        elements.remove(0);
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return elements.get(0);
    }

    public boolean isEmpty() {
        return elements.size() == 0;
    }

    public int size() {
        return elements.size();
    }

    public MyArrayList<T> getElements() {
        return elements;
    }
}