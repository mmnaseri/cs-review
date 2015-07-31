package com.mmnaseri.cs.clrs.ch18.s3;

import com.mmnaseri.cs.clrs.ch18.BTreeTestTools;
import com.mmnaseri.cs.clrs.ch18.s1.MapStorage;
import com.mmnaseri.cs.clrs.ch18.s1.NodeDefinition;
import com.mmnaseri.cs.clrs.ch18.s1.ReflectiveIndexed;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/30/15)
 */
public class BTreeTest {

    private List<String> sampleProducer() {
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
        return input;
    }

    @DataProvider
    public Object[][] stressDataProvider() {
        final List<String> input = sampleProducer();
        int fromDegree = 2;
        int toDegree = 250;
        int benchmark = 250;
        final List<Object[]> cases = new ArrayList<>();
        for (int degree = fromDegree; degree < toDegree + 1; degree++) {
            cases.add(new Object[]{degree, benchmark, input});
        }
        return cases.toArray(new Object[cases.size()][]);
    }

    @DataProvider
    public Object[][] failingTest() {
        return new Object[][]{
            new Object[]{3, 32, sampleProducer()}
        };
    }

    @Test(dataProvider = "stressDataProvider")
    public void stressTest(int degree, int benchmark, List<String> input) throws Exception {
        for (int size = degree; size < benchmark; size++) {
            //we first prepare the tree to have the given degree and have the first `size` items of the input sequence inserted in it
            final MapStorage<ReflectiveIndexed<String>> dataStore = new MapStorage<>();
            final MapStorage<NodeDefinition<String>> nodeStore = new MapStorage<>();
            final BTree<ReflectiveIndexed<String>, String> tree = new BTree<>(dataStore, nodeStore, degree);
            for (int insertion = 0; insertion < size; insertion ++) {
                tree.insert(new ReflectiveIndexed<>(input.get(insertion)));
            }
            //at this point, the tree must hold up to node checking scrutiny
            BTreeTestTools.checkNodes(tree.getRoot(), degree);
            final List<String> inserted = new ArrayList<>(input).subList(0, size);
            Collections.sort(inserted);
            final List<String> initialState = new ArrayList<>();
            BTreeTestTools.collectLeaves(dataStore, tree.getRoot(), initialState);
            assertThat(initialState, is(inserted));
            //we now delete the items in the order they were inserted
            for (int deletion = 0; deletion < size; deletion++) {
                final String item = input.get(deletion);
                tree.delete(new ReflectiveIndexed<>(item));
                inserted.remove(item);
                //while the tree is not empty, each node must abide by the property that it has
                //between t-1 and 2t-1 keys except the root which must be non-empty
                if (!inserted.isEmpty()) {
                    BTreeTestTools.checkNodes(tree.getRoot(), degree);
                }
                //the item we just deleted should not be found on the tree
                assertThat(tree.find(item), is(nullValue()));
                //every other item we have not deleted should be found somewhere on the tree
                for (String remaining : inserted) {
                    final ReflectiveIndexed<String> found = tree.find(remaining);
                    assertThat(found, is(notNullValue()));
                    assertThat(found.getKey(), is(notNullValue()));
                    assertThat(found.getKey(), is(remaining));
                }
                //further more, the tree's leaves must still present the whole data set in sorted order
                final List<String> leaves = new ArrayList<>();
                BTreeTestTools.collectLeaves(dataStore, tree.getRoot(), leaves);
                assertThat(leaves, is(inserted));
            }
        }
    }

}