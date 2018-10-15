package com.mmnaseri.cs.clrs.ch15.sp;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Ramin Farhanian (rf.tech@icloud.com)
 * @since 1.0 (04/10/2018)
 */
public abstract class BaseEditDistanceCalculatorTest {

    protected abstract EditDistanceCalculator getEditDistanceCalculator();

    @Test(dataProvider = "testSuiteDataProvider")
    public void testFunctionality(@SuppressWarnings("UnusedParameters") String name, String first, String second, List<EditOperation> expectedResult, CostFunction costFunction) throws Exception {
        final EditDistanceCalculator editDistanceCalculator = getEditDistanceCalculator();
        final List<EditOperation> expenses = editDistanceCalculator.calculate(first, second, costFunction);
        if (expectedResult == null) {
            assertThat(expenses, is(nullValue()));
        } else {
            assertThat(expenses, is(notNullValue()));
            assertThat(expenses, hasSize(expectedResult.size()));
            assertThat(EditOperation.getTotalCost(expenses), is(equalTo(EditOperation.getTotalCost(expectedResult))));
        }
    }

    @DataProvider
    protected Object[][] testSuiteDataProvider() {
        return new Object[][]{
                new Object[]{"One replacement", "a", "j",
                        Arrays.asList(
                                new EditOperation(EditOperationType.REPLACE, "a for j", 30)), new DefaultCostFunction()},
                new Object[]{"One delete", "x", "",
                        Arrays.asList(
                                new EditOperation(EditOperationType.DELETE, "x", 40)), new DefaultCostFunction()},
                new Object[]{"One insert", "", "x",
                        Arrays.asList(
                                new EditOperation(EditOperationType.INSERT, "x", 40)), new DefaultCostFunction()},
                new Object[]{"One Copy", "x", "x",
                        Arrays.asList(
                                new EditOperation(EditOperationType.COPY, "x", 0)), new DefaultCostFunction()},
                new Object[]{"One Twiddle", "zy", "yz",
                        Arrays.asList(
                                new EditOperation(EditOperationType.TWIDDLE, "zy", 10)), new DefaultCostFunction()},
                new Object[]{"Multiple Insert", "a", "jklmn",
                        Arrays.asList(
                                new EditOperation(EditOperationType.REPLACE, "a for j", 30),
                                new EditOperation(EditOperationType.INSERT, "k", 40),
                                new EditOperation(EditOperationType.INSERT, "l", 40),
                                new EditOperation(EditOperationType.INSERT, "m", 40),
                                new EditOperation(EditOperationType.INSERT, "n", 40)), new DefaultCostFunction()},
                new Object[]{"Multiple Delete", "jklmn", "a",
                        Arrays.asList(
                                new EditOperation(EditOperationType.REPLACE, "j for a", 30),
                                new EditOperation(EditOperationType.DELETE, "k", 40),
                                new EditOperation(EditOperationType.DELETE, "l", 40),
                                new EditOperation(EditOperationType.DELETE, "m", 40),
                                new EditOperation(EditOperationType.DELETE, "n", 40)), new DefaultCostFunction()},
                new Object[]{"Multiple Copy Insert Senario", "fred", "frxxedxx",
                        Arrays.asList(
                                new EditOperation(EditOperationType.COPY, "f", 0),
                                new EditOperation(EditOperationType.COPY, "r", 0),
                                new EditOperation(EditOperationType.INSERT, "e for x", 40),
                                new EditOperation(EditOperationType.INSERT, "e for x", 40),
                                new EditOperation(EditOperationType.COPY, "e", 0),
                                new EditOperation(EditOperationType.COPY, "d", 0),
                                new EditOperation(EditOperationType.INSERT, "x", 40),
                                new EditOperation(EditOperationType.INSERT, "x", 40)), new DefaultCostFunction()},
                new Object[]{"Multiple replacement", "abcdefg", "hijklmn",
                        Arrays.asList(
                                new EditOperation(EditOperationType.REPLACE, "a for h", 30),
                                new EditOperation(EditOperationType.REPLACE, "b for i", 30),
                                new EditOperation(EditOperationType.REPLACE, "c for j", 30),
                                new EditOperation(EditOperationType.REPLACE, "d for k", 30),
                                new EditOperation(EditOperationType.REPLACE, "e for l", 30),
                                new EditOperation(EditOperationType.REPLACE, "f for m", 30),
                                new EditOperation(EditOperationType.REPLACE, "g for n", 30)), new DefaultCostFunction()},
                new Object[]{"Multiple Copies", "xyz", "xyz",
                        Arrays.asList(
                                new EditOperation(EditOperationType.COPY, "x", 0),
                                new EditOperation(EditOperationType.COPY, "y", 0),
                                new EditOperation(EditOperationType.COPY, "z", 0)), new DefaultCostFunction()},
                new Object[]{"One replacement using a different cost function", "a", "j",
                        Arrays.asList(
                                new EditOperation(EditOperationType.INSERT, "a for j", 20),
                                new EditOperation(EditOperationType.DELETE, "a", 19)
                        ), new ExpensiveReplacementCostFunction()}

        };
    }

}