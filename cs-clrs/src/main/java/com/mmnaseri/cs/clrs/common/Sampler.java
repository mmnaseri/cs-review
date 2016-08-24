package com.mmnaseri.cs.clrs.common;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (5/27/15, 12:32 AM)
 */
public interface Sampler<E> {

    E[] sample(E[] array, int size);

}
