package com.mmnaseri.cs.clrs.ch18.s1;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15)
 */
@Quality(Stage.UNTESTED)
public abstract class AbstractBTree<I extends Indexed<K>, K extends Comparable<K>> {

    private final DataStore<I> dataStore;
    private final NodeStore<K> nodeStore;
    private final int degree;

    public AbstractBTree(DataStore<I> dataStore, NodeStore<K> nodeStore, int degree) {
        this.dataStore = dataStore;
        this.nodeStore = nodeStore;
        this.degree = degree;
    }

    public abstract void insert(I value);

    public abstract void delete(I value);

    public abstract BTreeNode<I, K> find(I value);

    protected DataStore<I> getDataStore() {
        return dataStore;
    }

    protected NodeStore<K> getNodeStore() {
        return nodeStore;
    }

    public int getDegree() {
        return degree;
    }

}
