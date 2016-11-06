package com.mmnaseri.cs.clrs.ch32.s4.impl;

import com.mmnaseri.cs.clrs.ch32.s4.PrefixFunction;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/6/16, 11:17 AM)
 */
@Quality(Stage.TESTED)
public class ArrayPrefixFunction implements PrefixFunction {

    private final int[] lengths;

    public ArrayPrefixFunction(int[] lengths) {
        this.lengths = lengths;
    }

    @Override
    public int prefix(int index) {
        return lengths[index];
    }

}
