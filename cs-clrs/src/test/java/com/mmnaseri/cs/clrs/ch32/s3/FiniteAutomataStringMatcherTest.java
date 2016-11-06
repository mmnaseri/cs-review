package com.mmnaseri.cs.clrs.ch32.s3;

import com.mmnaseri.cs.clrs.ch32.s1.BaseStringMatcherTest;
import com.mmnaseri.cs.clrs.ch32.s1.StringMatcher;
import com.mmnaseri.cs.clrs.ch32.s3.impl.DefaultTransitionFunctionFactory;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/6/16, 10:45 AM)
 */
public class FiniteAutomataStringMatcherTest extends BaseStringMatcherTest {

    @Override
    protected StringMatcher getStringMatcher() {
        return new FiniteAutomataStringMatcher(new DefaultTransitionFunctionFactory());
    }

}