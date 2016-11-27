package com.mmnaseri.cs.ctci.ch02.p04;

import com.mmnaseri.cs.ctci.ch02.SinglyLinkedNode;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 2:24 PM)
 */
public interface ListPartitioner<E extends Comparable<E>, N extends SinglyLinkedNode<E, N>> {

    N partition(N head, E pivot);

}
