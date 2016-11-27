package com.mmnaseri.cs.ctci.ch02.p02;

import com.mmnaseri.cs.ctci.ch02.SinglyLinkedNode;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 1:35 PM)
 */
public class KthToLastFinderImpl<D, N extends SinglyLinkedNode<D, N>> implements KthToLastFinder<D, N> {

    @Override
    public N find(N head, int k) {
        if (head == null) {
            return null;
        }
        N current = head;
        N result = head;
        while (k >= 0) {
            k --;
            current = current.next();
            if (current == null) {
                return null;
            }
        }
        while (current.next() != null) {
            current = current.next();
            result = result.next();
        }
        return result;
    }

}
