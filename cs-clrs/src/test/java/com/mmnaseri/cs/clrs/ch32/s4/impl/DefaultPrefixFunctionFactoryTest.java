package com.mmnaseri.cs.clrs.ch32.s4.impl;

import com.mmnaseri.cs.clrs.ch32.s4.PrefixFunction;
import com.mmnaseri.cs.clrs.ch32.s4.PrefixFunctionFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/6/16, 1:00 PM)
 */
public class DefaultPrefixFunctionFactoryTest {

    /**
     * Brute force implementation that <em>obviously</em> works
     * @param pattern    the pattern
     * @return prefix function Pi(k) such that Pi(x) is the `r + 1` so that pattern[0..r] is a proper suffix of pattern[0..x]
     * or `0` if this condition cannot be met
     */
    private int[] compute(String pattern) {
        final int[] prefixes = new int[pattern.length()];
        prefixes[0] = 0;
        for (int i = 1; i < pattern.length() + 1; i++) {
            String prefix = null;
            for (int j = i - 1; j >= 0; j--) {
                if (pattern.substring(0, i).endsWith(pattern.substring(0, j))) {
                    prefix = pattern.substring(0, j);
                    break;
                }
            }
            if (prefix == null) {
                prefixes[i - 1] = 0;
            } else {
                prefixes[i - 1] = prefix.length();
            }
        }
        return prefixes;
    }

    private PrefixFunctionFactory getFactory() {
        return new DefaultPrefixFunctionFactory();
    }

    @DataProvider
    public Object[][] patternProvider() {
        final List<String> patterns = new ArrayList<>();
        final String alphabet = "abcdefghijklmnopqrstuvwxyz1234567890";
        final Random random = new Random();
        for (int i = 0; i < 500; i++) {
            final int length = 10 + random.nextInt(40);
            String pattern = "";
            while (pattern.length() < length) {
                pattern += alphabet.charAt(random.nextInt(alphabet.length()));
            }
            patterns.add(pattern);
        }
        final Object[][] result = new Object[patterns.size()][];
        for (int i = 0; i < patterns.size(); i++) {
            String pattern = patterns.get(i);
            result[i] = new Object[]{pattern, compute(pattern)};
        }
        return result;
    }

    @Test(dataProvider = "patternProvider")
    public void testName(String pattern, int[] prefixes) throws Exception {
        final PrefixFunction function = getFactory().getInstance(pattern);
        assertThat(function, is(notNullValue()));
        for (int i = 0; i < prefixes.length; i++) {
            int prefix = prefixes[i];
            assertThat(function.prefix(i), is(prefix));
        }
    }

}