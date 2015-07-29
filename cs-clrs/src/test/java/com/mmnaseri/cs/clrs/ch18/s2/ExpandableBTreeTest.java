package com.mmnaseri.cs.clrs.ch18.s2;

import com.mmnaseri.cs.clrs.ch18.s1.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/28/15)
 */
public class ExpandableBTreeTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreatingSmallDegree() throws Exception {
        new NonDeletingBTree(null, null, 1);
    }

    @DataProvider
    public Object[][] stressDataProvider() {
        final List<String> input = new ArrayList<>();
        final List<String> seed = Arrays.asList("1", "3", "2");
        for (String prefix : seed) {
            input.add(prefix);
        }
        for (int i = 0; i < 5; i ++) {
            final List<List<String>> addendum = new ArrayList<>();
            for (String word : input) {
                final List<String> variations = new ArrayList<>();
                for (String suffix : seed) {
                    variations.add(word + suffix);
                }
                addendum.add(variations);
            }
            for (List<String> derivatives : addendum) {
                for (String derivative : derivatives) {
                    if (!input.contains(derivative)) {
                        input.add(derivative);
                    }
                }
            }
        }
        int fromDegree = 2;
        int toDegree = 250;
        int benchmark = 250;
        final List<Object[]> cases = new ArrayList<>();
        for (int degree = fromDegree; degree < toDegree + 1; degree++) {
            cases.add(new Object[]{input, degree, benchmark});
        }
        return cases.toArray(new Object[cases.size()][]);
    }

    @Test(dataProvider = "stressDataProvider")
    public void stressTest(List<String> input, int degree, int benchmark) throws Exception {
        for (int size = degree; size < benchmark; size ++) {
            final MapStorage<ReflectiveIndexed<String>> dataStore = new MapStorage<>();
            final MapStorage<NodeDefinition<String>> nodeStore = new MapStorage<>();
            final NonDeletingBTree tree = new NonDeletingBTree(dataStore, nodeStore, degree);
            for (int insertion = 0; insertion < size; insertion ++) {
                tree.insert(new ReflectiveIndexed<>(input.get(insertion)));
            }
            final Set<String> trial = new HashSet<>();
            collectLeaves(dataStore, tree.getRoot(), trial);
            assertThat(trial.size(), is(size));
            assertThat(new HashSet<>(input.subList(0, size)), is(trial));
            for (int index = 0; index < size; index ++) {
                final ReflectiveIndexed<String> found = tree.find(new ReflectiveIndexed<>(input.get(index)));
                assertThat(found, is(notNullValue()));
                assertThat(found.getKey(), is(notNullValue()));
                assertThat(found.getKey(), is(input.get(index)));
            }
            assertThat(tree.find(input.get(size)), is(nullValue()));
        }
    }

    private static void collectLeaves(Storage<?> dataStore, BTreeNode<?, ?> node, Set<String> input) {
        if (node.isLeaf()) {
            int size = input.size();
            final List<?> keys = node.getKeys();
            if (dataStore.read(node.getId(), 0) != null) {
                input.add(String.valueOf(dataStore.read(node.getId(), 0)));
            } else {
                size --;
            }
            for (int i = 0; i < keys.size(); i++) {
                final Object value = dataStore.read(node.getId(), i + 1);
                if (value == null) {
                    size --;
                    continue;
                }
                input.add(String.valueOf(value));
            }
            assertThat(input.size() - size, is(keys.size() + 1));
        }
        node = node.getFirstChild();
        while (node != null) {
            collectLeaves(dataStore, node, input);
            node = node.getNextSibling();
        }
    }


    private static class NonDeletingBTree extends ExpandableBTree<ReflectiveIndexed<String>, String> {

        public NonDeletingBTree(Storage<ReflectiveIndexed<String>> dataStore, Storage<NodeDefinition<String>> nodeStore, int degree) {
            super(dataStore, nodeStore, degree);
        }

        @Override
        public void delete(ReflectiveIndexed<String> value) {
            throw new UnsupportedOperationException();
        }

    }

}