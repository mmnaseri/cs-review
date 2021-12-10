package com.mmnaseri.cs.clrs.ch32.s4;

import com.mmnaseri.cs.clrs.ch32.s1.AbstractStringMatcher;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/6/16, 12:48 PM)
 */
@Quality(Stage.TESTED)
public class KnuthMorrisPrattStringMatcher extends AbstractStringMatcher {

  private final PrefixFunctionFactory factory;

  public KnuthMorrisPrattStringMatcher(PrefixFunctionFactory factory) {
    this.factory = factory;
  }

  @Override
  protected List<Integer> findIndexOf(String needle, String haystack) {
    final PrefixFunction function = factory.getInstance(needle);
    final List<Integer> indices = new ArrayList<>();
    int matched = 0;
    for (int i = 0; i < haystack.length(); i++) {
      final char character = haystack.charAt(i);
      while (matched > 0 && character != needle.charAt(matched)) {
        matched = function.prefix(matched - 1);
      }
      if (character == needle.charAt(matched)) {
        matched++;
      }
      if (matched == needle.length()) {
        indices.add(i - needle.length() + 1);
        matched = function.prefix(matched - 1);
      }
    }
    return indices;
  }
}
