package com.mmnaseri.cs.skiena.ch04.s9;

import org.testng.annotations.Test;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 10:56 AM)
 */
public class NaiveOccurrenceCounterTest extends BaseOccurrenceCounterTest {

    @Override
    protected OccurrenceCounter<Integer> getCounter() {
        return new NaiveOccurrenceCounter<>();
    }

    @Test(dataProvider = "arrayProvider")
    public void testCountingUnsortedArrays(Integer[] array) throws Exception {
        testCounting(array);
    }

}