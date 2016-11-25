package com.mmnaseri.cs.ctci.ch01.p03;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 9:42 AM)
 */
public class OffsettingUrlify implements Urlify {

    @Override
    public void urlify(char[] chars) {
        if (chars == null || chars.length == 0) {
            throw new IllegalArgumentException();
        }
        int offset = 0;
        while (chars[chars.length - 1 - offset] == ' ') {
            offset ++;
        }
        for (int i = chars.length - 1 - offset; offset != 0; i --) {
            if (chars[i] != ' ') {
                chars[i + offset] = chars[i];
            } else {
                offset -= 2;
                chars[i + offset] = '%';
                chars[i + offset + 1] = '2';
                chars[i + offset + 2] = '0';
            }
        }
    }

}
