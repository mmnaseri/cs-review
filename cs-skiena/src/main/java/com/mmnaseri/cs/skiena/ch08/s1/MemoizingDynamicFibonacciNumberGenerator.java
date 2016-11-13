package com.mmnaseri.cs.skiena.ch08.s1;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/13/16, 8:42 AM)
 */
@Quality(Stage.UNTESTED)
public class MemoizingDynamicFibonacciNumberGenerator implements FibonacciNumberGenerator {

    private final List<BigInteger> cache;

    public MemoizingDynamicFibonacciNumberGenerator() {
        cache = new ArrayList<>();
        cache.add(BigInteger.ONE);
        cache.add(BigInteger.ONE);
    }

    @Override
    public BigInteger generate(int index) {
        while (cache.size() <= index) {
            cache.add(null);
        }
        if (cache.get(index) == null) {
            cache.set(index, generate(index - 1).add(generate(index - 2)));
        }
        return cache.get(index);
    }
}
