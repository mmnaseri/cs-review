package com.mmnaseri.cs.clrs.ch18.s1;

import java.util.List;
import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15)
 */
public class NodeDefinition<K extends Comparable<K>> {

    private boolean leaf;
    private List<K> keys;
    private UUID id;

    public NodeDefinition() {
    }

    public NodeDefinition(boolean leaf, List<K> keys, UUID id) {
        this.leaf = leaf;
        this.keys = keys;
        this.id = id;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public List<K> getKeys() {
        return keys;
    }

    public void setKeys(List<K> keys) {
        this.keys = keys;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id.toString().substring(id.toString().length() - 4) + keys.toString();
    }
}
