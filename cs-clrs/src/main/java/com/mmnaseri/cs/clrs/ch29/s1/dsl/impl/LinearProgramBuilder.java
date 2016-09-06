package com.mmnaseri.cs.clrs.ch29.s1.dsl.impl;

import com.mmnaseri.cs.clrs.ch29.s1.ConstraintType;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgram;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgramConstraint;
import com.mmnaseri.cs.clrs.ch29.s1.dsl.*;
import com.mmnaseri.cs.clrs.ch29.s1.impl.DefaultLinearFunction;
import com.mmnaseri.cs.clrs.ch29.s1.impl.DefaultLinearProgram;
import com.mmnaseri.cs.clrs.ch29.s1.impl.DefaultLinearProgramConstraint;
import com.mmnaseri.cs.clrs.common.NumberUtils;

import java.util.*;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/31/16, 11:51 AM)
 */
public class LinearProgramBuilder<E extends Number> implements Start<E>, ConstraintDefinitionConjunction<E> {

    private final Class<E> type;
    private final List<E> objective;
    private final E objectiveOffset;
    private final List<List<E>> constraints;
    private final List<ConstraintType> constraintTypes;
    private final List<E> values;
    private final List<E> offsets;
    private final int slackness;

    private LinearProgramBuilder(Class<E> type, List<E> objective, E objectiveOffset, List<List<E>> constraints, List<ConstraintType> constraintTypes, List<E> values, List<E> offsets, int slackness) {
        this.type = type;
        this.objective = objective;
        this.objectiveOffset = objectiveOffset;
        this.constraints = constraints;
        this.constraintTypes = constraintTypes;
        this.values = values;
        this.offsets = offsets;
        this.slackness = slackness;
    }

    @SafeVarargs
    @Override
    public final ConstraintOffset<E> when(E first, E... rest) {
        if ((rest.length + 1) != objective.size()) {
            throw new IllegalArgumentException("Expected constraint to have " + objective.size() + " coefficients");
        }
        return new ConstraintValueBuilder<>(this, createList(first, rest));
    }

    @SafeVarargs
    @Override
    public final ConstraintOffset<E> and(E first, E... rest) {
        return when(first, rest);
    }

    @Override
    public LinearProgram<E> maximize() {
        final List<LinearProgramConstraint<E>> constraints = new ArrayList<>();
        for (int i = 0; i < this.constraints.size(); i++) {
            final List<E> coefficients = this.constraints.get(i);
            constraints.add(new DefaultLinearProgramConstraint<>(type, coefficients, constraintTypes.get(i), values.get(i), offsets.get(i)));
        }
        return new DefaultLinearProgram<>(constraints, new DefaultLinearFunction<>(type, objective, objectiveOffset), slackness);
    }

    @Override
    public ConstraintDefinition<E> andSlackness(int slackness) {
        return new LinearProgramBuilder<>(type, objective, objectiveOffset, constraints, constraintTypes, values, offsets, slackness);
    }

    @Override
    public Slackness<E> offsetBy(E offset) {
        return new LinearProgramBuilder<>(type, objective, offset, constraints, constraintTypes, values, offsets, slackness);
    }

    private static class ConstraintValueBuilder<E extends Number> implements ConstraintOffset<E> {

        private final LinearProgramBuilder<E> builder;
        private final List<E> coefficients;
        private final E offset;

        private ConstraintValueBuilder(LinearProgramBuilder<E> builder, List<E> coefficients) {
            this(builder, coefficients, NumberUtils.zero(builder.type));
        }

        private ConstraintValueBuilder(LinearProgramBuilder<E> builder, List<E> coefficients, E offset) {
            this.builder = builder;
            this.coefficients = coefficients;
            this.offset = offset;
        }

        @Override
        public ConstraintDefinitionConjunction<E> is(E value) {
            return add(ConstraintType.EQUAL_TO, value);
        }

        @Override
        public ConstraintDefinitionConjunction<E> isGreaterThan(E value) {
            if (builder.slackness > 0) {
                throw new IllegalStateException("Cannot create a non-slack constraint for a linear program with slackness " + builder.slackness);
            }
            return add(ConstraintType.GREATER_THAN_OR_EQUAL_TO, value);
        }

        @Override
        public ConstraintDefinitionConjunction<E> isLessThan(E value) {
            if (builder.slackness > 0) {
                throw new IllegalStateException("Cannot create a non-slack constraint for a linear program with slackness " + builder.slackness);
            }
            return add(ConstraintType.LESS_THAN_OR_EQUAL_TO, value);
        }

        private ConstraintDefinitionConjunction<E> add(ConstraintType type, E value) {
            final List<List<E>> constraints = new ArrayList<>(builder.constraints);
            final List<ConstraintType> constraintTypes = new ArrayList<>(builder.constraintTypes);
            final List<E> values = new ArrayList<>(builder.values);
            final List<E> offsets = new ArrayList<>(builder.offsets);
            constraints.add(coefficients);
            constraintTypes.add(type);
            values.add(value);
            offsets.add(offset);
            return new LinearProgramBuilder<>(builder.type, builder.objective, builder.objectiveOffset, constraints, constraintTypes, values, offsets, builder.slackness);
        }

        @Override
        public ConstraintValue<E> offsetBy(E offset) {
            return new ConstraintValueBuilder<>(builder, coefficients, offset);
        }

    }

    @SafeVarargs
    private static <E> List<E> createList(E first, E... rest) {
        final List<E> list = new ArrayList<>();
        list.add(first);
        list.addAll(Arrays.asList(rest));
        return list;
    }

    @SafeVarargs
    public static <E extends Number> Start<E> withObjective(E first, E... rest) {
        Objects.requireNonNull(first);
        //noinspection unchecked
        final Class<E> type = (Class<E>) first.getClass();
        return new LinearProgramBuilder<>(type, createList(first, rest), NumberUtils.zero(type), Collections.<List<E>>emptyList(), Collections.<ConstraintType>emptyList(), Collections.<E>emptyList(), Collections.<E>emptyList(), 0);
    }

}
