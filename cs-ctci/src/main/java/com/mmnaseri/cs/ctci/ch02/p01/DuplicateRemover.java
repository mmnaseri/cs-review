package com.mmnaseri.cs.ctci.ch02.p01;

import com.mmnaseri.cs.ctci.ch02.SinglyLinkedNode;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 2:08 PM)
 */
public interface DuplicateRemover<E, N extends SinglyLinkedNode<E, N>> {

    void removeDuplicates(N head);

}
