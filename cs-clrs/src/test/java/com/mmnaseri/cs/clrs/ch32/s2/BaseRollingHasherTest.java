package com.mmnaseri.cs.clrs.ch32.s2;

import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/4/16, 7:19 PM)
 */
public abstract class BaseRollingHasherTest {

    protected abstract RollingHasher getHasher();

    @Test
    public void testRolling() throws Exception {
        String input = "abcdefghijklmnopqrstuvwxyz";
        String text = "";
        final Random random = new Random();
        for (int i = 0; i < 5; i++) {
            text += input.charAt(random.nextInt(input.length()));
        }
        final RollingHasher hasher = getHasher();
        BigInteger hash = hasher.hash(text);
        for (int i = 0; i < 1000; i++) {
            char next = input.charAt(random.nextInt(input.length()));
            final String newText = text.substring(1) + next;
            final BigInteger direct = hasher.hash(newText);
            final BigInteger rolled = hasher.roll(text, hash, next);
            assertThat(rolled, is(direct));
            hash = rolled;
            text = newText;
        }
    }

}
