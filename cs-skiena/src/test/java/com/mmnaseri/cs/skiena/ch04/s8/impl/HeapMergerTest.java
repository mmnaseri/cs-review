package com.mmnaseri.cs.skiena.ch04.s8.impl;

import com.mmnaseri.cs.skiena.ch04.s8.Merger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 9:59 AM)
 */
public class HeapMergerTest {

    private Merger<Integer> getMerger() {
        return new HeapMerger<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer first, Integer second) {
                return Integer.compare(first, second);
            }
        });
    }

    @DataProvider
    private Object[][] listProvider() {
        final List<List<List<Integer>>> seed = new ArrayList<>();
        final Random random = new Random();
        for (int i = 0; i < 100; i++) {
            final List<List<Integer>> lists = new ArrayList<>();
            for (int j = 0; j < 2 + random.nextInt(48); j++) {
                final List<Integer> items = new ArrayList<>();
                for (int k = 0; k < 200 + random.nextInt(1800); k++) {
                    items.add(random.nextInt());
                }
                Collections.sort(items);
                lists.add(items);
            }
            seed.add(lists);
        }
        final List<Object[]> result = new ArrayList<>();
        for (List<List<Integer>> lists : seed) {
            result.add(new Object[]{lists});
        }
        return result.toArray(new Object[result.size()][]);
    }

    @Test(dataProvider = "listProvider")
    public final void testMerging(List<List<Integer>> lists) throws Exception {
        final List<Integer> expected = new ArrayList<>();
        for (List<Integer> list : lists) {
            expected.addAll(list);
        }
        Collections.sort(expected);
        final Merger<Integer> merger = getMerger();
        final List<Integer> merged = merger.merge(lists.toArray(new List[lists.size()]));
        assertThat(merged, is(notNullValue()));
        assertThat(merged, hasSize(expected.size()));
        for (int i = 0; i < merged.size(); i++) {
            final Integer item = merged.get(i);
            assertThat(item, is(expected.get(i)));
        }
    }

}