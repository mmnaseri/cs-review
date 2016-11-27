package com.mmnaseri.cs.ctci.ch02.p01;

import com.mmnaseri.cs.ctci.ch02.DefaultSinglyLinkedNode;
import com.mmnaseri.cs.ctci.ch02.LinkedNodeBuilder;
import com.mmnaseri.cs.ctci.ch02.SinglyLinkedNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 2:08 PM)
 */
public class HashingDuplicateRemover<E, N extends SinglyLinkedNode<E, N>> implements DuplicateRemover<E, N> {

    @Override
    public void removeDuplicates(N head) {
        if (head == null) {
            return;
        }
        final Set<E> values = new HashSet<>();
        values.add(head.data());
        while (head.next() != null) {
            if (values.contains(head.next().data())) {
                head.setNext(head.next().next());
            } else {
                values.add(head.next().data());
                head = head.next();
            }
        }
    }

}
