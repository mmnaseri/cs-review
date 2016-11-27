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
        final Map<ChangeAmount, Integer> cache = new HashMap<>();
        return makeChange(25, amount, cache); //try breaking it to change by taking "25"s (the 0th denominator).
    }

    private int makeChange(int coin, int amount, Map<ChangeAmount, Integer> cache) {
        if (coin == 1) {
            return 1; //this is the end of the recursion
        }
        final ChangeAmount changeAmount = new ChangeAmount(amount, coin);
        if (cache.containsKey(changeAmount)) {
            return cache.get(changeAmount);
        }
        int count = 0;
        int ways = 0;
        while (count * coin <= amount) {
            ways += makeChange(next(coin), amount - count * coin, cache); //see how many ways we can break the rest of the money while considering the next set of coins
            count ++; //add one more coin to pay for more of the money using this denominator
        }
        cache.put(changeAmount, ways);
        return ways;
    }

    private int next(int coin) {
        switch (coin) {
            case 25:
                return 10;
            case 10:
                return 5;
            default:
                return 1;
        }
    }

    private static class ChangeAmount {

        private final int amount;
        private final int denominator;

        private ChangeAmount(int amount, int denominator) {
            this.amount = amount;
            this.denominator = denominator;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ChangeAmount that = (ChangeAmount) o;
            return amount == that.amount && denominator == that.denominator;
        }

        @Override
        public int hashCode() {
            int result = amount;
            result = 31 * result + denominator;
            return result;
        }
    }

}
