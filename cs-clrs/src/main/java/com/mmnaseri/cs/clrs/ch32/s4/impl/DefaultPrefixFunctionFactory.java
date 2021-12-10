package com.mmnaseri.cs.clrs.ch32.s4.impl;

import com.mmnaseri.cs.clrs.ch32.s4.PrefixFunction;
import com.mmnaseri.cs.clrs.ch32.s4.PrefixFunctionFactory;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/6/16, 11:18 AM)
 */
@Quality(Stage.TESTED)
public class DefaultPrefixFunctionFactory implements PrefixFunctionFactory {

  @Override
  public PrefixFunction getInstance(String pattern) {
    final int[] prefixes = new int[pattern.length()];
    prefixes[0] = 0;
    int length = 0;
    for (int i = 1; i < pattern.length(); i++) {
      final char character = pattern.charAt(i);
      while (length > 0 && character != pattern.charAt(length)) {
        length = prefixes[length - 1];
      }
      if (character == pattern.charAt(length)) {
        length++;
      }
      prefixes[i] = length;
    }
    return new ArrayPrefixFunction(prefixes);
  }
}
