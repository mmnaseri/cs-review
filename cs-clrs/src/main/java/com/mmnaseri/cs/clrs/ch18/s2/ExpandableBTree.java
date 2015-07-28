package com.mmnaseri.cs.clrs.ch18.s2;

import com.mmnaseri.cs.clrs.ch18.s1.*;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15)
 */
@Quality(Stage.INCOMPLETE)
public abstract class ExpandableBTree<I extends Indexed<K>, K extends Comparable<K>> extends AbstractBTree<I, K> {

    private BTreeNode<I, K> root;

    public ExpandableBTree(DataStore<I> dataStore, NodeStore<K> nodeStore, int degree) {
        super(dataStore, nodeStore, degree);
        init();
    }

    protected void init() {
        final List<K> siblings = new ArrayList<>();
        root = new BTreeNode<>(getDataStore(), getNodeStore(), siblings, 0, UUID.randomUUID());
        root.setLeaf(true);
    }

    public BTreeNode<I, K> getRoot() {
        return root;
    }

    public void setRoot(BTreeNode<I, K> root) {
        this.root = root;
    }

    @Override
    public void insert(I value) {
        throw new UnsupportedOperationException();
    }

    protected void split(BTreeNode<I, K> parent, int index) {
        throw new UnsupportedOperationException();
    }

    protected void insertNonFull(BTreeNode<I, K> node, I value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BTreeNode<I, K> find(I value) {
        return find(getRoot(), value);
    }

    protected BTreeNode<I, K> find(BTreeNode<I, K> root, I value) {
        int i = 0;
        while (i < root.getKeys().size() && root.getKey(i).compareTo(value.getKey()) < 0) {
            i ++;
        }
        if (i < root.getKeys().size() && root.getKey(i).equals(value.getKey())) {
            return root;
        } else if (root.isLeaf()) {
            return null;
        } else {
            return find(assemble(root, i), value);
        }
    }

    protected BTreeNode<I, K> assemble(BTreeNode<I, K> parent, int child) {
        final NodeDefinition<K> definition = getNodeStore().read(parent.getId(), child);
        final I value = getDataStore().read(definition.getId());
        final BTreeNode<I, K> node = new BTreeNode<>(getDataStore(), getNodeStore(), parent.getKeys(), child, definition.getId());
        node.setLeaf(definition.isLeaf());
        node.setValue(value);
        for (K childKey : definition.getKeys()) {
            node.addKey(childKey);
        }
        return node;
    }

}
