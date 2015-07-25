package com.mmnaseri.cs.clrs.ch12.sp;

import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/25/15, 12:17 AM)
 */
public abstract class BitUtils {

    @Quality(Stage.UNTESTED)
    public static int fromBits(Bit[] bits) {
        int result = 0;
        for (Bit bit : bits) {
            result = result << 1;
            result += (bit.equals(Bit.ONE) ? 1 : 0);
        }
        return result;
    }

    @Quality(Stage.UNTESTED)
    public static Bit[] toBits(int number) {
        final int digits = Math.max((int) Math.ceil(Math.log(number) / Math.log(2)), 1);
        final Bit[] bits = new Bit[digits];
        for (int i = 0; i < digits; i ++) {
            bits[bits.length - i - 1] = number % 2 == 0 ? Bit.ZERO : Bit.ONE;
            number = number >> 1;
        }
        return bits;
    }

    public static String toString(Bit[] bits) {
        final StringBuilder builder = new StringBuilder();
        for (Bit bit : bits) {
            switch (bit) {
                case ZERO:
                    builder.append("0");
                    break;
                case ONE:
                    builder.append("1");
                    break;
                case NEUTRAL:
                    builder.append("z");
                    break;
            }
        }
        return builder.toString();
    }
}
