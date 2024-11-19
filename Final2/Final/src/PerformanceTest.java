
import java.util.Random;
import models.MyArrayList;
import models.MyQueue;
import models.MyStack;

public class PerformanceTest {

    public static void main(String[] args) {
        final int DATA_SIZE = 10000; // Size of the dataset for testing
        Random random = new Random();

        // Test MyArrayList
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        System.out.println("Testing MyArrayList...");
        long start = System.nanoTime();
        for (int i = 0; i < DATA_SIZE; i++) {
            myArrayList.add(random.nextInt());
        }
        long end = System.nanoTime();
        System.out.println("Add operation time: " + (end - start) + " ns");

        start = System.nanoTime();
        myArrayList.remove(DATA_SIZE / 2);
        end = System.nanoTime();
        System.out.println("Remove operation time: " + (end - start) + " ns");

        start = System.nanoTime();
        myArrayList.get(DATA_SIZE / 2);
        end = System.nanoTime();
        System.out.println("Get operation time: " + (end - start) + " ns");

        // Test MyQueue
        MyQueue<Integer> myQueue = new MyQueue<>();
        System.out.println("\nTesting MyQueue...");
        start = System.nanoTime();
        for (int i = 0; i < DATA_SIZE; i++) {
            myQueue.enqueue(random.nextInt());
        }
        end = System.nanoTime();
        System.out.println("Enqueue operation time: " + (end - start) + " ns");

        start = System.nanoTime();
        myQueue.dequeue();
        end = System.nanoTime();
        System.out.println("Dequeue operation time: " + (end - start) + " ns");

        // Test MyStack
        MyStack<Integer> myStack = new MyStack<>();
        System.out.println("\nTesting MyStack...");
        start = System.nanoTime();
        for (int i = 0; i < DATA_SIZE; i++) {
            myStack.push(random.nextInt());
        }
        end = System.nanoTime();
        System.out.println("Push operation time: " + (end - start) + " ns");

        start = System.nanoTime();
        myStack.pop();
        end = System.nanoTime();
        System.out.println("Pop operation time: " + (end - start) + " ns");

        // Test Insertion Sort
        MyArrayList<Integer> unsortedList = new MyArrayList<>();
        for (int i = 0; i < DATA_SIZE; i++) {
            unsortedList.add(random.nextInt());
        }
        System.out.println("\nTesting Insertion Sort...");
        start = System.nanoTime();
        insertionSort(unsortedList);
        end = System.nanoTime();
        System.out.println("Sorting time: " + (end - start) + " ns");

        // Test Linear Search
        System.out.println("\nTesting Linear Search...");
        int target = unsortedList.get(DATA_SIZE / 2); // Select a target to find
        start = System.nanoTime();
        linearSearch(unsortedList, target);
        end = System.nanoTime();
        System.out.println("Search time: " + (end - start) + " ns");
    }

    // Insertion sort implementation for MyArrayList
    public static void insertionSort(MyArrayList<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    // Linear search implementation for MyArrayList
    public static int linearSearch(MyArrayList<Integer> list, int target) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == target) {
                return i;
            }
        }
        return -1; // Not found
    }
}
