package com.mmnaseri.cs.ds.clrs.ch12.s1;

import com.mmnaseri.cs.ds.clrs.ch10.s4.TreeNode;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/15/15, 12:34 PM)
 */
public interface TreeWalk<E, N extends TreeNode<E>> {

    void perform(N root, TreeWalkCallback<E, N> callback);

}
