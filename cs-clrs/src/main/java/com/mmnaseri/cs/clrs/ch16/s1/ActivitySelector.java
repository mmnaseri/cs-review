package com.mmnaseri.cs.clrs.ch16.s1;

import java.util.Set;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/22/15)
 */
public interface ActivitySelector {

    Set<Integer> select(Activity... activities);

}
