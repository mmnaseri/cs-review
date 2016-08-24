package com.mmnaseri.cs.clrs.ch21.s3;

import com.mmnaseri.cs.clrs.ch21.DisjointSet;
import com.mmnaseri.cs.clrs.ch21.Element;
import com.mmnaseri.cs.clrs.ch21.s1.BaseDisjointSetTest;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/2/15, 11:55 AM)
 */
public class SimpleForestDisjointSetTest extends BaseDisjointSetTest {

    @Override
    protected DisjointSet<Element<Integer>, Integer> createSet() {
        //noinspection unchecked
        return new SimpleForestDisjointSet();
    }

}