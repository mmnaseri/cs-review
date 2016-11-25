package com.mmnaseri.cs.ctci.ch01.p02;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 9:28 AM)
 */
public class HashMapCheckPermutation implements CheckPermutation {
    @Override
    public boolean isPermutation(String first, String second) {
        final Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < first.length(); i++) {
            final Character c = first.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        for (int i = 0; i < second.length(); i++) {
            final Character c = second.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) {
                map.remove(c);
            }
        }
        return map.isEmpty();
    }
}
