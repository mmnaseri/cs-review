package com.mmnaseri.cs.clrs.ch18.s1;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15)
 */
public class NodeDefinition<K extends Comparable<K>> {

    private boolean leaf;
    private List<K> keys;

    public NodeDefinition() {
    }

    public NodeDefinition(boolean leaf, List<K> keys) {
        this.leaf = leaf;
        this.keys = keys;
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

}
