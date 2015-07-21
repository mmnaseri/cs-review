package com.mmnaseri.cs.clrs.ch02.sp;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HornerPolynomialCalculatorTest {

    @Test
    public void testEmptyCoefficientList() throws Exception {
        assertThat(new HornerPolynomialCalculator().calculate(0), is(0d));
        assertThat(new HornerPolynomialCalculator().calculate(1), is(0d));
        assertThat(new HornerPolynomialCalculator().calculate(2), is(0d));
        assertThat(new HornerPolynomialCalculator().calculate(3), is(0d));
    }

    @Test
    public void testCalculatingZeroValue() throws Exception {
        final HornerPolynomialCalculator calculator = new HornerPolynomialCalculator();
        //0 + (-2) x + 1 * x^2
        double x = 2.0;
        assertThat(calculator.calculate(x, 0, -2, 1), is(0 - 2 * x + x * x));
    }

    @Test
    public void testCalculatingPolynomial() throws Exception {
        final HornerPolynomialCalculator calculator = new HornerPolynomialCalculator();
        //x^4-2x^3+x^2-10, x = 1
        double x = 1.0;
        assertThat(calculator.calculate(x, -10, 0, 1, -2, 1), is(Math.pow(x, 4) - 2 * Math.pow(x, 3) + Math.pow(x, 2) - 10));
        //x^4-2x^3+x^2-10, x = -1
        x = -1.0;
        assertThat(calculator.calculate(x, -10, 0, 1, -2, 1), is(Math.pow(x, 4) - 2 * Math.pow(x, 3) + Math.pow(x, 2) - 10));
    }
}