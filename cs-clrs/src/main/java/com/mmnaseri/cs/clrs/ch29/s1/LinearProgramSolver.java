package com.mmnaseri.cs.clrs.ch29.s1;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (9/7/16, 6:53 PM)
 */
public interface LinearProgramSolver<E extends Number> {

    List<E> solve(LinearProgram<E> program);

}
