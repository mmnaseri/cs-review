package com.mmnaseri.cs.clrs.ch32.s1;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/4/16, 6:27 PM)
 */
public class NaiveStringMatcherTest extends BaseStringMatcherTest {

    @Override
    protected StringMatcher getStringMatcher() {
        return new NaiveStringMatcher();
    }

}