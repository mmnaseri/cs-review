package com.mmnaseri.cs.clrs.ch21.s1;

import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 2:55 AM)
 */
class ElementContainer<E> {

    private SimpleElement<E> head;
    private SimpleElement<E> tail;
    private UUID set;

    ElementContainer(UUID set) {
        this.set = set;
    }

    UUID getSet() {
        return set;
    }

    SimpleElement<E> getHead() {
        return head;
    }

    void setHead(SimpleElement<E> head) {
        this.head = head;
    }

    SimpleElement<E> getTail() {
        return tail;
    }

    void setTail(SimpleElement<E> tail) {
        this.tail = tail;
    }

    void unlink() {
        head = null;
        tail = null;
        set = null;
    }

}
