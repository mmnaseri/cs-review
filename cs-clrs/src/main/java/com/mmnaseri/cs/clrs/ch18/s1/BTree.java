package com.mmnaseri.cs.clrs.ch18.s1;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15)
 */
@Quality(Stage.UNTESTED)
public abstract class BTree<I extends Indexed<K>, K extends Comparable<K>> {

    private final DataStore<I, K> dataStore;
    private final NodeStore<K> nodeStore;

    public BTree(DataStore<I, K> dataStore, NodeStore<K> nodeStore) {
        this.dataStore = dataStore;
        this.nodeStore = nodeStore;
    }

    public abstract void insert(I value);

    public abstract void delete(I value);

    public abstract BTreeNode<I, K> find(I value);

    protected DataStore<I, K> getDataStore() {
        return dataStore;
    }

    protected NodeStore<K> getNodeStore() {
        return nodeStore;
    }

}
