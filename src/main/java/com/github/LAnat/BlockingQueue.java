package com.github.LAnat;

public class BlockingQueue<T> {
    private final Object notFull = new Object();
    private final Object notEmpty = new Object();

    private final T[] buffer;
    private int head = 0;
    private int tail = 0;
    private int count = 0;

    @SuppressWarnings("unchecked")
    public BlockingQueue(int capacity) {
        buffer = (T[]) new Object[capacity];
    }

    public void enqueue(T item) throws InterruptedException {
        synchronized (notFull) {
            while (count == buffer.length) {
                notFull.wait();
            }

            buffer[tail++] = item;
            tail = tail % buffer.length;
            count++;

            synchronized (notEmpty) {
                notEmpty.notify();
            }
        }
    }

    public T dequeue() throws InterruptedException {
        synchronized (notEmpty) {
            while (count == 0) {
                notEmpty.wait();
            }

            T item = buffer[head];
            buffer[head++] = null;
            head = head % buffer.length;
            count--;

            synchronized (notFull) {
                notFull.notify();
            }

            return item;
        }
    }

    public int size() {
        synchronized (notFull) {
            return count;
        }
    }
}
