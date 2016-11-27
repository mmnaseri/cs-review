package com.mmnaseri.cs.ctci.ch08.p11;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/26/16, 10:25 PM)
 */
public class RecursiveDenominatorCounter implements DenominatorCounter {

    @Override
    public int makeChange(int amount) {
        int[] denominators = {25, 10, 5, 1}; //putting this in decreasing order makes it more efficient
        final Map<ChangeAmount, Integer> cache = new HashMap<>();
        return makeChange(denominators, amount, 0, cache); //try breaking it to change by taking "25"s (the 0th denominator).
    }

    private int makeChange(int[] denominators, int amount, int index, Map<ChangeAmount, Integer> cache) {
        if (index >= denominators.length - 1) {
            return 1; //this is the end of the recursion
        }
        final ChangeAmount changeAmount = new ChangeAmount(amount, index);
        if (cache.containsKey(changeAmount)) {
            return cache.get(changeAmount);
        }
        int count = 0;
        int ways = 0;
        while (count * denominators[index] <= amount) {
            ways += makeChange(denominators, amount - count * denominators[index], index + 1, cache); //see how many ways we can break the rest of the money while considering only the remaining denominators
            count ++; //add one more coin to pay for more of the money using this denominator
        }
        cache.put(changeAmount, ways);
        return ways;
    }

    private static class ChangeAmount {

        private final int amount;
        private final int index;

        private ChangeAmount(int amount, int index) {
            this.amount = amount;
            this.index = index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ChangeAmount that = (ChangeAmount) o;
            return amount == that.amount && index == that.index;
        }

        @Override
        public int hashCode() {
            int result = amount;
            result = 31 * result + index;
            return result;
        }
    }

}
