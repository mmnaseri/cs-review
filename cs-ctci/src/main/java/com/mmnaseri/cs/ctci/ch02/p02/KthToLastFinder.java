package com.mmnaseri.cs.ctci.ch02.p02;

import com.mmnaseri.cs.ctci.ch02.SinglyLinkedNode;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 1:34 PM)
 */
public interface KthToLastFinder<D, N extends SinglyLinkedNode<D, N>> {

    N find(N head, int k);

}
