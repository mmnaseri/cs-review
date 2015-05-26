package com.mmnaseri.cs.algorithm.clrs.ch2.p;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 3:35 AM)
 */
public class HornerPolynomialCalculator {

    public double calculate(double[] coefficients, double x) {
        double result = 0;
        for (int i = coefficients.length - 1; i >= 0; i --) {
            result = coefficients[i] + x * result;
        }
        return result;
    }

}
