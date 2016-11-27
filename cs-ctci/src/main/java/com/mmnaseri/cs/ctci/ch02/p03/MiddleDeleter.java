package com.mmnaseri.cs.ctci.ch02.p03;

import com.mmnaseri.cs.ctci.ch02.SinglyLinkedNode;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 1:42 PM)
 */
public interface MiddleDeleter<E, N extends SinglyLinkedNode<E, N>> {

    void delete(N node);

}
