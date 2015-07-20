package com.mmnaseri.cs.clrs.ch14.s3;

import com.mmnaseri.cs.clrs.ch13.s1.RedBlackTreeNode;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/20/15, 4:20 AM)
 */
public class IntervalTreeNode extends RedBlackTreeNode<Interval> {

    public int getMaximum() {
        return Math.max(getValue().getEnd(), Math.max(getLeftChild() == null ? Integer.MIN_VALUE : getLeftChild().getValue().getEnd(), getRightChild() == null ? Integer.MIN_VALUE : getRightChild().getValue().getEnd()));
    }

    @Override
    public IntervalTreeNode getParent() {
        return (IntervalTreeNode) super.getParent();
    }

    @Override
    public IntervalTreeNode getLeftChild() {
        return (IntervalTreeNode) super.getLeftChild();
    }

    @Override
    public IntervalTreeNode getRightChild() {
        return (IntervalTreeNode) super.getRightChild();
    }
}
