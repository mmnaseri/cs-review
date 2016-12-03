package com.mmnaseri.cs.skiena.ch08.s6;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/30/16, 4:56 PM)
 */
public abstract class TerminalGrammarRule extends GrammarRule {

    private final String terminal;

    protected TerminalGrammarRule(String terminal) {
        this.terminal = terminal;
    }

    public String getTerminal() {
        return terminal;
    }

}
