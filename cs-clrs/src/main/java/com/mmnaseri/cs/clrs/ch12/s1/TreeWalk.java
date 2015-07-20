package com.mmnaseri.cs.clrs.ch12.s1;

import com.mmnaseri.cs.clrs.ch10.s4.TreeNode;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/15/15, 12:34 PM)
 */
public interface TreeWalk<E, N extends TreeNode<E>> {

    <C extends TreeWalkCallback<E, N>> C perform(N root, C callback);

}
