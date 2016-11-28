package com.mmnaseri.cs.ctci.ch16.p18;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/27/16, 8:26 PM)
 */
public class DynamicPatternMatcher implements PatternMatcher {

    @Override
    public boolean matches(String pattern, String text) {
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }
        final HashTable table = HashTable.given(pattern);
        return matches(table, pattern, text);
    }

    private boolean matches(HashTable table, String pattern, String text) {
        if (text.isEmpty() || pattern.isEmpty()) {
            return pattern.isEmpty() && text.isEmpty();
        }
        final char character = pattern.charAt(0);
        if (table.has(character)) {
            final String assumption = table.get(character);
            return text.startsWith(assumption) && matches(table, pattern.substring(1), text.substring(assumption.length()));
        }
        String assumption = "";
        while (!text.isEmpty()) {
            assumption += text.charAt(0);
            text = text.substring(1);
            final HashTable copy = HashTable.assumeFor(table, character, assumption);
            if (matches(copy, pattern.substring(1), text)) {
                for (Character key : copy.chars()) {
                    table.assume(key, copy.get(key));
                }
                return true;
            }
        }
        return false;
    }

    private static class HashTable {

        private final Map<Character, String> map = new HashMap<>();

        private HashTable assume(Character character, String text) {
            map.put(character, text);
            return this;
        }

        private boolean has(Character character) {
            return map.containsKey(character) && !map.get(character).isEmpty();
        }

        private String get(Character character) {
            return map.get(character);
        }

        private Set<Character> chars() {
            return map.keySet();
        }

        private static HashTable given(String pattern) {
            final HashTable table = new HashTable();
            for (int i = 0; i < pattern.length(); i++) {
                final Character character = pattern.charAt(i);
                table.map.put(character, "");
            }
            return table;
        }

        private static HashTable assumeFor(HashTable hashTable, Character character, String assumption) {
            final HashTable table = new HashTable();
            table.map.putAll(hashTable.map);
            table.assume(character, assumption);
            return table;
        }

    }

}
