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
@Quality(value = Stage.TESTED, explanation = "Stress tested with 100% coverage and further examined manually very closely")
public abstract class ExpandableBTree<I extends Indexed<K>, K extends Comparable<K>> extends AbstractBTree<I, K> {

    private BTreeNode<K> root;

    public ExpandableBTree(Storage<I> dataStore, Storage<NodeDefinition<K>> nodeStore, int degree) {
        super(dataStore, nodeStore, degree);
        init();
    }

    protected void init() {
        root = new BTreeNode<>(getNodeStore(), 0, UUID.randomUUID());
        root.setLeaf(true);
        getNodeStore().write(getId(), 0, new NodeDefinition<>(true, root.getKeys(), root.getId()));
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
            final BTreeNode<K> root = new BTreeNode<>(getNodeStore(), 0, UUID.randomUUID());
            root.setLeaf(false);
            originalRoot.setParent(root);
            //delete the original root node from disk
            getNodeStore().delete(getId(), 0);
            //write new root node definition unto the disk
            getNodeStore().write(getId(), 0, new NodeDefinition<>(root.isLeaf(), root.getKeys(), root.getId()));
            //rewrite original root node as a new regular internal node
            getNodeStore().write(root.getId(), 0, new NodeDefinition<>(originalRoot.isLeaf(), originalRoot.getKeys(), originalRoot.getId()));
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
        //shift everything one to the right on the parent side to make room for the new key
        for (int i = parent.getKeys().size(); i > index; i --) {
            moveInternals(parent, i, parent, i + 1);
        }
        //pull the key up to the parent
        parent.addKey(index, key);
        //create a new id for the newly spawned node
        final UUID uuid = UUID.randomUUID();
        //move items in the parent to make room for the new key
        for (int i = midpoint + 1; i <= keys.size(); i ++) {
            moveInternals(child, i, uuid, i - midpoint - 1);
        }
        //remove keys from the original node
        while (child.getKeys().size() > midpoint) {
            child.removeKey(midpoint);
        }
        //create the new node
        final BTreeNode<K> node = new BTreeNode<>(getNodeStore(), index + 1, uuid);
        node.setLeaf(child.isLeaf());
        node.setParent(parent);
        //add the left-hand side keys to the newly created node
        for (K childKey : left) {
            node.addKey(childKey);
        }
        getNodeStore().write(parent.getId(), index, new NodeDefinition<>(child.isLeaf(), child.getKeys(), child.getId()));
        getNodeStore().write(parent.getId(), index + 1, new NodeDefinition<>(child.isLeaf(), left, uuid));
        if (!parent.isRoot()) {
            getNodeStore().write(parent.getParent().getId(), parent.getIndex(), new NodeDefinition<>(parent.isLeaf(), parent.getKeys(), parent.getId()));
        }
    }

    private void insertNonFull(BTreeNode<K> node, I value) {
        int i = node.getKeys().size() - 1;
        if (node.isLeaf()) {
            while (i >= 0 && node.getKey(i).compareTo(value.getKey()) > 0) {
                moveInternals(node, i + 1, node, i + 2);
                i --;
            }
            i ++;
            moveInternals(node, i, node.getId(), i + 1);
            node.addKey(i, value.getKey());
            writeNode(node);
            getDataStore().write(node.getId(), i, value);
        } else {
            while (i >= 0 && node.getKey(i).compareTo(value.getKey()) > 0) {
                i --;
            }
            i ++;
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
        SearchResult<K> result = find(getRoot(), key);
        if (result == null) {
            return null;
        }
        if (!result.getNode().isLeaf()) {
            result = findLeaf(loadChild(result.getNode(), result.getIndex()));
        }
        return getDataStore().read(result.getNode().getId(), result.getIndex());
    }

    protected SearchResult<K> findLeaf(BTreeNode<K> node) {
        if (!node.isLeaf()) {
            return findLeaf(loadChild(node, node.getKeys().size()));
        } else {
            return new SearchResult<>(node, node.getKeys().size());
        }
    }

    @Override
    public I find(I value) {
        return find(value.getKey());
    }

    protected SearchResult<K> find(BTreeNode<K> root, K key) {
        int i = 0;
        while (i < root.getKeys().size() && root.getKey(i).compareTo(key) < 0) {
            i ++;
        }
        if (i < root.getKeys().size() && root.getKey(i).equals(key)) {
            return new SearchResult<>(root, i);
        } else if (root.isLeaf()) {
            return null;
        } else {
            return find(loadChild(root, i), key);
        }
    }
    
    protected BTreeNode<K> loadChild(BTreeNode<K> parent, int child) {
        final NodeDefinition<K> definition = getNodeStore().read(parent.getId(), child);
        final I value = getDataStore().read(parent.getId(), child);
        final BTreeNode<K> node = new BTreeNode<>(getNodeStore(), child, definition.getId());
        node.setLeaf(definition.isLeaf());
        node.setValue(value);
        node.setParent(parent);
        for (K childKey : definition.getKeys()) {
            node.addKey(childKey);
        }
        return node;
    }

    protected void writeNode(BTreeNode<K> node) {
        final NodeDefinition<K> definition = new NodeDefinition<>(node.isLeaf(), node.getKeys(), node.getId());
        if (!node.isRoot()) {
            getNodeStore().write(node.getParent().getId(), node.getIndex(), definition);
        } else {
            getNodeStore().write(getId(), 0, definition);
        }
    }

    protected void moveInternals(BTreeNode<K> source, int sourceIndex, BTreeNode<K> target, int targetIndex) {
        moveInternals(source, sourceIndex, target.getId(), targetIndex);
    }

    protected void moveInternals(BTreeNode<K> source, int sourceIndex, UUID target, int targetIndex) {
        if (source.isLeaf()) {
            getDataStore().move(source.getId(), sourceIndex, target, targetIndex);
        } else {
            getNodeStore().move(source.getId(), sourceIndex, target, targetIndex);
        }
    }

    protected static class SearchResult<K extends Comparable<K>> {

        private final BTreeNode<K> node;
        private final int index;

        public SearchResult(BTreeNode<K> node, int index) {
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
