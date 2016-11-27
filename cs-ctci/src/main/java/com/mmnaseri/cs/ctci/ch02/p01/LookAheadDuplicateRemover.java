package com.mmnaseri.cs.ctci.ch02.p01;

import com.mmnaseri.cs.ctci.ch02.SinglyLinkedNode;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 2:18 PM)
 */
public class LookAheadDuplicateRemover<E, N extends SinglyLinkedNode<E, N>> implements DuplicateRemover<E, N> {

    @Override
    public void removeDuplicates(N head) {
        N seen = head;
        while (seen != null) {
            N current = seen.next();
            N previous = seen;
            while (current != null) {
                if (current.data() == seen.data()) {
                    previous.setNext(current.next());
                } else {
                    previous = current;
                }
                current = current.next();
            }
            seen = seen.next();
        }
    }

}
