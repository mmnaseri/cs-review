package com.mmnaseri.cs.clrs.ch12.s4;

import com.mmnaseri.cs.clrs.ch12.s3.BinarySearchTree;
import com.mmnaseri.cs.clrs.ch5.s3.InPlacePermuter;
import com.mmnaseri.cs.clrs.common.Permuter;
import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/18/15, 10:04 PM)
 */
@Quality(Stage.UNTESTED)
public class RandomBinarySearchTreeBuilder<E> {

    private final BinarySearchTree<E, ?> tree;
    private final Permuter<E> permuter;

    public RandomBinarySearchTreeBuilder(BinarySearchTree<E, ?> tree) {
        this.tree = tree;
        permuter = new InPlacePermuter<>();
    }

    public void build(E[] values) {
        permuter.permute(values);
        tree.insert(values);
    }

}
