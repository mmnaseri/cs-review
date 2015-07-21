package com.mmnaseri.cs.clrs.ch02.sp;

public class BruteForceInversionCounterTest extends BaseInversionCounterTest {

    @Override
    protected InversionCounter<Integer> getCounter() {
        return new BruteForceInversionCounter<>(NATURAL_ORDER);
    }
}