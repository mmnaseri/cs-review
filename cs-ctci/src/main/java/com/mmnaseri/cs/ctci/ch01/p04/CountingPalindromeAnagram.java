package com.mmnaseri.cs.ctci.ch01.p04;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 9:55 AM)
 */
public class CountingPalindromeAnagram implements PalindromeAnagram {

    @Override
    public boolean isPalindromeAnagram(String text) {
        text = text.replaceAll(" +", "").toLowerCase();
        int odds = 0;
        final Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            final Character c = text.charAt(i);
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
            if (map.get(c) % 2 == 1) {
                odds ++;
            } else {
                odds --;
            }
        }
        return odds <= 1;
    }

}
