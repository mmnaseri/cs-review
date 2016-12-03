package com.mmnaseri.cs.skiena.ch08.s6;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/30/16, 4:56 PM)
 */
public abstract class NonTerminalGrammarRule extends GrammarRule {

    private final List<GrammarRule> terms;

    protected NonTerminalGrammarRule(List<GrammarRule> terms) {
        this.terms = terms;
    }

    public List<GrammarRule> getTerms() {
        return terms;
    }

}
