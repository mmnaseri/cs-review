package com.mmnaseri.cs.algorithm.clrs.ch2.sp;

import org.testng.annotations.Test;

import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/11/15, 1:00 PM)
 */
public abstract class BaseInversionCounterTest {

    protected static final Comparator<Integer> NATURAL_ORDER = new Comparator<Integer>() {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }

    };

    protected abstract InversionCounter<Integer> getCounter();

    @Test
    public void testCountingInversionsInEmptyArray() throws Exception {
        assertThat(getCounter().count(), is(0));
    }

    @Test
    public void testCountingInversionsWhenThereAreNone() throws Exception {
        assertThat(getCounter().count(1, 2, 3, 4, 5, 6), is(0));
    }

    @Test
    public void testCountingInversionsInReverseArray() throws Exception {
        final Integer[] numbers = {6, 5, 4, 3, 2, 1};
        assertThat(getCounter().count(numbers), is(numbers.length * (numbers.length - 1) / 2)); //
    }

    @Test
    public void testCountingInversionsWhenThereIsOnlyOneElementInTheWrongPlace() throws Exception {
        assertThat(getCounter().count(4, 1, 2, 3), is(3));
    }

    @Test
    public void testCountingInversionsWithDuplicates() throws Exception {
        assertThat(getCounter().count(0, 1, 1, 1, 1, 2), is(0));
    }
}
