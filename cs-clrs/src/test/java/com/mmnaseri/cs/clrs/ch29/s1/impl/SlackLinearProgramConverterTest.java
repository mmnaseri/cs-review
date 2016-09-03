package com.mmnaseri.cs.clrs.ch29.s1.impl;

import com.mmnaseri.cs.clrs.ch29.s1.ConstraintType;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgram;
import com.mmnaseri.cs.clrs.ch29.s1.dsl.impl.LinearProgramBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (9/2/16, 10:23 PM)
 */
public class SlackLinearProgramConverterTest {

    private SlackLinearProgramConverter<Integer> converter;

    @BeforeMethod
    public void setUp() throws Exception {
        converter = new SlackLinearProgramConverter<>(Integer.class);
    }

    @Test
    public void testSlackness() throws Exception {
        final LinearProgram<Integer> program = program();
        assertThat(program.isSlack(), is(false));
        assertThat(program.getSlackness(), is(0));
        assertThat(program.variables(), is(3));
        final LinearProgram<Integer> converted = converter.convert(program);
        assertThat(converted, is(notNullValue()));
        assertThat(converted.isSlack(), is(true));
        assertThat(converted.getSlackness(), is(3));
    }

    @Test
    public void testConversion() throws Exception {
        final LinearProgram<Integer> program = program();
        final LinearProgram<Integer> converted = converter.convert(program);
        assertThat(converted.getObjective(), is(notNullValue()));
        assertThat(converted.getObjective().getCoefficients(), contains(2, -3, 3, 0, 0, 0));
        assertThat(converted.getConstraints(), hasSize(3));
        assertThat(converted.getConstraints().get(0), is(notNullValue()));
        assertThat(converted.getConstraints().get(0).isSlack(), is(true));
        assertThat(converted.getConstraints().get(0).getConstraintType(), is(ConstraintType.EQUAL_TO));
        assertThat(converted.getConstraints().get(0).getCoefficients(), contains(1, 1, -1, 1, 0, 0));
        assertThat(converted.getConstraints().get(1), is(notNullValue()));
        assertThat(converted.getConstraints().get(1).isSlack(), is(true));
        assertThat(converted.getConstraints().get(1).getConstraintType(), is(ConstraintType.EQUAL_TO));
        assertThat(converted.getConstraints().get(1).getCoefficients(), contains(-1, -1, 1, 0, 1, 0));
        assertThat(converted.getConstraints().get(2), is(notNullValue()));
        assertThat(converted.getConstraints().get(2).isSlack(), is(true));
        assertThat(converted.getConstraints().get(2).getConstraintType(), is(ConstraintType.EQUAL_TO));
        assertThat(converted.getConstraints().get(2).getCoefficients(), contains(1, -2, 2, 0, 0, 1));
    }

    private LinearProgram<Integer> program() {
        return LinearProgramBuilder.withObjective(2, -3, 3)
                .when(1, 1, -1).isLessThan(7)
                .and(-1, -1, 1).isLessThan(-7)
                .and(1, -2, 2).isLessThan(4)
                .maximize();
    }

}