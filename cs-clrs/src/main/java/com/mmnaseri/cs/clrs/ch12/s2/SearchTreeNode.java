package com.mmnaseri.cs.clrs.ch12.s2;

import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/15/15, 3:04 PM)
 */
public abstract class SearchTreeNode<E extends Comparable<E>> extends BinaryTreeNode<E> {

    public abstract SearchTreeNode<E> find(E value);

    public abstract SearchTreeNode<E> minimum();

    public abstract SearchTreeNode<E> maximum();

    public abstract SearchTreeNode<E> predecessor();

    public abstract SearchTreeNode<E> successor();

    public abstract SearchTreeNode<E> insert(E value);

    public abstract void delete(E value);

    public abstract void delete();

}
