package com.mmnaseri.cs.clrs.ch15.s4;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/20/15)
 */
public interface LargestCommonSubSequenceDiscoverer<E> {

    List<E> find(List<E> first, List<E> second);

}
