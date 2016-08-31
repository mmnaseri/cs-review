package com.mmnaseri.cs.clrs.ch29.s1.dsl.impl;

import com.mmnaseri.cs.clrs.ch29.s1.ConstraintType;
import com.mmnaseri.cs.clrs.ch29.s1.LinearFunction;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgram;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgramConstraint;
import com.mmnaseri.cs.clrs.ch29.s1.dsl.ConstraintDefinition;
import com.mmnaseri.cs.clrs.ch29.s1.dsl.ConstraintDefinitionConjunction;
import com.mmnaseri.cs.clrs.ch29.s1.dsl.ConstraintValue;
import com.mmnaseri.cs.clrs.ch29.s1.impl.DefaultLinearFunction;
import com.mmnaseri.cs.clrs.ch29.s1.impl.DefaultLinearProgram;
import com.mmnaseri.cs.clrs.ch29.s1.impl.DefaultLinearProgramConstraint;

import java.util.*;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/31/16, 11:11 AM)
 */
public class LinearProgramBuilder<E extends Number> implements ConstraintDefinition<E>, ConstraintDefinitionConjunction<E> {

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
        return new ConstraintValueBuilder<>(this, createList(first, rest));
    }

    @SafeVarargs
    @Override
    public final ConstraintValue<E> and(E first, E... rest) {
        return new ConstraintValueBuilder<>(this, createList(first, rest));
    }

    @Override
    public LinearProgram<E> maximize() {
        final LinearFunction<E> function = new DefaultLinearFunction<>(type, objective);
        final Set<LinearProgramConstraint<E>> constraints = new HashSet<>();
        for (int i = 0; i < this.constraints.size(); i++) {
            List<E> coefficients = this.constraints.get(i);
            final DefaultLinearProgramConstraint<E> constraint = new DefaultLinearProgramConstraint<>(type, coefficients, constraintTypes.get(i), values.get(i));
            constraints.add(constraint);
        }
        return new DefaultLinearProgram<>(constraints, function);
    }

    private static <E> List<E> createList(E first, E[] rest) {
        final List<E> objective = new ArrayList<>();
        objective.add(first);
        objective.addAll(Arrays.asList(rest));
        return objective;
    }

    private static class ConstraintValueBuilder<E extends Number> implements ConstraintValue<E> {

        private final LinearProgramBuilder<E> builder;

        private final List<E> coefficients;
        private ConstraintValueBuilder(LinearProgramBuilder<E> builder, List<E> coefficients) {
            this.builder = builder;
            this.coefficients = coefficients;
            if (coefficients.size() != builder.objective.size()) {
                throw new IllegalArgumentException("Expected constraint to have " + builder.objective.size() + " coefficients");
            }
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
        public ConstraintDefinitionConjunction<E> isLessThen(E value) {
            return add(ConstraintType.LESS_THAN_OR_EQUAL_TO, value);
        }

        private ConstraintDefinitionConjunction<E> add(ConstraintType type, E value) {
            final List<List<E>> constraints = new ArrayList<>(builder.constraints);
            constraints.add(coefficients);
            final List<ConstraintType> constraintTypes = new ArrayList<>(builder.constraintTypes);
            constraintTypes.add(type);
            final List<E> values = new ArrayList<>(builder.values);
            values.add(value);
            return new LinearProgramBuilder<>(builder.type, builder.objective, constraints, constraintTypes, values);
        }

    }

    @SafeVarargs
    public static <E extends Number> ConstraintDefinition<E> withObjective(E first, E... rest) {
        Objects.requireNonNull(first);
        //noinspection unchecked
        final Class<E> type = (Class<E>) first.getClass();
        final List<E> objective = createList(first, rest);
        return new LinearProgramBuilder<>(type, objective, Collections.<List<E>>emptyList(), Collections.<ConstraintType>emptyList(), Collections.<E>emptyList());
    }

}
