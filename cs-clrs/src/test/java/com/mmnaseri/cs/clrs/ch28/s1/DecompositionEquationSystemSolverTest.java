package com.mmnaseri.cs.clrs.ch28.s1;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 9:57 PM)
 */
public class DecompositionEquationSystemSolverTest extends BaseEquationSystemSolverTest {

    @Override
    protected EquationSystemSolver<Double> getEquationSystemSolver() {
        return new DecompositionEquationSystemSolver<>(Double.class, new LUPMatrixDecomposer<>(Double.class));
    }

}