package com.mmnaseri.cs.clrs.ch11.s4;

import com.mmnaseri.cs.clrs.ch11.HashCalculator;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/13/15)
 */
@Quality(Stage.TESTED)
public class LinearHashCalculator implements HashCalculator {

    private final int prime;
    private final int coefficient;
    private final int offset;

    public LinearHashCalculator(int prime, int coefficient, int offset) {
        this.prime = prime;
        this.coefficient = coefficient;
        this.offset = offset;
    }

    @Override
    public int calculate(int index, int capacity) {
        //this is to protect ourselves against integer value overflows
        long base = (long) coefficient * index + offset;
        base = base % prime;
        return (int) (base % capacity);
    }

}
