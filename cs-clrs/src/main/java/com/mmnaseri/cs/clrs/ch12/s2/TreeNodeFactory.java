package com.mmnaseri.cs.clrs.ch12.s2;

import com.mmnaseri.cs.clrs.ch10.s4.TreeNode;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/18/15, 8:28 PM)
 */
public interface TreeNodeFactory<E, N extends TreeNode<E>> {

    N createNode(E value);

}
