package com.mmnaseri.cs.clrs.ch12.s1;

import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/15/15, 2:49 PM)
 */
public class NonRecursiveInOrderTreeWalkTest extends InOrderTreeWalkTest {

    @Override
    protected TreeWalk<Integer, BinaryTreeNode<Integer>> getTreeWalk() {
        return new NonRecursiveInOrderTreeWalk<>();
    }
}
