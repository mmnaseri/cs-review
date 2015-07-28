package com.mmnaseri.cs.clrs.ch18.s2;

import com.mmnaseri.cs.clrs.ch18.s1.*;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15)
 */
@Quality(Stage.INCOMPLETE)
public abstract class ExpandableBTree<I extends Indexed<K>, K extends Comparable<K>> extends AbstractBTree<I, K> {

    private BTreeNode<I, K> root;

    public ExpandableBTree(DataStore<I, K> dataStore, NodeStore<K> nodeStore) {
        super(dataStore, nodeStore);
    }

    public BTreeNode<I, K> getRoot() {
        return root;
    }

    public void setRoot(BTreeNode<I, K> root) {
        this.root = root;
    }

    @Override
    public void insert(I value) {
        throw new UnsupportedOperationException("This operation has not been implemented yet");
    }

    @Override
    public BTreeNode<I, K> find(I value) {
        if (getRoot() == null) {
            return null;
        }
        return find(getRoot(), value);
    }

    protected BTreeNode<I, K> find(BTreeNode<I, K> root, I value) {
        if (value.equals(root.getValue())) {
            return root;
        }
        if (root.isLeaf()) {
            return null;
        }
        final List<K> keys = root.getKeys();
        final K key = value.getKey();
        for (int i = 0; i < keys.size(); i++) {
            K target = keys.get(i);
            if (target.compareTo(key) > 0) {
                return find(assemble(root, i), value);
            }
        }
        return find(assemble(root, keys.size() - 1), value);
    }

    protected BTreeNode<I, K> assemble(BTreeNode<I, K> parent, int child) {
        final List<K> keys = parent.getKeys();
        final K key = keys.get(child);
        final I data = getDataStore().read(key);
        final List<K> children = getNodeStore().read(key);
        final List<K> before = keys.subList(0, child);
        final List<K> after = keys.subList(child + 1, keys.size());
        final BTreeNode<I, K> node = new BTreeNode<>(getDataStore(), getNodeStore(), before, after);
        node.setValue(data);
        for (K childKey : children) {
            node.addKey(childKey);
        }
        return node;
    }

}
