package com.mmnaseri.cs.clrs.ch28.s2;

import com.mmnaseri.cs.clrs.ch28.s1.BaseEquationSystemSolverTest;
import com.mmnaseri.cs.clrs.ch28.s1.EquationSystemSolver;
import com.mmnaseri.cs.clrs.ch28.s1.LUPMatrixDecomposer;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 10:21 PM)
 */
public class DecomposerAgnosticEquationSystemSolverTestWithLUP extends BaseEquationSystemSolverTest {

    @Override
    protected EquationSystemSolver<Double> getEquationSystemSolver() {
        return new DecomposerAgnosticEquationSystemSolver<>(Double.class, new LUPMatrixDecomposer<>(Double.class));
    }

}