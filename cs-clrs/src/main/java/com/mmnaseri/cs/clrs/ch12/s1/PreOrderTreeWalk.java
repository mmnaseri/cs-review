package com.mmnaseri.cs.clrs.ch12.s1;

import com.mmnaseri.cs.clrs.ch10.s4.TreeNode;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/15/15, 12:39 PM)
 */
@Quality(Stage.TESTED)
public class PreOrderTreeWalk<E, N extends TreeNode<E>> implements TreeWalk<E, N> {

    @Override
    public <C extends TreeWalkCallback<E, N>> C perform(N root, C callback) {
        if (root == null) {
            return null;
        }
        callback.apply(root);
        for (TreeNode<E> child : root.getChildren()) {
            if (child != null) {
                //noinspection unchecked
                perform((N) child, callback);
            }
        }
        return callback;
    }

}
