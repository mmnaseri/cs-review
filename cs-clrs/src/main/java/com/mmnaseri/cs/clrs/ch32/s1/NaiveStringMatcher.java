package com.mmnaseri.cs.clrs.ch32.s1;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * In this naive implementation we just gloss over the string, and whenever something seems interesting, we start matching it against the pattern
 *
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/4/16, 6:27 PM)
 */
@Quality(Stage.TESTED)
public class NaiveStringMatcher extends AbstractStringMatcher {

    @Complexity(value = "O((n - m + 1)m) or simply O(mn)", explanation = "m is the length of the needle, and n is the length of the haystack")
    @Override
    protected List<Integer> findIndexOf(String needle, String haystack) {
        final List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                boolean match = true;
                for (int j = 0; j < needle.length(); j++) {
                    if (haystack.charAt(i + j) != needle.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    indices.add(i);
                }
            }
        }
        return indices;
    }

}
