package com.mmnaseri.cs.clrs.ch29.s1.impl;

import com.mmnaseri.cs.clrs.ch29.s1.LinearProgram;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgramConverter;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgramSolver;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (9/7/16, 6:53 PM)
 */
public class SimplexLinearProgramSolver<E extends Number> implements LinearProgramSolver<E> {

    private final Class<E> type;
    private final LinearProgramConverter<E> converter;

    public SimplexLinearProgramSolver(Class<E> type) {
        this.type = type;
        converter = new SlackLinearProgramConverter<>(type);
    }

    @Override
    public List<E> solve(LinearProgram<E> program) {
        final LinearProgram<E> converted = converter.convert(program);
        final List<E> result = doSolve(converted);
        if (result == null) {
            throw new IllegalStateException("No result was appropriated for this program");
        }
        if (result.size() != program.variables()) {
            throw new IllegalStateException("Could not find solutions for all the variables");
        }
        return result;
    }

    private List<E> doSolve(LinearProgram<E> program) {
        
        return null;
    }

    private LinearProgram<E> pivot(LinearProgram<E> program, int entering, int leaving) {
        final Matrix<E> coefficients = new ArrayMatrix<>(program.getConstraints().size(), program.variables());
        final Matrix<E> values = new ArrayMatrix<>(program.getConstraints().size(), 1);
        return null;
    }

}
