package com.mmnaseri.cs.clrs.ch32.s2;

import java.math.BigInteger;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/4/16, 7:13 PM)
 */
public class RabinFingerprintRollingHash implements RollingHasher {

  private final BigInteger prime;

  public RabinFingerprintRollingHash(int prime) {
    this.prime = BigInteger.valueOf(prime);
  }

  @Override
  public BigInteger hash(String text) {
    BigInteger code = new BigInteger("0");
    for (int i = 0; i < text.length(); i++) {
      final BigInteger term =
          prime.pow(text.length() - i - 1).multiply(BigInteger.valueOf(text.charAt(i)));
      code = code.add(term);
    }
    return code;
  }

  @Override
  public BigInteger roll(String text, BigInteger hash, char next) {
    final BigInteger high =
        prime.pow(text.length() - 1).multiply(BigInteger.valueOf(text.charAt(0)));
    return hash.subtract(high).multiply(prime).add(BigInteger.valueOf(next));
  }
}
