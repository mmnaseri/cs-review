package com.mmnaseri.cs.clrs.ch32.s2;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/4/16, 7:15 PM)
 */
public class NaiveRollingHasherTest extends BaseRollingHasherTest {

    @Override
    protected RollingHasher getHasher() {
        return new NaiveRollingHasher();
    }

}