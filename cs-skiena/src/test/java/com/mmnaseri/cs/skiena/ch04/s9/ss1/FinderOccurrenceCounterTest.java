package com.mmnaseri.cs.skiena.ch04.s9.ss1;

import com.mmnaseri.cs.clrs.ch02.s3.BinarySearchFinder;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 11:24 AM)
 */
public class FinderOccurrenceCounterTest extends BaseOccurrenceCounterTest {

    @Override
    protected OccurrenceCounter<Integer> getCounter() {
        return new FinderOccurrenceCounter<>(new BinarySearchFinder<Integer>());
    }

}