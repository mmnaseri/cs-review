package com.mmnaseri.cs.clrs.ch10.s1;

import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/12/15, 9:44 PM)
 */
@Quality(Stage.TESTED)
public class FixedSizeQueue<E> {

    private final Object[] items;
    private int tail;
    private int head;
    private int size;

    public FixedSizeQueue(int capacity) {
        items = new Object[Math.max(1, capacity)];
        tail = -1;
        head = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public int getSize() {
        return size;
    }

    public void enqueue(E item) {
        if (size == items.length) {
            throw new IllegalStateException("Queue is full");
        }
        size ++;
        items[head] = item;
        head ++;
        if (head == items.length) {
            head = 0;
        }
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        tail ++;
        size --;
        final Object object = items[tail];
        items[tail] = null;
        if (tail == items.length) {
            tail = -1;
        }
        //noinspection unchecked
        return (E) object;
    }

}
