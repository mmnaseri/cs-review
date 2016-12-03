package com.mmnaseri.cs.skiena.ch08.s6.impl;

import com.mmnaseri.cs.skiena.ch08.s6.ContextFreeGrammar;
import com.mmnaseri.cs.skiena.ch08.s6.GrammarRule;

import java.util.List;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (12/2/16, 7:35 PM)
 */
public class DefaultContextFreeGrammar implements ContextFreeGrammar {

    private GrammarRule startingRule;
    private List<GrammarRule> rules;

    @Override
    public GrammarRule getStartingRule() {
        return startingRule;
    }

    public DefaultContextFreeGrammar setStartingRule(GrammarRule startingRule) {
        this.startingRule = startingRule;
        return this;
    }

    @Override
    public List<GrammarRule> getRules() {
        return rules;
    }

    public DefaultContextFreeGrammar setRules(List<GrammarRule> rules) {
        this.rules = rules;
        return this;
    }

}
