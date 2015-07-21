package com.mmnaseri.cs.clrs.ch02.sp;

public class MergeInversionCounterTest extends BaseInversionCounterTest {

    @Override
    protected InversionCounter<Integer> getCounter() {
        return new MergeInversionCounter<>(NATURAL_ORDER);
    }

}