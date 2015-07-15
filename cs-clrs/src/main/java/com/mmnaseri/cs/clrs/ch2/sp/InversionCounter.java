package com.mmnaseri.cs.clrs.ch2.sp;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/11/15, 1:41 AM)
 */
public interface InversionCounter<E extends Comparable<E>> {

    @SuppressWarnings("unchecked")
    int count(E... items);

}
