package com.mmnaseri.cs.clrs.ch12.s1;

import com.mmnaseri.cs.clrs.ch10.s4.TreeNode;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/15/15, 12:34 PM)
 */
public interface TreeWalkCallback<E, N extends TreeNode<E>> {

    void apply(N node);

}
