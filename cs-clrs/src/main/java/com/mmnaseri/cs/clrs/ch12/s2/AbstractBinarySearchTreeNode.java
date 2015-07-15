package com.mmnaseri.cs.clrs.ch12.s2;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/15/15, 3:06 PM)
 */
public abstract class AbstractBinarySearchTreeNode<E extends Comparable<E>> extends SearchTreeNode<E> {

    @Override
    public SearchTreeNode<E> minimum() {
        return getLeftChild() == null ? this : ((SearchTreeNode<E>) getLeftChild()).minimum();
    }

    @Override
    public SearchTreeNode<E> maximum() {
        return getRightChild() == null ? this : ((SearchTreeNode<E>) getRightChild()).maximum();
    }

    @Override
    public SearchTreeNode<E> predecessor() {
        if (getLeftChild() != null) {
            return ((SearchTreeNode<E>) getLeftChild()).maximum();
        }
        SearchTreeNode<E> current = this;
        SearchTreeNode<E> parent = (SearchTreeNode<E>) getParent();
        while (parent != null && current == parent.getLeftChild()) {
            current = parent;
            parent = (SearchTreeNode<E>) parent.getParent();
        }
        return parent;
    }

    @Override
    public SearchTreeNode<E> successor() {
        if (getRightChild() != null) {
            return ((SearchTreeNode<E>) getRightChild()).minimum();
        }
        SearchTreeNode<E> current = this;
        SearchTreeNode<E> parent = (SearchTreeNode<E>) getParent();
        while (parent != null && current == parent.getRightChild()) {
            current = parent;
            parent = (SearchTreeNode<E>) parent.getParent();
        }
        return parent;
    }

    @Override
    public SearchTreeNode<E> find(E value) {
        if (getValue() != null && getValue().equals(value) || getValue() == null && value == null) {
            return this;
        }
        if (getValue() == null) {
            return null;
        }
        if (getValue().compareTo(value) > 0) {
            return getLeftChild() == null ? null : ((SearchTreeNode<E>) getLeftChild()).find(value);
        } else {
            return getRightChild() == null ? null : ((SearchTreeNode<E>) getRightChild()).find(value);
        }
    }

}
