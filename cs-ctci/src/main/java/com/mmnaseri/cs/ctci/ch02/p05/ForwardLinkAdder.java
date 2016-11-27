package com.mmnaseri.cs.ctci.ch02.p05;

import com.mmnaseri.cs.ctci.ch02.DefaultSinglyLinkedNode;
import com.mmnaseri.cs.ctci.ch02.SinglyLinkedNode;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 2:38 PM)
 */
@SuppressWarnings({"unchecked", "RedundantTypeArguments"})
public class ForwardLinkAdder implements LinkAdder {

    @Override
    public <N extends SinglyLinkedNode<Integer, N>> SinglyLinkedNode<Integer, ?> add(N first, N second) {
        return add(first, second, 0);
    }

    private <N extends SinglyLinkedNode<Integer, N>> N add(N first, N second, int carry) {
        if (first == null && second == null) {
            if (carry == 0) {
                return null;
            } else {
                return this.<N>createNode(carry);
            }
        }
        final N node;
        final int addition;
        if (first == null) {
            addition = second.data() + carry;
        } else if (second == null) {
            addition = first.data() + carry;
        } else {
            addition = first.data() + second.data() + carry;
        }
        carry = addition / 10;
        node = this.<N>createNode(addition % 10);
        node.setNext(add(first == null ? null : first.next(), second == null ? null : second.next(), carry));
        return node;
    }

    private <N extends SinglyLinkedNode<Integer, N>> N createNode(Integer data) {
        final DefaultSinglyLinkedNode<Integer> node = new DefaultSinglyLinkedNode<>();
        node.setData(data);
        return (N) node;
    }

}
