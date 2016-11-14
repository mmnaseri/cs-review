package com.mmnaseri.cs.clrs.ch29.s1.impl;

import com.mmnaseri.cs.clrs.ch29.s1.ConstraintType;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgram;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgramConstraint;
import com.mmnaseri.cs.clrs.ch29.s1.dsl.impl.LinearProgramBuilder;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/31/16, 1:46 PM)
 */
public class StandardLinearProgramConverterTest {

    private StandardLinearProgramConverter<Integer> converter;

    @BeforeMethod
    public void setUp() throws Exception {
        converter = new StandardLinearProgramConverter<>(Integer.class);
    }

    @Test
    public void testThatSlacknessIsPreserved() throws Exception {
        final LinearProgram<Integer> program = createProgram();
        assertThat(program, is(not(slack())));
        final LinearProgram<Integer> converted = converter.convert(program);
        assertThat(converted, is(notNullValue()));
        assertThat(converted, is(not(slack())));
    }

    @Test
    public void testThatNumberOfVariablesIrPreserved() throws Exception {
        final LinearProgram<Integer> program = createProgram();
        final LinearProgram<Integer> converted = converter.convert(program);
        assertThat(converted, is(notNullValue()));
        assertThat(converted, hasVariables(program.variables()));
    }

    @Test
    public void testAllConstraintsAreLessThan() throws Exception {
        final LinearProgram<Integer> program = createProgram();
        final LinearProgram<Integer> converted = converter.convert(program);
        assertThat(converted, is(notNullValue()));
        for (LinearProgramConstraint<Integer> constraint : converted.getConstraints()) {
            assertThat(constraint.getConstraintType(), is(ConstraintType.LESS_THAN_OR_EQUAL_TO));
        }
    }

    @Test
    public void testWhenASignIsInvertedVariablesAreNegated() throws Exception {
        final LinearProgram<Integer> program = LinearProgramBuilder.withObjective(1, 2, 3)
                .when(4, 5, 6).isGreaterThan(-4).maximize();
        final LinearProgram<Integer> converted = converter.convert(program);
        assertThat(converted, is(notNullValue()));
        final LinearProgramConstraint<Integer> constraint = converted.getConstraints().iterator().next();
        assertThat(constraint, is(notNullValue()));
        assertThat(constraint.getConstraintType(), is(ConstraintType.LESS_THAN_OR_EQUAL_TO));
        assertThat(constraint.getValue(), is(4));
        assertThat(constraint.getCoefficient(0), is(-4));
        assertThat(constraint.getCoefficient(1), is(-5));
        assertThat(constraint.getCoefficient(2), is(-6));
    }

    private LinearProgram<Integer> createProgram() {
        return LinearProgramBuilder.withObjective(1, 2, 3)
                .when(3, 4, 5).isLessThan(1)
                .and(7, 7, 0).isGreaterThan(10)
                .and(1, 1, 1).isLessThan(-3)
                .and(1, 2, 1).isGreaterThan(3)
                .maximize();
    }

    private Matcher<? super LinearProgram<Integer>> hasVariables(final int variables) {
        return new BaseMatcher<LinearProgram<Integer>>() {
            @Override
            public boolean matches(Object item) {
                return item instanceof LinearProgram && ((LinearProgram) item).variables() == variables;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("a program with " + variables + " variables");
            }
        };
    }

    private Matcher<LinearProgram<?>> slack() {
        return new BaseMatcher<LinearProgram<?>>() {
            @Override
            public boolean matches(Object item) {
                return item instanceof LinearProgram && ((LinearProgram) item).isSlack();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("slack");
            }
        };
    }

}