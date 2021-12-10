package com.mmnaseri.cs.clrs.ch32.s2;

import java.math.BigInteger;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/4/16, 7:09 PM)
 */
public interface RollingHasher {

  BigInteger hash(String text);

  BigInteger roll(String text, BigInteger hash, char next);
}
