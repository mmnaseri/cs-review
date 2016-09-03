package com.mmnaseri.cs.clrs.ch29.s1.impl;

import com.mmnaseri.cs.clrs.ch29.s1.ConstraintType;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgram;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgramConstraint;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgramConverter;
import com.mmnaseri.cs.clrs.common.NumberUtils;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (9/2/16, 6:54 PM)
 */
@Quality(Stage.TESTED)
public class SlackLinearProgramConverter<E extends Number> implements LinearProgramConverter<E> {

    private final Class<E> type;
    private final LinearProgramConverter<E> converter;

    public SlackLinearProgramConverter(Class<E> type) {
        this.type = type;
        converter = new StandardLinearProgramConverter<>(this.type);
    }

    @Override
    public LinearProgram<E> convert(LinearProgram<E> original) {
        //make sure that all standard form constraints are in the `<=` form.
        original = converter.convert(original);
        int slackness = 0;
        final List<LinearProgramConstraint<E>> constraints = new ArrayList<>();
        for (LinearProgramConstraint<E> constraint : original.getConstraints()) {
            if (!constraint.isSlack()) {
                slackness ++;
            }
        }
        int index = 0;
        for (LinearProgramConstraint<E> constraint : original.getConstraints()) {
            final List<E> coefficients = new ArrayList<>(constraint.getCoefficients());
            for (int i = 0; i < slackness; i++) {
                coefficients.add(NumberUtils.zero(type));
            }
            if (!constraint.isSlack()) {
                coefficients.set(constraint.getCoefficients().size() + index, NumberUtils.one(type));
                index ++;
            }
            final LinearProgramConstraint<E> substitute = new DefaultLinearProgramConstraint<>(type, coefficients, ConstraintType.EQUAL_TO, constraint.getValue());
            constraints.add(substitute);
        }
        final List<E> objectiveCoefficients = new ArrayList<>(original.getObjective().getCoefficients());
        for (int i = 0; i < slackness; i++) {
            objectiveCoefficients.add(NumberUtils.zero(type));
        }
        return new DefaultLinearProgram<>(constraints, new DefaultLinearFunction<>(type, objectiveCoefficients), slackness);
    }

}
