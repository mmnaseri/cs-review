package com.mmnaseri.cs.clrs.ch29.s1.impl;

import com.mmnaseri.cs.clrs.ch29.s1.LinearProgram;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgramConverter;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgramSolver;
import com.mmnaseri.cs.clrs.ch29.s1.dsl.impl.LinearProgramBuilder;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.NumberUtils;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.io.PrintWriter;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (9/7/16, 6:53 PM)
 */
@Quality(Stage.INCOMPLETE)
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
        final E divisor = program.getConstraints().get(leaving).getCoefficient(entering);
        final E newEnteringValue = NumberUtils.divide(program.getConstraints().get(leaving).getValue(), divisor);
        for (int i = 0; i < program.variables(); i++) {
            if (program.getBasicVariables().contains(i) || i == entering) {
                continue;
            }
            coefficients.set(entering, i, NumberUtils.divide(program.getConstraints().get(leaving).getCoefficient(i), divisor));
        }
        coefficients.set(entering, leaving, NumberUtils.divide(NumberUtils.one(type), divisor));
        return null;
    }

    public static void main(String[] args) throws Exception {
        final LinearProgram<Integer> program = LinearProgramBuilder.withObjective(3, 1, 2)
                .when(1, 1, 3).isLessThan(30)
                .and(2, 2, 5).isLessThan(24)
                .and(4, 1, 2).isLessThan(36)
                .maximize();
        final LinearProgramWriter<Integer> writer = new LinearProgramWriter<>(Integer.class);
        final SimplexLinearProgramSolver<Integer> solver = new SimplexLinearProgramSolver<>(Integer.class);
        final LinearProgram<Integer> converted = solver.converter.convert(program);
        System.out.println(writer.writeAsString(converted));
        final LinearProgram<Integer> first = solver.pivot(converted, 0, 5);
        System.out.println(writer.writeAsString(first));
    }

}
