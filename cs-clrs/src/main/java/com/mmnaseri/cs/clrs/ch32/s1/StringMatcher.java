package com.mmnaseri.cs.clrs.ch32.s1;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/4/16, 6:26 PM)
 */
public interface StringMatcher {

  /**
   * Finds all the offsets at which the {@code needle} occurs within the {@code haystack} if there
   * are no occurrences
   *
   * @param needle the string to search for
   * @param haystack the string to look at for the occurrences of the needle
   * @return all indexes at which the needle can be found
   */
  Integer[] indexOf(String needle, String haystack);
}
