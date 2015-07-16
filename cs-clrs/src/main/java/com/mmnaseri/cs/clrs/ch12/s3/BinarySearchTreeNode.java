package com.mmnaseri.cs.clrs.ch12.s3;

import com.mmnaseri.cs.clrs.ch12.s2.AbstractBinarySearchTreeNode;
import com.mmnaseri.cs.clrs.ch12.s2.SearchTreeNode;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/15/15, 3:54 PM)
 */
public class BinarySearchTreeNode<E extends Comparable<E>> extends AbstractBinarySearchTreeNode<E> {

    protected BinarySearchTreeNode<E> create(E value) {
        final BinarySearchTreeNode<E> node = new BinarySearchTreeNode<>();
        node.setValue(value);
        return node;
    }

    @Override
    public SearchTreeNode<E> insert(E value) {
        final BinarySearchTreeNode<E> child = create(value);
        if (getValue() == null || getValue().compareTo(value) > 0) {
            if (getLeftChild() == null) {
                setLeftChild(child);
            } else {
                return ((BinarySearchTreeNode<E>) getLeftChild()).insert(value);
            }
        } else {
            if (getRightChild() == null) {
                setRightChild(child);
            } else {
                return ((BinarySearchTreeNode<E>) getRightChild()).insert(value);
            }
        }
        return child;
    }

    @Override
    public void delete() {
        if (getLeftChild() == null && getRightChild() == null) {
            if (getParent() != null) {
                if (getParent().getRightChild() == this) {
                    getParent().deleteRightChild();
                } else {
                    getParent().deleteLeftChild();
                }
            }
        } else if (getLeftChild() == null) {
            if (getParent() != null) {
                if (getParent().getRightChild() == this) {
                    getParent().setRightChild(getRightChild());
                } else {
                    getParent().setLeftChild(getRightChild());
                }
            }
        } else if (getRightChild() == null) {
            if (getParent() != null) {
                if (getParent().getRightChild() == this) {
                    getParent().setRightChild(getLeftChild());
                } else {
                    getParent().setLeftChild(getLeftChild());
                }
            }
        } else {

        }
        setParent(null);
    }

    @Override
    public void delete(E value) {
        final SearchTreeNode<E> node = find(value);
        if (node != null) {
            node.delete();
        }
    }
}
