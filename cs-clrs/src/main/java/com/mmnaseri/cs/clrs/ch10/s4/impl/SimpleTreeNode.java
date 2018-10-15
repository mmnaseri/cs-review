package com.mmnaseri.cs.clrs.ch10.s4.impl;

import com.mmnaseri.cs.clrs.ch10.s4.TreeNode;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/13/15, 12:22 AM)
 */
@Quality(Stage.TESTED)
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

    private int getIndex() {
        if (getParent() == null) {
            return -1;
        }
        final List<? extends TreeNode<E>> children = parent.getChildren();
        for (int i = 0; i < children.size(); i++) {
            TreeNode<E> node = children.get(i);
            if (node == this) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public TreeNode<E> getPreviousSibling() {
        final int index = getIndex();
        if (index <= 0) {
            return null;
        }
        return getParent().getChildren().get(index - 1);
    }

    @Override
    public TreeNode<E> getNextSibling() {
        final int index = getIndex();
        if (index == -1 || index >= getParent().getChildren().size() - 1) {
            return null;
        }
        return getParent().getChildren().get(index + 1);
    }

    @Override
    public TreeNode<E> getFirstChild() {
        return getChildren().isEmpty() ? null : getChildren().get(0);
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
        return String.valueOf(value) + (getChildren().isEmpty() ? "" : children.toString());
    }

}
