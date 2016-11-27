package com.mmnaseri.cs.ctci.ch02.p03;

import com.mmnaseri.cs.ctci.ch02.SinglyLinkedNode;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 1:42 PM)
 */
public class MiddleDeleterImpl<E, N extends SinglyLinkedNode<E, N>> implements MiddleDeleter<E, N> {

    @Override
    public void delete(N node) {
        node.setData(node.next().data());
        node.setNext(node.next().next());
    }

}
