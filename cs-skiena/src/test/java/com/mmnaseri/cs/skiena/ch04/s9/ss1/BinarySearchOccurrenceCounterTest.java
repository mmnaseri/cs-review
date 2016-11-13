package com.mmnaseri.cs.skiena.ch04.s9.ss1;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 1:05 PM)
 */
public class BinarySearchOccurrenceCounterTest extends BaseOccurrenceCounterTest {

    @Override
    protected OccurrenceCounter<Integer> getCounter() {
        return new BinarySearchOccurrenceCounter<>();
    }

}