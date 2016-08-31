package com.mmnaseri.cs.clrs.ch29.s1.dsl;

import com.mmnaseri.cs.clrs.ch29.s1.LinearProgram;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/31/16, 11:06 AM)
 */
public interface Maximize<E extends Number> extends End {

    LinearProgram<E> maximize();

}
