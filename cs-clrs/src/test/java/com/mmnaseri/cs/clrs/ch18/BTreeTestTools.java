package com.mmnaseri.cs.clrs.ch18;

import com.mmnaseri.cs.clrs.ch18.s1.BTreeNode;
import com.mmnaseri.cs.clrs.ch18.s1.Storage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/31/15)
 */
public abstract class BTreeTestTools {

    public static void checkNodes(BTreeNode node, int degree) {
        if (node.isRoot()) {
            assertThat(node.getKeys().isEmpty(), is(false));
        } else {
            assertThat(node.getKeys().size(), is(greaterThanOrEqualTo(degree - 1)));
            assertThat(node.getKeys().size(), is(lessThan(2 * degree)));
        }
        node = node.getFirstChild();
        while (node != null) {
            checkNodes(node, degree);
            node = node.getNextSibling();
        }
    }

    public static void collectLeaves(Storage<?> dataStore, BTreeNode<?> node, List<String> collection) {
        if (node.isLeaf()) {
            int size = collection.size();
            final List<?> keys = node.getKeys();
            if (dataStore.read(node.getId(), 0) != null) {
                collection.add(String.valueOf(dataStore.read(node.getId(), 0)));
            } else {
                size--;
            }
            for (int i = 0; i < keys.size(); i++) {
                final Object value = dataStore.read(node.getId(), i + 1);
                if (value == null) {
                    size--;
                    continue;
                }
                collection.add(String.valueOf(value));
            }
            assertThat(collection.size() - size, is(keys.size() + 1));
        }
        node = node.getFirstChild();
        while (node != null) {
            collectLeaves(dataStore, node, collection);
            node = node.getNextSibling();
        }
    }

}
