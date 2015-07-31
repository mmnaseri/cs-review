package com.mmnaseri.cs.clrs.ch18.s1;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15)
 */
@Quality(Stage.UNTESTED)
public abstract class AbstractBTree<I extends Indexed<K>, K extends Comparable<K>> {

    private final Storage<I> dataStore;
    private final Storage<NodeDefinition<K>> nodeStore;
    private final int degree;
    private final UUID id;

    public AbstractBTree(Storage<I> dataStore, Storage<NodeDefinition<K>> nodeStore, int degree) {
        this.dataStore = dataStore;
        this.nodeStore = nodeStore;
        this.degree = degree;
        if (degree < 2) {
            throw new IllegalArgumentException();
        }
        id = UUID.randomUUID();
    }

    protected UUID getId() {
        return id;
    }

    public abstract void insert(I value);

    public abstract void delete(I value);

    public abstract I find(K key);

    public abstract I find(I value);

    protected Storage<I> getDataStore() {
        return dataStore;
    }

    protected Storage<NodeDefinition<K>> getNodeStore() {
        return nodeStore;
    }

    public final int getDegree() {
        return degree;
    }

}
