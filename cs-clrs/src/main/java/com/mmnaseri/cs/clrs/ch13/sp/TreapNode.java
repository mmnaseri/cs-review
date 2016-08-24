package com.mmnaseri.cs.clrs.ch13.sp;

import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/26/15, 8:04 PM)
 */
public class TreapNode<E> extends BinaryTreeNode<E> {

    private int priority;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public TreapNode<E> getRightChild() {
        return (TreapNode<E>) super.getRightChild();
    }

    @Override
    public List<? extends TreapNode<E>> getChildren() {
        //noinspection unchecked
        return (List<? extends TreapNode<E>>) super.getChildren();
    }

    @Override
    public TreapNode<E> getLeftChild() {
        return (TreapNode<E>) super.getLeftChild();
    }

    @Override
    public TreapNode<E> getParent() {
        return (TreapNode<E>) super.getParent();
    }

}
