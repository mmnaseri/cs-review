package com.mmnaseri.cs.clrs.ch32.s2;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/4/16, 7:19 PM)
 */
public class RabinFingerprintRollingHashTest extends BaseRollingHasherTest {

    @Override
    protected RollingHasher getHasher() {
        return new RabinFingerprintRollingHash(701);
    }

}