package com.mmnaseri.cs.clrs.ch10.s4.impl;

import com.mmnaseri.cs.clrs.ch10.s4.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15)
 */
public class LevelLinkedTreeNode<E> implements TreeNode<E> {

    private E value;
    private LevelLinkedTreeNode<E> parent;
    private LevelLinkedTreeNode<E> firstChild;
    private LevelLinkedTreeNode<E> nextSibling;
    private LevelLinkedTreeNode<E> previousSibling;

    @Override
    public E getValue() {
        return value;
    }

    @Override
    public void setValue(E value) {
        this.value = value;
    }

    @Override
    public List<? extends LevelLinkedTreeNode<E>> getChildren() {
        final List<LevelLinkedTreeNode<E>> children = new ArrayList<>();
        LevelLinkedTreeNode<E> node = getFirstChild();
        while (node != null) {
            children.add(node);
            node = node.getNextSibling();
        }
        return children;
    }

    @Override
    public int addChild(TreeNode<E> child) {
        int index = 0;
        final LevelLinkedTreeNode<E> sibling = (LevelLinkedTreeNode<E>) child;
        sibling.setParent(this);
        if (firstChild == null) {
            setFirstChild(sibling);
        } else {
            final List<? extends LevelLinkedTreeNode<E>> children = getChildren();
            index = children.size();
            final LevelLinkedTreeNode<E> lastChild = children.get(index - 1);
            lastChild.setNextSibling(sibling);
            sibling.setPreviousSibling(lastChild);
        }
        return index;
    }

    @Override
    public void deleteChild(int index) {
        LevelLinkedTreeNode<E> node = this.firstChild;
        while (index > 0 ) {
            if (node == null) {
                return;
            }
            node = node.getNextSibling();
            index --;
        }
        deleteChild(node);
    }

    public void deleteChild(LevelLinkedTreeNode<E> node) {
        if (node.getParent() != this) {
            return;
        }
        node.setParent(null);
        if (node.getPreviousSibling() == null) {
            setFirstChild(node.getNextSibling());
        }
        if (node.getNextSibling() != null) {
            node.getNextSibling().setPreviousSibling(node.getPreviousSibling());
        }
        node.setNextSibling(null);
        node.setPreviousSibling(null);
    }

    @Override
    public boolean isRoot() {
        return getParent() == null;
    }

    @Override
    public boolean isLeaf() {
        return getFirstChild() == null;
    }

    @Override
    public LevelLinkedTreeNode<E> getParent() {
        return parent;
    }

    @Override
    public void setParent(TreeNode<E> parent) {
        this.parent = (LevelLinkedTreeNode<E>) parent;
    }

    @Override
    public LevelLinkedTreeNode<E> getFirstChild() {
        return firstChild;
    }

    public void setFirstChild(LevelLinkedTreeNode<E> firstChild) {
        this.firstChild = firstChild;
    }

    @Override
    public LevelLinkedTreeNode<E> getNextSibling() {
        return nextSibling;
    }

    public void setNextSibling(LevelLinkedTreeNode<E> nextSibling) {
        this.nextSibling = nextSibling;
    }

    @Override
    public LevelLinkedTreeNode<E> getPreviousSibling() {
        return previousSibling;
    }

    public void setPreviousSibling(LevelLinkedTreeNode<E> previousSibling) {
        this.previousSibling = previousSibling;
    }

}
