package com.mmnaseri.cs.ctci.ch02;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 2:12 PM)
 */
public class DefaultSinglyLinkedNode<E> implements SinglyLinkedNode<E, DefaultSinglyLinkedNode<E>> {

    private DefaultSinglyLinkedNode<E> next;
    private E data;

    @Override
    public DefaultSinglyLinkedNode<E> next() {
        return next;
    }

    @Override
    public E data() {
        return data;
    }

    @Override
    public void setNext(DefaultSinglyLinkedNode<E> next) {
        this.next = next;
    }

    @Override
    public void setData(E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data + (next == null ? "" : " -> " + next);
    }

}
