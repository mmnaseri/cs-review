package com.mmnaseri.cs.clrs.ch18.s1;

import com.mmnaseri.cs.clrs.ch10.s4.impl.LevelLinkedTreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/27/15)
 */
public class BTreeNode<K extends Comparable<K>> extends LevelLinkedTreeNode<Object> {

  private final Storage<NodeDefinition<K>> nodeStore;
  private final List<K> keys;
  private final int index;
  private boolean leaf;
  private final UUID id;

  public BTreeNode(Storage<NodeDefinition<K>> nodeStore, int index, UUID id) {
    this.nodeStore = nodeStore;
    this.index = index;
    this.id = id;
    this.keys = new ArrayList<>();
  }

  @Override
  public List<? extends BTreeNode<K>> getChildren() {
    //noinspection unchecked
    return (List<? extends BTreeNode<K>>) super.getChildren();
  }

  @Override
  public BTreeNode<K> getParent() {
    return (BTreeNode<K>) super.getParent();
  }

  private BTreeNode<K> getNode(BTreeNode<K> parent, int index) {
    final NodeDefinition<K> definition = nodeStore.read(parent.getId(), index);
    if (definition == null) {
      return null;
    }
    final BTreeNode<K> node = new BTreeNode<>(nodeStore, index, definition.getId());
    node.setValue(null);
    node.setParent(parent);
    node.setLeaf(definition.isLeaf());
    for (K childKey : definition.getKeys()) {
      node.addKey(childKey);
    }
    return node;
  }

  @Override
  public BTreeNode<K> getFirstChild() {
    return getNode(this, 0);
  }

  @Override
  public BTreeNode<K> getNextSibling() {
    return getNode(getParent(), index + 1);
  }

  @Override
  public BTreeNode<K> getPreviousSibling() {
    return getNode(getParent(), index - 1);
  }

  public void addKey(K key) {
    keys.add(key);
  }

  public void addKey(int index, K key) {
    keys.add(index, key);
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

  public int getIndex() {
    return index;
  }

  @Override
  public String toString() {
    return id.toString().substring(id.toString().length() - 4) + keys;
  }
}
