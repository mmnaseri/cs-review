package com.mmnaseri.cs.ctci.ch02;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 1:34 PM)
 */
public interface SinglyLinkedNode<E, N extends SinglyLinkedNode<E, N>> {

    N next();

    E data();

    void setNext(N next);

    void setData(E data);

}
