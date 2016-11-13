package com.mmnaseri.cs.skiena.ch08.s1;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.math.BigInteger;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/13/16, 8:35 AM)
 */
@Quality(Stage.UNTESTED)
public class NaiveFibonacciNumberGenerator implements FibonacciNumberGenerator {

    @Override
    public BigInteger generate(int index) {
        return index <= 1 ? BigInteger.ONE : generate(index - 1).add(generate(index - 2));
    }

}
