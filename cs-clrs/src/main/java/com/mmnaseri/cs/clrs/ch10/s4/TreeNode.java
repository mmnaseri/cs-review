package com.mmnaseri.cs.clrs.ch10.s4;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/13/15, 12:21 AM)
 */
public interface TreeNode<E> {

    E getValue();

    void setValue(E value);

    List<? extends TreeNode<E>> getChildren();

    int addChild(TreeNode<E> child);

    void deleteChild(int index);

    TreeNode<E> getParent();

    void setParent(TreeNode<E> parent);

    TreeNode<E> getPreviousSibling();

    TreeNode<E> getNextSibling();

    TreeNode<E> getFirstChild();

    boolean isRoot();

    boolean isLeaf();

}
