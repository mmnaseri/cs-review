package com.mmnaseri.cs.skiena.ch08.s1;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/13/16, 8:36 AM)
 */
@Quality(Stage.UNTESTED)
public class OnDemandDynamicFibonacciNumberGenerator implements FibonacciNumberGenerator {

    @Override
    public BigInteger generate(int index) {
        return generate(new ArrayList<BigInteger>(), index);
    }

    private BigInteger generate(List<BigInteger> cache, int index) {
        while (index >= cache.size()) {
            cache.add(null);
        }
        if (cache.get(index) != null) {
            return cache.get(index);
        }
        final BigInteger result;
        if (index <= 1) {
            result = BigInteger.ONE;
        } else {
            result = generate(cache, index - 1).add(generate(cache, index - 2));
        }
        cache.set(index, result);
        return result;
    }

}
