package com.mmnaseri.cs.skiena.ch08.s6;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/30/16, 4:56 PM)
 */
public interface NonTerminalGrammarRule extends GrammarRule {

    List<GrammarRule> getTerms();

}
