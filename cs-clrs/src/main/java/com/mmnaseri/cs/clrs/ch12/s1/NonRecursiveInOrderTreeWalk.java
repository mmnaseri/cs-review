package com.mmnaseri.cs.clrs.ch12.s1;

import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;
import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

import java.util.Stack;

/**
 * As described by exercise 12.1-3
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/15/15, 1:32 PM)
 */
@Quality(Stage.TESTED)
public class NonRecursiveInOrderTreeWalk<E, N extends BinaryTreeNode<E>> implements TreeWalk<E, N> {

    @SuppressWarnings("unchecked")
    @Override
    public <C extends TreeWalkCallback<E, N>> C perform(N root, C callback) {
        final Stack<N> stack = new Stack<>();
        N current = root;
        boolean popped = false;
        do {
            if (current.getLeftChild() == null || popped) {
                callback.apply(current);
                if (current.getRightChild() != null) {
                    current = (N) current.getRightChild();
                    popped = false;
                } else if (!stack.isEmpty()) {
                    current = stack.pop();
                    popped = true;
                } else {
                    break;
                }
            } else {
                stack.push(current);
                current = (N) current.getLeftChild();
                popped = false;
            }
        } while (true);
        return callback;
    }

}
