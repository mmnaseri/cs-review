package com.mmnaseri.cs.clrs.ch13.s3;

import com.mmnaseri.cs.clrs.ch13.s1.NodeColor;
import org.testng.annotations.Test;

import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/20/15, 12:12 AM)
 */
public class RedBlackTreeTest {

    protected RedBlackTree<Integer> tree() {
        return new RedBlackTree<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
    }

    @Test
    public void testInsertingTheRoot() throws Exception {
        final RedBlackTree<Integer> tree = tree();
        tree.insert(10);
        assertThat(tree.getRoot(), is(notNullValue()));
        assertThat(tree.getRoot().getValue(), is(10));
        assertThat(tree.getRoot().getColor(), is(NodeColor.BLACK));
    }

    @Test
    public void testInsertingItem() throws Exception {
        final RedBlackTree<Integer> tree = tree();
        tree.insert(10, 7, 13);
        assertThat(tree.getRoot(), is(notNullValue()));
        assertThat(tree.getRoot().getValue(), is(10));
        assertThat(tree.getRoot().getColor(), is(NodeColor.BLACK));
        assertThat(tree.getRoot().getLeftChild(), is(notNullValue()));
        assertThat(tree.getRoot().getLeftChild().getValue(), is(7));
        assertThat(tree.getRoot().getLeftChild().getColor(), is(NodeColor.RED));
        assertThat(tree.getRoot().getRightChild(), is(notNullValue()));
        assertThat(tree.getRoot().getRightChild().getValue(), is(13));
        assertThat(tree.getRoot().getRightChild().getColor(), is(NodeColor.RED));
        tree.insert(15);
        assertThat(tree.getRoot(), is(notNullValue()));
        assertThat(tree.getRoot().getValue(), is(10));
        assertThat(tree.getRoot().getColor(), is(NodeColor.BLACK));
        assertThat(tree.getRoot().getLeftChild(), is(notNullValue()));
        assertThat(tree.getRoot().getLeftChild().getValue(), is(7));
        assertThat(tree.getRoot().getLeftChild().getColor(), is(NodeColor.BLACK));
        assertThat(tree.getRoot().getRightChild(), is(notNullValue()));
        assertThat(tree.getRoot().getRightChild().getValue(), is(13));
        assertThat(tree.getRoot().getRightChild().getColor(), is(NodeColor.BLACK));
        assertThat(tree.getRoot().getRightChild().getRightChild(), is(notNullValue()));
        assertThat(tree.getRoot().getRightChild().getRightChild().getValue(), is(15));
        assertThat(tree.getRoot().getRightChild().getRightChild().getColor(), is(NodeColor.RED));
    }

    @Test
    public void testDeletingItem() throws Exception {
        final RedBlackTree<Integer> tree = tree();
        tree.insert(10, 7, 13, 15);
        tree.delete(13);
        assertThat(tree.getRoot(), is(notNullValue()));
        assertThat(tree.getRoot().getValue(), is(10));
        assertThat(tree.getRoot().getColor(), is(NodeColor.BLACK));
        assertThat(tree.getRoot().getLeftChild(), is(notNullValue()));
        assertThat(tree.getRoot().getLeftChild().getValue(), is(7));
        assertThat(tree.getRoot().getLeftChild().getColor(), is(NodeColor.BLACK));
        assertThat(tree.getRoot().getRightChild(), is(notNullValue()));
        assertThat(tree.getRoot().getRightChild().getValue(), is(15));
        assertThat(tree.getRoot().getRightChild().getColor(), is(NodeColor.BLACK));
    }



}