package com.mmnaseri.cs.clrs.ch32.s4;

import com.mmnaseri.cs.clrs.ch32.s1.BaseStringMatcherTest;
import com.mmnaseri.cs.clrs.ch32.s1.StringMatcher;
import com.mmnaseri.cs.clrs.ch32.s4.impl.DefaultPrefixFunctionFactory;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/6/16, 12:49 PM)
 */
public class KnuthMorrisPrattStringMatcherTest extends BaseStringMatcherTest {

    @Override
    protected StringMatcher getStringMatcher() {
        return new KnuthMorrisPrattStringMatcher(new DefaultPrefixFunctionFactory());
    }

}