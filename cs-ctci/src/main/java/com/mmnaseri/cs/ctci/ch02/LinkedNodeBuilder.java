package com.mmnaseri.cs.ctci.ch02;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 2:11 PM)
 */
public class LinkedNodeBuilder {

    @SafeVarargs
    public static <E> DefaultSinglyLinkedNode<E> buildSinglyLinkedList(E... items) {
        if (items.length == 0) {
            return null;
        }
        final DefaultSinglyLinkedNode<E> head = new DefaultSinglyLinkedNode<>();
        head.setData(items[0]);
        DefaultSinglyLinkedNode<E> current = head;
        for (int i = 1; i < items.length; i++) {
            final DefaultSinglyLinkedNode<E> node = new DefaultSinglyLinkedNode<>();
            node.setData(items[i]);
            current.setNext(node);
            current = node;
        }
        return head;
    }

}
