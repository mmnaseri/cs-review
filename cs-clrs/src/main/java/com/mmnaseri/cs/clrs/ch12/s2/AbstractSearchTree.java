package com.mmnaseri.cs.clrs.ch12.s2;

import com.mmnaseri.cs.clrs.ch10.s4.TreeNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/18/15, 8:16 PM)
 */
public abstract class AbstractSearchTree<E, N extends TreeNode<E>> implements SearchTree<E, N> {

    private final Comparator<E> comparator;
    private final TreeNodeFactory<E, N> factory;
    private N root;

    public AbstractSearchTree(Comparator<E> comparator, TreeNodeFactory<E, N> factory) {
        this.comparator = comparator;
        this.factory = factory;
    }

    protected int compare(E first, E second) {
        return comparator.compare(first, second);
    }

    protected int compare(N first, N second) {
        return compare(first.getValue(), second.getValue());
    }

    protected boolean lessThan(E first, E second) {
        return compare(first, second) < 0;
    }

    protected boolean lessThan(N first, N second) {
        return compare(first, second) < 0;
    }

    public N getRoot() {
        return root;
    }

    public void setRoot(N root) {
        this.root = root;
    }

    @SafeVarargs
    @Override
    public final List<N> insert(E... value) {
        final ArrayList<N> list = new ArrayList<>();
        for (E item : value) {
            list.add(insert(item));
        }
        return list;
    }

    protected TreeNodeFactory<E, N> getFactory() {
        return factory;
    }

    @Override
    public String toString() {
        return String.valueOf(root);
    }

}
