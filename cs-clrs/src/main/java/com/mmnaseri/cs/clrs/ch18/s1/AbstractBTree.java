package com.mmnaseri.cs.clrs.ch18.s1;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
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

    public final int getDegree() {
        return degree;
    }

    protected BTreeNode<K> createNode(int index) {
        return createNode(UUID.randomUUID(), index);
    }

    protected BTreeNode<K> createNode(UUID id, int index) {
        return new BTreeNode<>(nodeStore, index, id);
    }

    protected void writeNode(BTreeNode<K> node) {
        final NodeDefinition<K> definition = new NodeDefinition<>(node.isLeaf(), node.getKeys(), node.getId());
        if (!node.isRoot()) {
            nodeStore.write(node.getParent().getId(), node.getIndex(), definition);
        } else {
            nodeStore.write(getId(), 0, definition);
        }
    }

    protected void moveInternals(BTreeNode<K> source, int sourceIndex, BTreeNode<K> target, int targetIndex) {
        moveInternals(source, sourceIndex, target.getId(), targetIndex);
    }

    protected void moveInternals(BTreeNode<K> source, int sourceIndex, UUID target, int targetIndex) {
        if (source.isLeaf()) {
            dataStore.move(source.getId(), sourceIndex, target, targetIndex);
        } else {
            nodeStore.move(source.getId(), sourceIndex, target, targetIndex);
        }
    }

    protected void deleteNode(UUID id, int index) {
        nodeStore.delete(id, index);
    }

    protected NodeDefinition<K> readNode(UUID id, int index) {
        return nodeStore.read(id, index);
    }

    protected void writeData(UUID id, int index, I value) {
        dataStore.write(id, index, value);
    }

    protected I readData(UUID id, int index) {
        return dataStore.read(id, index);
    }

    protected void deleteData(UUID id, int index) {
        dataStore.delete(id, index);
    }
}
