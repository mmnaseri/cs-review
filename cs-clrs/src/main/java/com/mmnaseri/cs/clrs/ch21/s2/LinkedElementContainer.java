package com.mmnaseri.cs.clrs.ch21.s2;

import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 3:18 AM)
 */
public class LinkedElementContainer<E extends LinkedElement<E, I, ?>, I> {

    private UUID uuid;
    private E head;
    private E tail;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public E getHead() {
        return head;
    }

    public void setHead(E head) {
        this.head = head;
    }

    public E getTail() {
        return tail;
    }

    public void setTail(E tail) {
        this.tail = tail;
    }

    public void unlink() {
        uuid = null;
        head = null;
        tail = null;
    }
}
