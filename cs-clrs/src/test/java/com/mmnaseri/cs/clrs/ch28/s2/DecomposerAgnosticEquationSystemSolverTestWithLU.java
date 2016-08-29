package com.mmnaseri.cs.clrs.ch28.s2;

import com.mmnaseri.cs.clrs.ch28.s1.BaseEquationSystemSolverTest;
import com.mmnaseri.cs.clrs.ch28.s1.EquationSystemSolver;
import com.mmnaseri.cs.clrs.ch28.s1.LUMatrixDecomposer;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 10:20 PM)
 */
public class DecomposerAgnosticEquationSystemSolverTestWithLU extends BaseEquationSystemSolverTest {

    @Override
    protected EquationSystemSolver<Double> getEquationSystemSolver() {
        return new DecomposerAgnosticEquationSystemSolver<>(Double.class, new LUMatrixDecomposer<>(Double.class));
    }

}