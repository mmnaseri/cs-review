package com.mmnaseri.cs.clrs.ch11.s3;

import com.mmnaseri.cs.clrs.ch11.HashCalculator;
import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/13/15)
 */
@Quality(Stage.TESTED)
public class MultiplicationHashCalculator implements HashCalculator {

    private final double factor;

    public MultiplicationHashCalculator(double factor) {
        this.factor = factor;
    }

    @Override
    public int calculate(int index, int capacity) {
        final double multiplied = index * factor;
        final double fraction = multiplied - Math.floor(multiplied);
        return (int) Math.floor(fraction * capacity);
    }

}
