package com.mmnaseri.cs.clrs.ch18.s1;

import com.mmnaseri.cs.clrs.ch10.s4.impl.LevelLinkedTreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15)
 */
public class BTreeNode<I extends Indexed<K>, K extends Comparable<K>> extends LevelLinkedTreeNode<I> {

    private final DataStore<I, K> storage;
    private final NodeStore<K> nodeStore;
    private final List<K> before;
    private final List<K> after;
    private final List<K> keys;

    public BTreeNode(DataStore<I, K> storage, NodeStore<K> nodeStore, List<K> before, List<K> after) {
        this.storage = storage;
        this.nodeStore = nodeStore;
        this.before = before;
        this.after = after;
        keys = new ArrayList<>();
    }

    @Override
    public List<? extends BTreeNode<I, K>> getChildren() {
        //noinspection unchecked
        return (List<? extends BTreeNode<I, K>>) super.getChildren();
    }

    @Override
    public BTreeNode<I, K> getParent() {
        return (BTreeNode<I, K>) super.getParent();
    }

    @Override
    public BTreeNode<I, K> getFirstChild() {
        if (keys.isEmpty()) {
            return null;
        }
        final K key = keys.get(0);
        final I data = storage.read(key);
        final List<K> children = nodeStore.read(key);
        final BTreeNode<I, K> node = new BTreeNode<>(storage, nodeStore, Collections.<K>emptyList(), keys.subList(1, keys.size()));
        node.setValue(data);
        node.setParent(this);
        for (K child : children) {
            node.addKey(child);
        }
        return node;
    }

    @Override
    public BTreeNode<I, K> getNextSibling() {
        if (after.isEmpty()) {
            return null;
        }
        final List<K> previous = new ArrayList<>(before);
        final List<K> next = after.subList(1, after.size());
        final K key = after.get(0);
        final I data = storage.read(key);
        final List<K> children = nodeStore.read(key);
        final BTreeNode<I, K> node = new BTreeNode<>(storage, nodeStore, previous, next);
        node.setValue(data);
        node.setParent(getParent());
        for (K child : children) {
            node.addKey(child);
        }
        return node;
    }

    @Override
    public BTreeNode<I, K> getPreviousSibling() {
        if (before.isEmpty()) {
            return null;
        }
        final List<K> previous = before.subList(0, before.size() - 1);
        final List<K> next = new ArrayList<>(after);
        final K key = before.get(before.size() - 1);
        final I data = storage.read(key);
        final BTreeNode<I, K> node = new BTreeNode<>(storage, nodeStore, previous, next);
        node.setValue(data);
        node.setParent(getParent());
        return node;
    }

    public void addKey(K key) {
        keys.add(key);
    }

    public void removeKey(K key) {
        keys.remove(key);
    }

    public void removeKey(int index) {
        keys.remove(index);
    }

    public List<K> getKeys() {
        return Collections.unmodifiableList(keys);
    }

    @Override
    public boolean isLeaf() {
        return super.isLeaf() || getKeys().isEmpty();
    }

}
