package com.mmnaseri.cs.clrs.ch12.s1;

import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/15/15, 12:40 PM)
 */
public class InOrderTreeWalkTest extends BaseTreeWalkTest {

    @Test
    @Override
    public void testWalkingOnALeafNode() throws Exception {
        final BinaryTreeNode<Integer> node = create(10, null, null);
        final TreeWalk<Integer, BinaryTreeNode<Integer>> walk = getTreeWalk();
        final LoggingTreeWalkCallback<Integer, BinaryTreeNode<Integer>> callback = new LoggingTreeWalkCallback<>();
        walk.perform(node, callback);
        assertThat(callback.getLog(), is(notNullValue()));
        assertThat(callback.getLog(), hasSize(1));
        assertThat(callback.getLog().get(0), is(node));
    }

    @Test
    @Override
    public void testWalkingWithALeftBranch() throws Exception {
        final BinaryTreeNode<Integer> node = create(10, create(11, create(12, create(13, null, null), null), null), null);
        final TreeWalk<Integer, BinaryTreeNode<Integer>> walk = getTreeWalk();
        final LoggingTreeWalkCallback<Integer, BinaryTreeNode<Integer>> callback = new LoggingTreeWalkCallback<>();
        walk.perform(node, callback);
        assertThat(callback.getLog(), is(notNullValue()));
        assertThat(callback.getLog(), hasSize(4));
        assertThat(callback.getLog().get(0).getValue(), is(13));
        assertThat(callback.getLog().get(1).getValue(), is(12));
        assertThat(callback.getLog().get(2).getValue(), is(11));
        assertThat(callback.getLog().get(3).getValue(), is(10));
    }

    @Test
    @Override
    public void testWalkingWithARightBranch() throws Exception {
        final BinaryTreeNode<Integer> node = create(10, null, create(11, null, create(12, null, create(13, null, null))));
        final TreeWalk<Integer, BinaryTreeNode<Integer>> walk = getTreeWalk();
        final LoggingTreeWalkCallback<Integer, BinaryTreeNode<Integer>> callback = new LoggingTreeWalkCallback<>();
        walk.perform(node, callback);
        assertThat(callback.getLog(), is(notNullValue()));
        assertThat(callback.getLog(), hasSize(4));
        assertThat(callback.getLog().get(0).getValue(), is(10));
        assertThat(callback.getLog().get(1).getValue(), is(11));
        assertThat(callback.getLog().get(2).getValue(), is(12));
        assertThat(callback.getLog().get(3).getValue(), is(13));
    }

    @Test
    @Override
    public void testWalkingWithAFullTree() throws Exception {
        final BinaryTreeNode<Integer> node = create(10, create(11, create(12, null, null), create(13, null, null)), create(14, create(15, null, null), create(16, null, null)));
        final TreeWalk<Integer, BinaryTreeNode<Integer>> walk = getTreeWalk();
        final LoggingTreeWalkCallback<Integer, BinaryTreeNode<Integer>> callback = new LoggingTreeWalkCallback<>();
        walk.perform(node, callback);
        assertThat(callback.getLog(), is(notNullValue()));
        assertThat(callback.getLog(), hasSize(7));
        assertThat(callback.getLog().get(0).getValue(), is(12));
        assertThat(callback.getLog().get(1).getValue(), is(11));
        assertThat(callback.getLog().get(2).getValue(), is(13));
        assertThat(callback.getLog().get(3).getValue(), is(10));
        assertThat(callback.getLog().get(4).getValue(), is(15));
        assertThat(callback.getLog().get(5).getValue(), is(14));
        assertThat(callback.getLog().get(6).getValue(), is(16));
    }

    protected TreeWalk<Integer, BinaryTreeNode<Integer>> getTreeWalk() {
        return new InOrderTreeWalk<>();
    }

}