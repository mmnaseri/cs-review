package com.mmnaseri.cs.clrs.ch12.s1;

import com.mmnaseri.cs.clrs.ch10.s4.TreeNode;
import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/15/15, 12:44 PM)
 */
public abstract class BaseTreeWalkTest {

    protected BinaryTreeNode<Integer> create(Integer value, BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right) {
        final BinaryTreeNode<Integer> node = new BinaryTreeNode<>();
        node.setValue(value);
        node.setLeftChild(left);
        node.setRightChild(right);
        return node;
    }

    public abstract void testWalkingOnALeafNode() throws Exception;

    public abstract void testWalkingWithALeftBranch() throws Exception;

    public abstract void testWalkingWithARightBranch() throws Exception;

    public abstract void testWalkingWithAFullTree() throws Exception;

    protected static class LoggingTreeWalkCallback<E, N extends TreeNode<E>> implements TreeWalkCallback<E, N> {

        private final List<N> log = new ArrayList<>();

        @Override
        public void apply(N node) {
            log.add(node);
        }

        public List<N> getLog() {
            return log;
        }

    }

}
