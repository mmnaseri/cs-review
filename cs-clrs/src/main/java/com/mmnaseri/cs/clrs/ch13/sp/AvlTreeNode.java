package com.mmnaseri.cs.clrs.ch13.sp;

import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/20/15, 12:51 AM)
 */
public class AvlTreeNode<E> extends BinaryTreeNode<E> {

    @Override
    public AvlTreeNode<E> getLeftChild() {
        return (AvlTreeNode<E>) super.getLeftChild();
    }

    @Override
    public AvlTreeNode<E> getRightChild() {
        return (AvlTreeNode<E>) super.getRightChild();
    }

    @Override
    public AvlTreeNode<E> getParent() {
        return (AvlTreeNode<E>) super.getParent();
    }

    public int getHeight() {
        if (isLeaf()) {
            return 1;
        } else {
            final int leftHeight;
            final int rightHeight;
            if (getLeftChild() != null) {
                leftHeight = getLeftChild().getHeight();
            } else {
                leftHeight = 0;
            }
            if (getRightChild() != null) {
                rightHeight = getRightChild().getHeight();
            } else {
                rightHeight = 0;
            }
            return 1 + Math.max(leftHeight, rightHeight);
        }
    }

    public int getBalancingFactor() {
        int rightHeight = getRightChild() == null ? 0 : getRightChild().getHeight();
        int leftHeight = getLeftChild() == null ? 0 : getLeftChild().getHeight();
        return rightHeight - leftHeight;
    }

}
