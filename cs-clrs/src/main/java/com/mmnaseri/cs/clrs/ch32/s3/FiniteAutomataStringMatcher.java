package com.mmnaseri.cs.clrs.ch32.s3;

import com.mmnaseri.cs.clrs.ch32.s1.AbstractStringMatcher;
import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/6/16, 10:34 AM)
 */
@Quality(Stage.TESTED)
public class FiniteAutomataStringMatcher extends AbstractStringMatcher {

    private final TransitionFunctionFactory factory;

    public FiniteAutomataStringMatcher(TransitionFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    @Complexity("O(max(n, m^3.|Sigma|))")
    protected List<Integer> findIndexOf(String needle, String haystack) {
        final TransitionFunction function = getFunction(needle);
        final List<Integer> indices = new ArrayList<>();
        int state = 0;
        for (int i = 0; i < haystack.length(); i++) {
            final char character = haystack.charAt(i);
            state = function.transition(state, character);
            if (state == needle.length()) {
                indices.add(i - needle.length() + 1);
            }
        }
        return indices;
    }

    @Complexity(value = "O(m^3 . |Sigma|)", explanation = "Here, `m` is the length of the needle, and `Sigma` is the alphabet.")
    private TransitionFunction getFunction(String needle) {
        return factory.getInstance(needle);
    }

}
