package com.mmnaseri.cs.clrs.ch12.s1;

import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/15/15, 12:36 PM)
 */
@Quality(Stage.TESTED)
public class InOrderTreeWalk<E, N extends BinaryTreeNode<E>> implements TreeWalk<E, N> {

    @SuppressWarnings("unchecked")
    @Override
    public <C extends TreeWalkCallback<E, N>> C perform(N root, C callback) {
        if (root == null) {
            return null;
        }
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
