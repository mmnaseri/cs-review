package com.mmnaseri.cs.clrs.ch15.s4;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/20/15)
 */
public interface LongestCommonSubSequenceFinder<E> {

  List<E> find(List<E> first, List<E> second);
}
