package com.mmnaseri.cs.clrs.ch32.s3;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/5/16, 2:09 PM)
 */
public interface TransitionFunctionFactory {

  TransitionFunction getInstance(String pattern);
}
