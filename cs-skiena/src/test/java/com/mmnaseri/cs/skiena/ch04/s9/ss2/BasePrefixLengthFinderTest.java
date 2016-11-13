package com.mmnaseri.cs.skiena.ch04.s9.ss2;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 2:27 PM)
 */
public abstract class BasePrefixLengthFinderTest {

    protected abstract PrefixLengthFinder<Integer> getLengthFinder();

    @DataProvider
    public Object[][] arrayProvider() {
        final List<Object[]> result = new ArrayList<>();
        final Random random = new Random();
        for (int i = 0; i < 100; i++) {
            final Integer[] array = new Integer[10 + random.nextInt(1990)];
            final int count = 1 + random.nextInt(array.length - 1);
            for (int j = 0; j < array.length; j++) {
                if (j < count) {
                    array[j] = 0;
                } else {
                    array[j] = 1;
                }
            }
            result.add(new Object[]{array, count});
        }
        return result.toArray(new Object[result.size()][]);
    }

    @Test(dataProvider = "arrayProvider")
    public void testCounting(Integer[] array, int count) throws Exception {
        final PrefixLengthFinder<Integer> finder = getLengthFinder();
        final int length = finder.findPrefixLength(array);
        assertThat(length, is(count));
    }

}
