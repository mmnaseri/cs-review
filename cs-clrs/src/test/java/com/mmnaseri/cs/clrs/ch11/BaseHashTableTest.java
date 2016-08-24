package com.mmnaseri.cs.clrs.ch11;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/13/15)
 */
public abstract class BaseHashTableTest {

    protected abstract HashTable<Integer>[] getHashTables();

    @Test
    public void testInitialState() throws Exception {
        final HashTable<Integer>[] hashTables = getHashTables();
        for (HashTable<Integer> hashTable : hashTables) {
            assertThat(hashTable.getSize(), is(0));
            assertThat(hashTable.isEmpty(), is(true));
        }
    }

    protected Map<String, TestCase> getTestCases() {
        final HashMap<String, TestCase> map = new HashMap<>();
        map.put("smallKeys,smallValues", new TestCase(new Integer[]{6, 5, 8, 9, 2, 7, 1}, new Integer[]{8, 5, 7, 3, 2, 1, 3}, new Integer[]{1, 2, 3, 4, 5, 6, 7}));
        map.put("smallKeys,largeValues", new TestCase(new Integer[]{7, 3, 2, 9, 0}, new Integer[]{45346231, 19283765, 938475268, 3456789, 1982376718}, new Integer[]{1, 2, 3, 4, 5}));
        map.put("smallKeys,duplicateValues", new TestCase(new Integer[]{7, 4, 8, 3, 2}, new Integer[]{1, 1, 1, 2, 2}, new Integer[]{1, 2, 3, 4, 5}));
        map.put("smallKeys,negativeValues", new TestCase(new Integer[]{6, 5, 3, 7}, new Integer[]{-5, -1, -8, -1}, new Integer[]{1, 2, 3, 4}));
        map.put("smallKeys,zeroValue", new TestCase(new Integer[]{1, 8, 4, 7}, new Integer[]{0, 2, 0, 3}, new Integer[]{1, 2, 3, 4}));
        map.put("smallKeys,nullValue", new TestCase(new Integer[]{1, 2, 3, 4}, new Integer[]{0, null, 1, null}, new Integer[]{1, 2, 3, 4}));
        map.put("smallKeys,infinityValue", new TestCase(new Integer[]{1, 6, 4, 7}, new Integer[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 1, 2}, new Integer[]{1, 2, 3, 4}));
        map.put("smallKeys.duplicates,smallValues", new TestCase(new Integer[]{1, 1, 1, 2}, new Integer[]{8, 7, 6, 5}, new Integer[]{1, 1, 1, 2}));
        map.put("smallKeys.duplicates,largeValues", new TestCase(new Integer[]{6, 5, 6, 5}, new Integer[]{545678123, 912873, 912109, 7537}, new Integer[]{1, 2, 2, 2}));
        return map;
    }

    @DataProvider
    public final Object[][] hashTableDataProvider() {
        final Map<String, TestCase> testCases = getTestCases();
        final List<Object[]> feed = new ArrayList<>();
        for (Map.Entry<String, TestCase> entry : testCases.entrySet()) {
            final String name = entry.getKey(); //name is added for test case visibility in case of failure
            final TestCase testCase = entry.getValue();
            final HashTable<Integer>[] hashTables = getHashTables();
            for (HashTable<Integer> hashTable : hashTables) {
                feed.add(new Object[]{hashTable, name, testCase.getIndices(), testCase.getValues(), testCase.getSizes()});
            }
        }
        return feed.toArray(new Object[feed.size()][]);
    }

    @SuppressWarnings("UnusedParameters")
    @Test(dataProvider = "hashTableDataProvider")
    public void testHashTableGrowth(HashTable<Integer> hashTable, String name, Integer[] indices, Integer[] items, Integer[] sizes) throws Exception {
        for (int i = 0; i < indices.length; i++) {
            final Integer index = indices[i];
            final Integer item = items[i];
            final Integer size = sizes[i];
            hashTable.put(index, item);
            assertThat(hashTable.getSize(), is(size));
            assertThat(hashTable.isEmpty(), is(false));
        }
    }

    @SuppressWarnings("UnusedParameters")
    @Test(dataProvider = "hashTableDataProvider")
    public void testHashTableShrinking(HashTable<Integer> hashTable, String name, Integer[] indices, Integer[] items, Integer[] sizes) throws Exception {
        final Set<Integer> keys = new HashSet<>();
        for (int i = 0; i < indices.length; i++) {
            final Integer index = indices[i];
            final Integer item = items[i];
            keys.add(index);
            hashTable.put(index, item);
        }
        for (Integer index : indices) {
            assertThat(hashTable.getSize(), is(keys.size()));
            hashTable.delete(index);
            keys.remove(index);
            assertThat(hashTable.getSize(), is(keys.size()));
        }
        assertThat(hashTable.isEmpty(), is(true));
    }

    @SuppressWarnings("UnusedParameters")
    @Test(dataProvider = "hashTableDataProvider")
    public void testHashTableValues(HashTable<Integer> hashTable, String name, Integer[] indices, Integer[] items, Integer[] sizes) throws Exception {
        final Map<Integer, Integer> values = new HashMap<>();
        for (int i = 0; i < indices.length; i++) {
            final Integer index = indices[i];
            final Integer item = items[i];
            values.put(index, item);
            hashTable.put(index, item);
        }
        assertThat(hashTable.getSize(), is(values.size()));
        for (Integer index : indices) {
            assertThat(hashTable.get(index), is(values.get(index)));
        }
    }

    protected static class TestCase {

        private Integer[] indices;
        private Integer[] values;
        private Integer[] sizes;

        public TestCase(Integer[] indices, Integer[] values, Integer[] sizes) {
            this.indices = indices;
            this.values = values;
            this.sizes = sizes;
        }

        public Integer[] getIndices() {
            return indices;
        }

        public Integer[] getValues() {
            return values;
        }

        public Integer[] getSizes() {
            return sizes;
        }
    }

}
