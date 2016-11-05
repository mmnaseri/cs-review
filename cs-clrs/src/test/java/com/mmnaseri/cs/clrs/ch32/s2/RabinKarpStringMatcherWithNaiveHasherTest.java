package com.mmnaseri.cs.clrs.ch32.s2;

import com.mmnaseri.cs.clrs.ch32.s1.BaseStringMatcherTest;
import com.mmnaseri.cs.clrs.ch32.s1.StringMatcher;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/4/16, 7:11 PM)
 */
public class RabinKarpStringMatcherWithNaiveHasherTest extends BaseStringMatcherTest {

    @Override
    protected StringMatcher getStringMatcher() {
        return new RabinKarpStringMatcher(new NaiveRollingHasher());
    }

}