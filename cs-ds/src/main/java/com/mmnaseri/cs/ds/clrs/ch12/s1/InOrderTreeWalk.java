package com.mmnaseri.cs.ds.clrs.ch12.s1;

import com.mmnaseri.cs.ds.clrs.ch10.s4.impl.BinaryTreeNode;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/15/15, 12:36 PM)
 */
public class InOrderTreeWalk<E, N extends BinaryTreeNode<E>> implements TreeWalk<E, N> {

    @SuppressWarnings("unchecked")
    @Override
    public void perform(N root, TreeWalkCallback<E, N> callback) {
        final BinaryTreeNode<E> leftChild = root.getLeftChild();
        final BinaryTreeNode<E> rightChild = root.getRightChild();
        if (leftChild != null) {
            perform((N) leftChild, callback);
        }
        callback.apply(root);
        if (rightChild != null) {
            perform((N) rightChild, callback);
        }
    }

}
