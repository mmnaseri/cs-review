package com.mmnaseri.cs.clrs.ch11.s5;

import com.mmnaseri.cs.clrs.ch11.HashTableProbe;
import com.mmnaseri.cs.clrs.ch11.s3.DivisionHashCalculator;
import com.mmnaseri.cs.clrs.ch11.s3.MultiplicationHashCalculator;
import com.mmnaseri.cs.clrs.ch11.s4.LinearHashCalculator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/14/15, 1:32 AM)
 */
public class LinearHashTableProbeTest extends BaseHashTableProbeTest {

    @Override
    protected HashTableProbe[] getProbes() {
        return new HashTableProbe[]{
                new LinearHashTableProbe(new DivisionHashCalculator()),
                new LinearHashTableProbe(new MultiplicationHashCalculator(Math.E)),
                new LinearHashTableProbe(new LinearHashCalculator(13, 7, 3))
        };
    }

}