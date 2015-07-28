package com.mmnaseri.cs.clrs.ch18.s1;

import com.mmnaseri.cs.clrs.ch10.s4.impl.LevelLinkedTreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
    private boolean leaf;
    private final UUID id;

    public BTreeNode(DataStore<I, K> storage, NodeStore<K> nodeStore, List<K> before, List<K> after) {
        this.storage = storage;
        this.nodeStore = nodeStore;
        this.before = before;
        this.after = after;
        keys = new ArrayList<>();
        id = UUID.randomUUID();
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
        final I data = storage.read(keys.get(0));
        final NodeDefinition<K> definition = nodeStore.read(getKey(), 0);
        final List<K> children = definition.getKeys();
        final BTreeNode<I, K> node = new BTreeNode<>(storage, nodeStore, Collections.<K>emptyList(), keys.subList(1, keys.size()));
        node.setValue(data);
        node.setParent(this);
        node.setLeaf(definition.isLeaf());
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
        final I data = storage.read(after.get(0));
        final NodeDefinition<K> definition = nodeStore.read(getKey(), previous.size());
        final List<K> children = definition.getKeys();
        final BTreeNode<I, K> node = new BTreeNode<>(storage, nodeStore, previous, next);
        node.setValue(data);
        node.setParent(getParent());
        node.setLeaf(definition.isLeaf());
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
        final I data = storage.read(before.get(before.size() - 1));
        final NodeDefinition<K> definition = nodeStore.read(getKey(), previous.size());
        final List<K> children = definition.getKeys();
        final BTreeNode<I, K> node = new BTreeNode<>(storage, nodeStore, previous, next);
        node.setValue(data);
        node.setParent(getParent());
        for (K child : children) {
            node.addKey(child);
        }
        return node;
    }

    public void addKey(K key) {
        keys.add(key);
    }

    public void addKey(int index, K key) {
        keys.add(index, key);
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
    
    public K getKey(int index) {
        return getKeys().get(index);
    }

    public K getKey() {
        return getValue() == null ? null : getValue().getKey();
    }

    @Override
    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public UUID getId() {
        return id;
    }

}
