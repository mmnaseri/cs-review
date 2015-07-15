package com.mmnaseri.cs.clrs.ch12.s1;

import com.mmnaseri.cs.clrs.ch10.s4.TreeNode;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/15/15, 12:39 PM)
 */
public class PreOrderTreeWalk<E, N extends TreeNode<E>> implements TreeWalk<E, N> {

    @Override
    public void perform(N root, TreeWalkCallback<E, N> callback) {
        callback.apply(root);
        for (TreeNode<E> child : root.getChildren()) {
            if (child != null) {
                //noinspection unchecked
                perform((N) child, callback);
            }
        }
    }

}
