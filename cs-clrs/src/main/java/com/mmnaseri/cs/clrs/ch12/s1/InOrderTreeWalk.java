package com.mmnaseri.cs.clrs.ch12.s1;

import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;
import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/15/15, 12:36 PM)
 */
@Quality(Stage.TESTED)
public class InOrderTreeWalk<E, N extends BinaryTreeNode<E>> implements TreeWalk<E, N> {

    @SuppressWarnings("unchecked")
    @Override
    public <C extends TreeWalkCallback<E, N>> C perform(N root, C callback) {
        final BinaryTreeNode<E> leftChild = root.getLeftChild();
        final BinaryTreeNode<E> rightChild = root.getRightChild();
        if (leftChild != null) {
            perform((N) leftChild, callback);
        }
        callback.apply(root);
        if (rightChild != null) {
            perform((N) rightChild, callback);
        }
        return callback;
    }
}
