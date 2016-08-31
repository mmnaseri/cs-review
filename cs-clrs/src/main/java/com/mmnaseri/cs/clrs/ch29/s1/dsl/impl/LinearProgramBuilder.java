package com.mmnaseri.cs.clrs.ch29.s1.dsl.impl;

import com.mmnaseri.cs.clrs.ch29.s1.ConstraintType;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgram;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgramConstraint;
import com.mmnaseri.cs.clrs.ch29.s1.dsl.ConstraintDefinitionConjunction;
import com.mmnaseri.cs.clrs.ch29.s1.dsl.ConstraintValue;
import com.mmnaseri.cs.clrs.ch29.s1.dsl.Start;
import com.mmnaseri.cs.clrs.ch29.s1.impl.DefaultLinearFunction;
import com.mmnaseri.cs.clrs.ch29.s1.impl.DefaultLinearProgram;
import com.mmnaseri.cs.clrs.ch29.s1.impl.DefaultLinearProgramConstraint;

import java.util.*;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/31/16, 11:51 AM)
 */
public class LinearProgramBuilder<E extends Number> implements Start<E>, ConstraintDefinitionConjunction<E> {

    private final Class<E> type;
    private final List<E> objective;
    private final List<List<E>> constraints;
    private final List<ConstraintType> constraintTypes;
    private final List<E> values;

    private LinearProgramBuilder(Class<E> type, List<E> objective, List<List<E>> constraints, List<ConstraintType> constraintTypes, List<E> values) {
        this.type = type;
        this.objective = objective;
        this.constraints = constraints;
        this.constraintTypes = constraintTypes;
        this.values = values;
    }

    @SafeVarargs
    @Override
    public final ConstraintValue<E> when(E first, E... rest) {
        if ((rest.length + 1) != objective.size()) {
            throw new IllegalArgumentException("Expected constraint to have " + objective.size() + " coefficients");
        }
        return new ConstraintValueBuilder<>(this, createList(first, rest));
    }

    @SafeVarargs
    @Override
    public final ConstraintValue<E> and(E first, E... rest) {
        return when(first, rest);
    }

    @Override
    public LinearProgram<E> maximize() {
        final Set<LinearProgramConstraint<E>> constraints = new HashSet<>();
        for (int i = 0; i < this.constraints.size(); i++) {
            final List<E> coefficients = this.constraints.get(i);
            constraints.add(new DefaultLinearProgramConstraint<E>(type, coefficients, constraintTypes.get(i), values.get(i)));
        }
        return new DefaultLinearProgram<>(constraints, new DefaultLinearFunction<>(type, objective));
    }

    private static class ConstraintValueBuilder<E extends Number> implements ConstraintValue<E> {

        private final LinearProgramBuilder<E> builder;
        private final List<E> coefficients;

        private ConstraintValueBuilder(LinearProgramBuilder<E> builder, List<E> coefficients) {
            this.builder = builder;
            this.coefficients = coefficients;
        }

        @Override
        public ConstraintDefinitionConjunction<E> is(E value) {
            return add(ConstraintType.EQUAL_TO, value);
        }

        @Override
        public ConstraintDefinitionConjunction<E> isGreaterThan(E value) {
            return add(ConstraintType.GREATER_THAN_OR_EQUAL_TO, value);
        }

        @Override
        public ConstraintDefinitionConjunction<E> isLessThan(E value) {
            return add(ConstraintType.LESS_THAN_OR_EQUAL_TO, value);
        }

        private ConstraintDefinitionConjunction<E> add(ConstraintType type, E value) {
            final List<List<E>> constraints = new ArrayList<>(builder.constraints);
            final List<ConstraintType> constraintTypes = new ArrayList<>(builder.constraintTypes);
            final List<E> values = new ArrayList<>(builder.values);
            constraints.add(coefficients);
            constraintTypes.add(type);
            values.add(value);
            return new LinearProgramBuilder<>(builder.type, builder.objective, constraints, constraintTypes, values);
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
        return new LinearProgramBuilder<>(type, createList(first, rest), Collections.<List<E>>emptyList(), Collections.<ConstraintType>emptyList(), Collections.<E>emptyList());
    }

}
