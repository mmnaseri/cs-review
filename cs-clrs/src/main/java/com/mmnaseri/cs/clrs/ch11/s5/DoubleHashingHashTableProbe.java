package com.mmnaseri.cs.clrs.ch11.s5;

import com.mmnaseri.cs.clrs.ch11.HashCalculator;
import com.mmnaseri.cs.clrs.ch11.HashTableProbe;
import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/14/15, 12:57 AM)
 */
@Quality(Stage.TESTED)
public class DoubleHashingHashTableProbe implements HashTableProbe {

    private final HashCalculator firstHashCalculator;
    private final HashCalculator secondHashCalculator;

    public DoubleHashingHashTableProbe(HashCalculator firstHashCalculator, HashCalculator secondHashCalculator) {
        this.firstHashCalculator = firstHashCalculator;
        this.secondHashCalculator = secondHashCalculator;
    }

    @Override
    public int probe(int index, int capacity, int sequence) {
        long base = firstHashCalculator.calculate(index, capacity);
        base += sequence * secondHashCalculator.calculate(index + 1, capacity);
        return (int) (base % capacity);
    }

}
