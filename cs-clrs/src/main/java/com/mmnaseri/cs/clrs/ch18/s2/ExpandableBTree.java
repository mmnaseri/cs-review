package com.mmnaseri.cs.clrs.ch18.s2;

import com.mmnaseri.cs.clrs.ch18.s1.*;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/27/15)
 */
@Quality(
    value = Stage.TESTED,
    explanation = "Stress tested with 100% coverage and further examined manually very closely")
public abstract class ExpandableBTree<I extends Indexed<K>, K extends Comparable<K>>
    extends AbstractBTree<I, K> {

  private BTreeNode<K> root;

  public ExpandableBTree(Storage<I> dataStore, Storage<NodeDefinition<K>> nodeStore, int degree) {
    super(dataStore, nodeStore, degree);
    init();
  }

  protected void init() {
    root = createNode(0);
    root.setLeaf(true);
    writeNode(root);
  }

  public BTreeNode<K> getRoot() {
    return root;
  }

  public void setRoot(BTreeNode<K> root) {
    this.root = root;
  }

  @Override
  public void insert(I value) {
    final BTreeNode<K> originalRoot = getRoot();
    if (isFull(originalRoot)) {
      final BTreeNode<K> root = createNode(0);
      root.setLeaf(false);
      originalRoot.setParent(root);
      // delete the original root node from disk
      deleteNode(getId(), 0);
      // write new root node definition unto the disk
      writeNode(root);
      writeNode(originalRoot);
      // rewrite original root node as a new regular internal node
      setRoot(root);
      split(root, 0);
      insertNonFull(root, value);
    } else {
      insertNonFull(originalRoot, value);
    }
  }

  private void split(BTreeNode<K> parent, int index) {
    final BTreeNode<K> child = loadChild(parent, index);
    final List<K> keys = child.getKeys();
    final int midpoint = getDegree() - 1;
    final List<K> left = new ArrayList<>(keys.subList(midpoint + 1, keys.size()));
    final K key = keys.get(midpoint);
    // shift everything one to the right on the parent side to make room for the new key
    for (int i = parent.getKeys().size(); i > index; i--) {
      moveInternals(parent, i, parent, i + 1);
    }
    // pull the key up to the parent
    parent.addKey(index, key);
    // create a new id for the newly spawned node
    final UUID uuid = UUID.randomUUID();
    // move items in the parent to make room for the new key
    for (int i = midpoint + 1; i <= keys.size(); i++) {
      moveInternals(child, i, uuid, i - midpoint - 1);
    }
    // remove keys from the original node
    while (child.getKeys().size() > midpoint) {
      child.removeKey(midpoint);
    }
    // create the new node
    final BTreeNode<K> node = createNode(uuid, index + 1);
    node.setLeaf(child.isLeaf());
    node.setParent(parent);
    // add the left-hand side keys to the newly created node
    for (K childKey : left) {
      node.addKey(childKey);
    }
    writeNode(child);
    writeNode(node);
    writeNode(parent);
  }

  private void insertNonFull(BTreeNode<K> node, I value) {
    int i = node.getKeys().size() - 1;
    if (node.isLeaf()) {
      while (i >= 0 && node.getKey(i).compareTo(value.getKey()) > 0) {
        moveInternals(node, i + 1, node, i + 2);
        i--;
      }
      i++;
      moveInternals(node, i, node.getId(), i + 1);
      node.addKey(i, value.getKey());
      writeNode(node);
      writeData(node.getId(), i, value);
    } else {
      while (i >= 0 && node.getKey(i).compareTo(value.getKey()) > 0) {
        i--;
      }
      i++;
      BTreeNode<K> child = loadChild(node, i);
      if (isFull(child)) {
        split(node, i);
        if (node.getKeys().get(i).compareTo(value.getKey()) < 0) {
          child = loadChild(node, i + 1);
        } else {
          child = loadChild(node, i);
        }
      }
      insertNonFull(child, value);
    }
  }

  private boolean isFull(BTreeNode<K> node) {
    return node.getKeys().size() == getDegree() * 2 - 1;
  }

  @Override
  public I find(K key) {
    DataLocation<K> result = find(getRoot(), key);
    if (result == null) {
      return null;
    }
    if (!result.getNode().isLeaf()) {
      result = findLeaf(loadChild(result.getNode(), result.getIndex()));
    }
    return readData(result.getNode().getId(), result.getIndex());
  }

  protected DataLocation<K> findLeaf(BTreeNode<K> node) {
    if (!node.isLeaf()) {
      return findLeaf(loadChild(node, node.getKeys().size()));
    } else {
      return new DataLocation<>(node, node.getKeys().size());
    }
  }

  @Override
  public I find(I value) {
    return find(value.getKey());
  }

  protected DataLocation<K> find(BTreeNode<K> root, K key) {
    int i = 0;
    while (i < root.getKeys().size() && root.getKey(i).compareTo(key) < 0) {
      i++;
    }
    if (i < root.getKeys().size() && root.getKey(i).equals(key)) {
      return new DataLocation<>(root, i);
    } else if (root.isLeaf()) {
      return null;
    } else {
      return find(loadChild(root, i), key);
    }
  }

  protected BTreeNode<K> loadChild(BTreeNode<K> parent, int child) {
    final NodeDefinition<K> definition = readNode(parent.getId(), child);
    final I value = readData(parent.getId(), child);
    final BTreeNode<K> node = createNode(definition.getId(), child);
    node.setLeaf(definition.isLeaf());
    node.setValue(value);
    node.setParent(parent);
    for (K childKey : definition.getKeys()) {
      node.addKey(childKey);
    }
    return node;
  }

  protected static class DataLocation<K extends Comparable<K>> {

    private final BTreeNode<K> node;
    private final int index;

    public DataLocation(BTreeNode<K> node, int index) {
      this.node = node;
      this.index = index;
    }

    public BTreeNode<K> getNode() {
      return node;
    }

    public int getIndex() {
      return index;
    }
  }
}
