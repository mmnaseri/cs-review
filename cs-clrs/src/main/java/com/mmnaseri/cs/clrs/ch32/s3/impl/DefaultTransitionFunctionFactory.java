package com.mmnaseri.cs.clrs.ch32.s3.impl;

import com.mmnaseri.cs.clrs.ch32.s3.TransitionFunction;
import com.mmnaseri.cs.clrs.ch32.s3.TransitionFunctionFactory;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/5/16, 2:10 PM)
 */
@Quality(Stage.UNTESTED)
public class DefaultTransitionFunctionFactory implements TransitionFunctionFactory {

  private String extractAlphabet(String pattern) {
    StringBuilder alphabet = new StringBuilder();
    for (int i = 0; i < pattern.length(); i++) {
      if (alphabet.toString().indexOf(pattern.charAt(i)) == -1) {
        alphabet.append(pattern.charAt(i));
      }
    }
    return alphabet.toString();
  }

  @Override
  public TransitionFunction getInstance(String pattern) {
    final String alphabet = extractAlphabet(pattern);
    final Map<Integer, Map<Character, Integer>> mappings = new HashMap<>();
    for (int i = 0; i <= pattern.length(); i++) {
      final Map<Character, Integer> transitions = new HashMap<>();
      mappings.put(i, transitions);
      for (int j = 0; j < alphabet.length(); j++) {
        final char character = alphabet.charAt(j);
        int next = Math.min(pattern.length(), i + 1);
        while (!pattern
            .substring(0, i)
            .concat(String.valueOf(character))
            .endsWith(pattern.substring(0, next))) {
          next--;
        }
        transitions.put(character, next);
      }
    }
    return new MapTransitionFunction(mappings);
  }
}
