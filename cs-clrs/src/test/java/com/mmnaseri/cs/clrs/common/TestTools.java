package com.mmnaseri.cs.clrs.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/1/15, 11:34 AM)
 */
public abstract class TestTools {

    public static List<String> sampleProducer(int length) {
        final List<String> input = new ArrayList<>();
        final List<String> seed = Arrays.asList("1", "3", "2");
        for (String prefix : seed) {
            input.add(prefix);
        }
        for (int i = 0; i < length; i ++) {
            final List<List<String>> addendum = new ArrayList<>();
            for (String word : input) {
                final List<String> variations = new ArrayList<>();
                for (String suffix : seed) {
                    variations.add(word + suffix);
                }
                addendum.add(variations);
            }
            for (List<String> derivatives : addendum) {
                for (String derivative : derivatives) {
                    if (!input.contains(derivative)) {
                        input.add(derivative);
                    }
                }
            }
        }
        return input;
    }

}
