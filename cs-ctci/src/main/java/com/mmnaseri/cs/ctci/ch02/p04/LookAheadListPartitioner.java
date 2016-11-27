package com.mmnaseri.cs.ctci.ch02.p04;

import com.mmnaseri.cs.ctci.ch02.SinglyLinkedNode;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 2:24 PM)
 */
public class LookAheadListPartitioner<E extends Comparable<E>, N extends SinglyLinkedNode<E, N>> implements ListPartitioner<E, N> {

    @Override
    public N partition(N head, E pivot) {
        if (head == null) {
            return null;
        }
        N left = head;
        N right = head;
        while (left != null && left.data().compareTo(pivot) >= 0) {
            left = left.next();
        }
        while (right != null && right.data().compareTo(pivot) < 0) {
            right = right.next();
        }
        if (right == null) {
            return null;
        }
        if (left == null) {
            return head;
        }
        if (right == head) {
            E temp = left.data();
            left.setData(right.data());
            right.setData(temp);
            N tempNode = right;
            right = left;
            left = tempNode;
        }
        N partitionPoint = right;
        while (right.next() != null) {
            if (right.next().data().compareTo(pivot) < 0) {
                final N tempNode = left.next();
                left.setNext(right.next());
                right.setNext(right.next().next());
                left.next().setNext(tempNode);
                left = left.next();
            } else {
                right = right.next();
            }
        }
        return partitionPoint;
    }

}
