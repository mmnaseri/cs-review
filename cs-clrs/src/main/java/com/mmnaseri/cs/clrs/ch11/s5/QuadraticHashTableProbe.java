package com.mmnaseri.cs.clrs.ch11.s5;

import com.mmnaseri.cs.clrs.ch11.HashCalculator;
import com.mmnaseri.cs.clrs.ch11.HashTableProbe;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/14/15, 12:54 AM)
 */
@Quality(Stage.UNTESTED)
public class QuadraticHashTableProbe implements HashTableProbe {

    private final HashCalculator hashCalculator;
    private final int firstCoefficient;
    private final int secondCoefficient;

    public QuadraticHashTableProbe(HashCalculator hashCalculator) {
        this(hashCalculator, 1);
    }

    public QuadraticHashTableProbe(HashCalculator hashCalculator, int firstCoefficient) {
        this(hashCalculator, firstCoefficient, 1);
    }

    public QuadraticHashTableProbe(HashCalculator hashCalculator, int firstCoefficient, int secondCoefficient) {
        this.hashCalculator = hashCalculator;
        this.firstCoefficient = firstCoefficient;
        this.secondCoefficient = secondCoefficient;
    }

    @Override
    public int probe(int index, int capacity, int sequence) {
        final int hash = hashCalculator.calculate(index, capacity);
        long base = hash + firstCoefficient * sequence + secondCoefficient * sequence * sequence;
        return (int) (base % capacity);
    }

}
