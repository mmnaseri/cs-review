package com.mmnaseri.cs.skiena.ch02.s6;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 12:41 AM)
 */
@Quality(Stage.TESTED)
public class FastPowerCalculator implements PowerCalculator {

    @Complexity(value = "O(log(n))", explanation = "n is the exponent. We divide the problem size by half at each recursion.")
    @Override
    public double calculate(double base, int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        }
        if (base == 0D) {
            return 0D;
        } else if (base == 1D) {
            return 1D;
        }
        if (exponent == 0) {
            return 1;
        } else if (exponent == 1) {
            return base;
        }
        double intermediate = calculate(base, exponent / 2);
        intermediate *= intermediate;
        return exponent % 2 == 1 ? base * intermediate : intermediate;
    }

}
