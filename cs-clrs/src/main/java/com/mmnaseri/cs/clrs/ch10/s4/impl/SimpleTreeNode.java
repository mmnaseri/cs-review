package com.mmnaseri.cs.clrs.ch10.s4.impl;

import com.mmnaseri.cs.clrs.ch10.s4.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/13/15, 12:22 AM)
 */
public class SimpleTreeNode<E> implements TreeNode<E> {

    private E value = null;
    private List<TreeNode<E>> children = new ArrayList<>();
    private TreeNode<E> parent = null;

    @Override
    public E getValue() {
        return value;
    }

    @Override
    public void setValue(E value) {
        this.value = value;
    }

    @Override
    public List<? extends TreeNode<E>> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public int addChild(TreeNode<E> child) {
        children.add(child);
        if (child != null) {
            child.setParent(this);
        }
        return children.size();
    }

    protected void setChild(int index, TreeNode<E> child) {
        if (child != null) {
            child.setParent(this);
        }
        children.set(index, child);
    }

    @Override
    public void deleteChild(int index) {
        final TreeNode<E> node = children.remove(index);
        if (node != null) {
            node.setParent(null);
        }
    }

    @Override
    public TreeNode<E> getParent() {
        return parent;
    }

    @Override
    public void setParent(TreeNode<E> parent) {
        this.parent = parent;
    }

    @Override
    public boolean isRoot() {
        return parent == null;
    }

    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public String toString() {
        return String.valueOf(value) + (children.isEmpty() ? "" : children.toString());
    }

}
