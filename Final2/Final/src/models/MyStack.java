package models;

public class MyStack<T> {
    private MyArrayList<T> elements;

    public MyStack() {
        elements = new MyArrayList<>();
    }

    public void push(T element) {
        elements.add(element);
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T element = elements.get(elements.size() - 1);
        elements.remove(elements.size() - 1);
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return elements.get(elements.size() - 1);
    }

    public boolean isEmpty() {
        return elements.size() == 0;
    }

    public int size() {
        return elements.size();
    }

    public T get(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
        return elements.get(index);
    }

    public T remove(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
        T element = elements.get(index);
        elements.remove(index);
        return element;
    }
}