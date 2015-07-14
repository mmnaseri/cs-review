package com.mmnaseri.cs.ds.clrs.ch11.s5;

import com.mmnaseri.cs.ds.clrs.ch11.HashTable;
import com.mmnaseri.cs.ds.clrs.ch11.s2.BaseLargeKeysHashTableTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.fail;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/14/15, 1:08 AM)
 */
public class OpenAddressedHashTableTest extends BaseLargeKeysHashTableTest {

    @Override
    protected HashTable<Integer>[] getHashTables() {
        //noinspection unchecked
        return new HashTable[]{new OpenAddressedHashTable(8)};
    }

    protected Integer[][] getHashTableData() {
        return new Integer[][]{
            new Integer[]{12537, 987123, 123, 5645763, 123, 897, 0, 123},
            new Integer[]{-12537, -987123, -123, -5645763, -123, -897, 0, -123},
            new Integer[]{12537, 987123, 123, 5645763, 123, 897, Integer.MIN_VALUE, Integer.MAX_VALUE},
            new Integer[]{null, 12313, 129387123, 12365, 49287, 123, 456, 42},
        };
    }
    
    @DataProvider
    public Object[][] hashTableOverflowDataProvider() {
        final List<Object[]> feed = new ArrayList<>();
        final Integer[][] data = getHashTableData();
        for (Integer[] items : data) {
            final HashTable<Integer>[] hashTables = getHashTables();
            for (HashTable<Integer> hashTable : hashTables) {
                final List<Integer> indices = new ArrayList<>();
                for (Integer ignored : items) {
                    int index;
                    do {
                        index = Math.abs(new Random().nextInt());
                    } while (indices.contains(index));
                    indices.add(index);
                }
                feed.add(new Object[]{hashTable, indices.toArray(new Integer[indices.size()]), items});
            }
        }
        return feed.toArray(new Object[feed.size()][]);
    }

    @Test(dataProvider = "hashTableOverflowDataProvider", expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = "Hash table is full")
    public void testOverflow(HashTable<Integer> hashTable, Integer[] indices, Integer[] items) throws Exception {
        final Set<Integer> keys = new HashSet<>();
        for (int i = 0; i < indices.length; i++) {
            final Integer index = indices[i];
            final Integer item = items[i];
            keys.add(index);
            try {
                hashTable.put(index, item);
            } catch (Exception e) {
                fail("Unexpected exception occurred: " + e);
            }
        }
        int extra;
        do {
            extra = Math.abs(new Random().nextInt());
        } while (keys.contains(extra));
        hashTable.put(extra, extra);
    }

}