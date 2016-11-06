package com.mmnaseri.cs.clrs.ch32.s3.impl;

import com.mmnaseri.cs.clrs.ch32.s3.TransitionFunction;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Map;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/5/16, 2:03 PM)
 */
@Quality(Stage.UNTESTED)
public class MapTransitionFunction implements TransitionFunction {

    private final Map<Integer, Map<Character, Integer>> mappings;

    public MapTransitionFunction(Map<Integer, Map<Character, Integer>> mappings) {
        this.mappings = mappings;
    }

    @Override
    public int transition(int state, char next) {
        if (mappings.containsKey(state)) {
            final Map<Character, Integer> transitions = mappings.get(state);
            if (transitions.containsKey(next)) {
                return transitions.get(next);
            }
            //reject
            return 0;
        }
        //reject
        return 0;
    }

}
