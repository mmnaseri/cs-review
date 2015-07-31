package com.mmnaseri.cs.clrs.ch18.s3;

import com.mmnaseri.cs.clrs.ch18.s1.BTreeNode;
import com.mmnaseri.cs.clrs.ch18.s1.Indexed;
import com.mmnaseri.cs.clrs.ch18.s1.NodeDefinition;
import com.mmnaseri.cs.clrs.ch18.s1.Storage;
import com.mmnaseri.cs.clrs.ch18.s2.ExpandableBTree;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/29/15)
 */
@Quality(value = Stage.TESTED, explanation = "Stress tested with 100% code coverage")
public class BTree<I extends Indexed<K>, K extends Comparable<K>> extends ExpandableBTree<I, K> {

    public BTree(Storage<I> dataStore, Storage<NodeDefinition<K>> nodeStore, int degree) {
        super(dataStore, nodeStore, degree);
    }

    @Override
    public void delete(I value) {
        delete(value.getKey());
    }

    protected void delete(K key) {
        delete(getRoot(), key);
    }

    private void delete(BTreeNode<K> node, K key) {
        int index = 0;
        while (index < node.getKeys().size() && node.getKey(index).compareTo(key) < 0) {
            index ++;
        }
        BTreeNode<K> child;
        if (index < node.getKeys().size() && node.getKey(index).equals(key)) {
            if (node.isLeaf()) {
                //shift any satellite data to the right of the key by one
                //so that it covers this node's data
                for (int i = index + 1; i <= node.getKeys().size(); i ++) {
                    getDataStore().move(node.getId(), i, node.getId(), i - 1);
                }
                //remove the key
                node.removeKey(index);
                //update node definition
                writeNode(node);
            } else {
                final BTreeNode<K> left = assemble(node, index);
                final BTreeNode<K> predecessor = findLeaf(left).getNode();
                if (left.getKeys().size() > getDegree() - 1) {
                    //find the key we want to replace this node with
                    final K target = predecessor.getKey(predecessor.getKeys().size() - 1);
                    //move that data to replace the current keys data
                    getDataStore().move(predecessor.getId(), predecessor.getKeys().size() - 1, predecessor.getId(), predecessor.getKeys().size());
                    //now that the data is tucked away safely, we can delete the replacement key recursively
                    delete(left, target);
                    //now that the key is deleted from the target, we can replace this key with the target
                    node.removeKey(index);
                    node.addKey(index, target);
                    writeNode(node);
                    return;
                }
                final BTreeNode<K> right = assemble(node, index + 1);
                if (right.getKeys().size() > getDegree() - 1) {
                    BTreeNode<K> successor = right;
                    while (!successor.isLeaf()) {
                        successor = assemble(successor, 0);
                    }
                    //find the key we want to replace this node with
                    final K target = successor.getKey(0);
                    //find where the data for that key is hosted
                    //move that data to replace the current keys data
                    getDataStore().move(successor.getId(), 0, predecessor.getId(), predecessor.getKeys().size());
                    //now that the data is tucked away safely, we can delete the replacement key recursively
                    delete(right, target);
                    //now that the key is deleted from the target, we can replace this key with the target
                    node.removeKey(index);
                    node.addKey(index, target);
                    writeNode(node);
                    return;
                }
                child = merge(node, index);
                delete(child, key);
                fixRoot(node, child);
            }
        } else if (!node.isLeaf()) {
            //the key is not in this node and this is not a leaf, so the key might still be in the subtrees
            child = assemble(node, index);
            if (child.getKeys().size() < getDegree()) {
                if (index > 0) {
                    //try to see if child's previous sibling can be of any help
                    final BTreeNode<K> left = assemble(node, index - 1);
                    if (left.getKeys().size() >= getDegree()) {
                        //we need to shift everything under child one to the right
                        for (int i = child.getKeys().size(); i >= 0; i--) {
                            getNodeStore().move(child.getId(), i, child.getId(), i + 1);
                            getDataStore().move(child.getId(), i, child.getId(), i + 1);
                        }
                        //we need to move current key's data to the child's left hand side
                        child.addKey(0, node.getKey(index - 1));
                        getNodeStore().move(left.getId(), left.getKeys().size(), child.getId(), 0);
                        getDataStore().move(left.getId(), left.getKeys().size(), child.getId(), 0);
                        final K substitute = left.getKeys().get(left.getKeys().size() - 1);
                        node.removeKey(index - 1);
                        node.addKey(index - 1, substitute);
                        left.removeKey(left.getKeys().size() - 1);
                        writeNode(left);
                        writeNode(child);
                        writeNode(node);
                        delete(child, key);
                        return;
                    }
                }
                if (index < node.getKeys().size()) {
                    final BTreeNode<K> right = assemble(node, index + 1);
                    if (right.getKeys().size() >= getDegree()) {
                        getDataStore().move(right.getId(), 0, child.getId(), child.getKeys().size() + 1);
                        getNodeStore().move(right.getId(), 0, child.getId(), child.getKeys().size() + 1);
                        for (int i = 1; i <= right.getKeys().size(); i ++) {
                            getNodeStore().move(right.getId(), i, right.getId(), i - 1);
                            getDataStore().move(right.getId(), i, right.getId(), i - 1);
                        }
                        child.addKey(node.getKey(index));
                        node.removeKey(index);
                        node.addKey(index, right.getKey(0));
                        right.removeKey(0);
                        writeNode(right);
                        writeNode(child);
                        writeNode(node);
                        delete(child, key);
                        return;
                    }
                }
                if (index < node.getKeys().size()) {
                    child = merge(node, index);
                } else {
                    child = merge(node, index - 1);
                }
            }
            delete(child, key);
            fixRoot(node, child);
        }
    }

    private void fixRoot(BTreeNode<K> node, BTreeNode<K> child) {
        if (node.isRoot() && node.getKeys().isEmpty()) {
            //the root node was emptied after the merge, which means we have to replaced it
            child.setParent(null);
            setRoot(child);
            getNodeStore().delete(getId(), 0);
            writeNode(child);
        }
    }

    private BTreeNode<K> merge(BTreeNode<K> node, int index) {
        final BTreeNode<K> left = assemble(node, index);
        final BTreeNode<K> right = assemble(node, index + 1);
        final K middle = node.getKey(index);
        for (int i = index + 1; i <= node.getKeys().size(); i ++) {
            getDataStore().move(node.getId(), i, node.getId(), i - 1);
            getNodeStore().move(node.getId(), i, node.getId(), i - 1);
        }
        node.removeKey(index);
        writeNode(node);
        left.addKey(left.getKeys().size(), middle);
        for (int i = 0; i <= right.getKeys().size(); i ++) {
            getDataStore().move(right.getId(), i, left.getId(), left.getKeys().size() + i);
            getNodeStore().move(right.getId(), i, left.getId(), left.getKeys().size() + i);
        }
        for (int i = 0; i < right.getKeys().size(); i++) {
            left.addKey(right.getKey(i));
        }
        writeNode(left);
        for (int i = 0; i < right.getKeys().size() + 1; i++) {
            getDataStore().delete(right.getId(), i);
            getNodeStore().delete(right.getId(), i);
        }
        left.setParent(node);
        return left;
    }

}
