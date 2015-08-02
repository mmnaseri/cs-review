package com.mmnaseri.cs.clrs.ch21.s1;

import com.mmnaseri.cs.clrs.ch21.DisjointSet;
import com.mmnaseri.cs.clrs.ch21.Element;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 11:10 AM)
 */
public class BasicDisjointSetTest extends BaseDisjointSetTest {

    @Override
    protected DisjointSet<Element<Integer>, Integer> createSet() {
        //noinspection unchecked
        return new BasicDisjointSet();
    }

}