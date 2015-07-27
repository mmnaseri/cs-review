package com.mmnaseri.cs.clrs.ch10.s4.impl;

import com.mmnaseri.cs.clrs.ch10.s4.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/13/15, 12:38 AM)
 */
public class BinaryTreeNode<E> extends SimpleTreeNode<E> {

    public static final int RIGHT_CHILD = 1;
    public static final int LEFT_CHILD = 0;

    public BinaryTreeNode() {
        super.addChild(null);
        super.addChild(null);
    }

    @Override
    public int addChild(TreeNode<E> child) {
        if (getLeftChild() == null) {
            setLeftChild((BinaryTreeNode<E>) child);
            return LEFT_CHILD;
        } else if (getRightChild() == null) {
            setRightChild((BinaryTreeNode<E>) child);
            return getLeftChild() == null ? LEFT_CHILD : RIGHT_CHILD;
        }
        return -1;
    }

    @Override
    public List<? extends BinaryTreeNode<E>> getChildren() {
        final List<? extends TreeNode<E>> children = super.getChildren();
        final ArrayList<BinaryTreeNode<E>> result = new ArrayList<>();
        for (TreeNode<E> child : children) {
            if (child != null) {
                result.add((BinaryTreeNode<E>) child);
            }
        }
        return result;
    }

    @Override
    protected void setChild(int index, TreeNode<E> child) {
        if (index == LEFT_CHILD || index == RIGHT_CHILD) {
            super.setChild(index, child);
        }
    }

    public void setLeftChild(BinaryTreeNode<E> leftChild) {
        super.setChild(LEFT_CHILD, leftChild);
    }

    public void setRightChild(BinaryTreeNode<E> rightChild) {
        super.setChild(RIGHT_CHILD, rightChild);
    }

    public BinaryTreeNode<E> getLeftChild() {
        return (BinaryTreeNode<E>) super.getChildren().get(LEFT_CHILD);
    }

    public BinaryTreeNode<E> getRightChild() {
        return (BinaryTreeNode<E>) super.getChildren().get(RIGHT_CHILD);
    }

    public void deleteLeftChild() {
        deleteChild(LEFT_CHILD);
    }

    public void deleteRightChild() {
        deleteChild(RIGHT_CHILD);
    }

    @Override
    public BinaryTreeNode<E> getParent() {
        return (BinaryTreeNode<E>) super.getParent();
    }

    @Override
    public void deleteChild(int index) {
        if (index == LEFT_CHILD || index == RIGHT_CHILD) {
            final BinaryTreeNode<E> node = (BinaryTreeNode<E>) super.getChildren().get(index);
            if (node != null) {
                node.setParent(null);
            }
            setChild(index, null);
        }
    }

    @Override
    public boolean isLeaf() {
        return getLeftChild() == null && getRightChild() == null;
    }

    @Override
    public TreeNode<E> getPreviousSibling() {
        if (getParent() == null || this == getParent().getLeftChild()) {
            return null;
        }
        return getParent().getLeftChild();
    }

    @Override
    public TreeNode<E> getNextSibling() {
        if (getParent() == null || this == getParent().getRightChild()) {
            return null;
        }
        return getParent().getRightChild();
    }

}
