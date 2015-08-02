package com.mmnaseri.cs.clrs.common;

import com.mmnaseri.cs.clrs.ch18.s1.Indexed;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/1/15, 2:48 PM)
 */
public interface DynamicSet<I extends Indexed<Integer>> {

    int size();

    boolean isEmpty();

    I find(Integer key);

    void add(I value);

    void delete(Integer index);

    I minimum();

    I maximum();

    I successor(Integer key);

    I predecessor(Integer key);

}
