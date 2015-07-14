package com.mmnaseri.cs.ds.clrs.ch11.s5;

import com.mmnaseri.cs.ds.clrs.ch11.HashTableProbe;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.hamcrest.number.OrderingComparison.lessThan;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/14/15, 1:39 AM)
 */
public abstract class BaseHashTableProbeTest {
    protected abstract HashTableProbe[] getProbes();

    public Object[][] getData() {
        return new Object[][]{
                new Object[]{54, 3},
                new Object[]{54, 10},
                new Object[]{54, 60},
                new Object[]{0, 20},
                new Object[]{0, 100},
                new Object[]{0, 1000},
                new Object[]{128712837, 10},
                new Object[]{128712837, 20},
                new Object[]{128712837, 30},
                new Object[]{128712837, 2},
                new Object[]{128712837, 3},
                new Object[]{128712837, 4},
        };
    }

    @DataProvider
    public Object[][] probeIntegrityDataProvider() {
        final List<Object[]> feed = new ArrayList<>();
        for (Object[] items : getData()) {
            for (HashTableProbe probe : getProbes()) {
                feed.add(new Object[]{probe, items[0], items[1]});
            }
        }
        return feed.toArray(new Object[feed.size()][]);
    }

    @Test(dataProvider = "probeIntegrityDataProvider")
    public void testProbeIntegrity(HashTableProbe probe, int index, int capacity) throws Exception {
        final Set<Integer> hashes = new HashSet<>();
        for (int i = 0; i < capacity; i ++) {
            final int hash = probe.probe(index, capacity, i);
            assertThat(hash, greaterThanOrEqualTo(0));
            assertThat(hash, lessThan(capacity));
            hashes.add(hash);
        }
        assertThat(hashes, hasSize(capacity));
    }
}
