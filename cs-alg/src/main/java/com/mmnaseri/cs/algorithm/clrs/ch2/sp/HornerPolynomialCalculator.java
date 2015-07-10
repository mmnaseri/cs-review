package com.mmnaseri.cs.algorithm.clrs.ch2.sp;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 3:35 AM)
 */
public class HornerPolynomialCalculator {

    public double calculate(double x, double... coefficients) {
        if (coefficients.length == 0) {
            return 0;
        }
        double result = coefficients[coefficients.length - 1];
        for (int i = coefficients.length - 2; i >= 0; i --) {
            result = coefficients[i] + x * result;
        }
        return result;
    }

    //a0+a1x+a2x^2=a0+x(a1+x(a2))
    //x^2-2x+0 = 0 + x (-2 + x * 1)

}
