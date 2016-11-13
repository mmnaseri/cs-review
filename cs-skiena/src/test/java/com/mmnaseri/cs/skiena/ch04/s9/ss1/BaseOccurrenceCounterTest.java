package com.mmnaseri.cs.skiena.ch04.s9.ss1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 11:03 AM)
 */
public abstract class BaseOccurrenceCounterTest {

    protected abstract OccurrenceCounter<Integer> getCounter();

    void testCounting(Integer[] array) throws Exception {
        final Set<Integer> set = new HashSet<>(Arrays.asList(array));
        final OccurrenceCounter<Integer> counter = getCounter();
        for (Integer item : set) {
            int count = 0;
            for (Integer integer : array) {
                if (integer.equals(item)) {
                    count ++;
                }
            }
            final int found = counter.count(array, item);
            assertThat(found, is(count));
        }
        final Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int other;
            do {
                other = random.nextInt();
            } while (set.contains(other));
            assertThat(counter.count(array, other), is(0));
        }
    }

    @Test(dataProvider = "arrayProvider")
    public void testCountingSortedData(Integer[] array) throws Exception {
        Arrays.sort(array);
        testCounting(array);
    }

    @DataProvider
    public Object[][] arrayProvider() {
        final List<Integer[]> arrays = new ArrayList<>();
        final Random random = new Random();
        for (int i = 0; i < 100; i++) {
            final Integer[] array = new Integer[random.nextInt(99) + 1];
            for (int j = 0; j < array.length; j++) {
                array[j] = random.nextInt();
            }
            arrays.add(array);
        }
        final List<Object[]> result = new ArrayList<>();
        for (Integer[] array : arrays) {
            result.add(new Object[]{array});
        }
        return result.toArray(new Object[result.size()][]);
    }

}
