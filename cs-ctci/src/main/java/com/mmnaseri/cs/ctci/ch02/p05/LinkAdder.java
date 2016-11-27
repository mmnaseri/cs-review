package com.mmnaseri.cs.ctci.ch02.p05;

import com.mmnaseri.cs.ctci.ch02.SinglyLinkedNode;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 2:37 PM)
 */
public interface LinkAdder {

    <N extends SinglyLinkedNode<Integer, N>> SinglyLinkedNode<Integer, ?> add(N first, N second);

}
