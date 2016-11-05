package com.mmnaseri.cs.clrs.ch32.s2;

import java.math.BigInteger;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/4/16, 7:10 PM)
 */
public class NaiveRollingHasher implements RollingHasher {

    @Override
    public BigInteger hash(String text) {
        BigInteger code = BigInteger.valueOf(0);
        for (int i = 0; i < text.length(); i++) {
            code = code.add(BigInteger.valueOf(text.charAt(i)));
        }
        return code;
    }

    @Override
    public BigInteger roll(String text, BigInteger hash, char next) {
        return hash.subtract(BigInteger.valueOf(text.charAt(0))).add(BigInteger.valueOf(next));
    }

}
