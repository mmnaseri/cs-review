package com.mmnaseri.cs.algorithm.clrs.ch2.sp;

public class BruteForceInversionCounterTest extends BaseInversionCounterTest {

    @Override
    protected InversionCounter<Integer> getCounter() {
        return new BruteForceInversionCounter<>(NATURAL_ORDER);
    }
}