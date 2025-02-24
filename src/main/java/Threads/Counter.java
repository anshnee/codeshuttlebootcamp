package Threads;

public class Counter {

    private int count = 0; // shared resource

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}




