package com.mmnaseri.cs.skiena.ch03.s3;

import com.mmnaseri.cs.clrs.common.Sorter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 10:28 AM)
 */
public abstract class BaseSortTest {

    protected static final Comparator<Integer> NATURAL_ORDER = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1, o2);
        }
    };

    protected abstract Sorter<Integer> getSorter(Comparator<Integer> comparator);

    @DataProvider
    public Object[][] arrayProvider() {
        final List<Integer[]> arrays = new ArrayList<>();
        final Random random = new Random();
        for (int i = 0; i < 100; i++) {
            final Integer[] integers = new Integer[random.nextInt(100)];
            for (int j = 0; j < integers.length; j++) {
                integers[j] = random.nextInt();
            }
            arrays.add(integers);
        }
        final List<Object[]> result = new ArrayList<>();
        for (Integer[] array : arrays) {
            result.add(new Object[]{array});
        }
        return result.toArray(new Object[result.size()][]);
    }

    @Test(dataProvider = "arrayProvider")
    public void testSorting(Integer[] array) throws Exception {
        final Integer[] copy = Arrays.asList(array).toArray(new Integer[array.length]);
        Arrays.sort(copy);
        final Sorter<Integer> sorter = getSorter(NATURAL_ORDER);
        sorter.sort(array);
        assertThat(array.length, is(copy.length));
        for (int i = 0; i < copy.length; i++) {
            Integer item = copy[i];
            assertThat(array[i], is(item));
        }
    }
    
}
