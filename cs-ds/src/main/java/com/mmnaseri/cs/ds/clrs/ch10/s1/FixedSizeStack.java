package com.mmnaseri.cs.ds.clrs.ch10.s1;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/12/15, 9:29 PM)
 */
public class FixedSizeStack<E> {

    private final Object[] items;
    private int top;

    public FixedSizeStack(int capacity) {
        items = new Object[capacity];
        top = 0;
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public void push(E item) {
        if (top < items.length) {
            items[top ++] = item;
        } else {
            throw new IllegalStateException("Stack is full");
        }
    }

    public E pop() {
        if (top == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        //noinspection unchecked
        return (E) items[-- top];
    }

    public int getSize() {
        return top;
    }

}
