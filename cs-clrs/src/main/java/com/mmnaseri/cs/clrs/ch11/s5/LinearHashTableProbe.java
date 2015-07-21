package com.mmnaseri.cs.clrs.ch11.s5;

import com.mmnaseri.cs.clrs.ch11.HashCalculator;
import com.mmnaseri.cs.clrs.ch11.HashTableProbe;
import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/14/15, 12:52 AM)
 */
@Quality(Stage.TESTED)
public class LinearHashTableProbe implements HashTableProbe {

    private final HashCalculator hashCalculator;

    public LinearHashTableProbe(HashCalculator hashCalculator) {
        this.hashCalculator = hashCalculator;
    }

    @Override
    public int probe(int index, int capacity, int sequence) {
        return (hashCalculator.calculate(index, capacity) + sequence) % capacity;
    }

}
