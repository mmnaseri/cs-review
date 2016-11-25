package com.mmnaseri.cs.ctci.ch01.p06;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 11:23 AM)
 */
public class IterativeStringCompressor implements StringCompressor {

    @Override
    public String compress(String original) {
        final StringBuilder builder = new StringBuilder();
        builder.append(original.charAt(0));
        int count = 1;
        for (int i = 1; i < original.length(); i++) {
            if (original.charAt(i - 1) != original.charAt(i)) {
                builder.append(count).append(original.charAt(i));
                count = 0;
            }
            count ++;
        }
        builder.append(count);
        return builder.length() < original.length() ? builder.toString() : original;
    }

}
