package com.mmnaseri.cs.skiena.ch02.s6;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 12:46 AM)
 */
public class FastPowerCalculatorTest {

    @DataProvider
    public Object[][] numberProvider() {
        final List<Object[]> result = new ArrayList<>();
        final Random random = new Random();
        for (int i = 0; i < 100; i++) {
            final double base = random.nextLong() / (double) (1 + random.nextInt());
            final int exponent = Math.abs(random.nextInt());
            result.add(new Object[]{base, exponent});
        }
        return result.toArray(new Object[result.size()][]);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testThatItDoesNotSupportNegativeNumber() throws Exception {
        calculate(100, -1);
    }

    @Test(dataProvider = "numberProvider")
    public void testCalculation(double base, int exponent) throws Exception {
        assertThat(calculate(base, exponent), is(Math.pow(base, exponent)));
    }

    private double calculate(double base, int exponent) {
        return new FastPowerCalculator().calculate(base, exponent);
    }

}