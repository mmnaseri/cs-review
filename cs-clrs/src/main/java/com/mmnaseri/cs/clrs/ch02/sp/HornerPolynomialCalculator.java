package com.mmnaseri.cs.clrs.ch02.sp;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 3:35 AM)
 */
@Quality(Stage.DOCUMENTED)
public class HornerPolynomialCalculator {

    /**
     * This method seeks to calculate the value of a single variable polynomial expression given that the
     * value of the variable is fixed statically
     * @param x               the value of the variable
     * @param coefficients    the coefficients in increasing order of importance
     * @return the value of the polynomial
     */
    @Complexity("O(n)")
    public double calculate(double x, double... coefficients) {
        /**
         * The calculation follows the following general pattern: at each step we will calculate the value of a single
         * linear expression which only depends on the first-order value of the variable. This means that an expression
         * such as "a0 + a1 * x + a2 * x^2 + a3 * x^3" will be written as follows:
         *     A = x * a3 + a2
         *     B = x * A + a1
         *     C = x * B + a0
         * which leaves us with no further coefficients, and thus yields the result:
         *     C = x * (x * (x * a3 + a2) + a1) + a0 = x * (x^2 * a3 + a2 * x + a1) + a0 = x^3 * a3 + x^2 * a2 + x * a1 + a0
         * which is what we began with.
         */
        if (coefficients.length == 0) {
            return 0;
        }
        double result = coefficients[coefficients.length - 1];
        for (int i = coefficients.length - 2; i >= 0; i --) {
            result = coefficients[i] + x * result;
        }
        return result;
    }

}
