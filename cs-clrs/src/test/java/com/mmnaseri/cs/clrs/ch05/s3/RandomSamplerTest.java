package com.mmnaseri.cs.clrs.ch05.s3;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * @author Ramin Farhanian (rf.tech@icloud.com)
 * @since 1.0 (10/01/18, 4:34 AM)
 */
public class RandomSamplerTest {

    private RandomSampler<Integer> sampler;
    private Integer[] input = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    @BeforeMethod
    public void setUp() {
        sampler = new RandomSampler<>();
    }

    @Test
    public void assertThatSamplerSamplesSubsetOfInput() {
        final int size = input.length - 5;
        final Integer[] output = sampler.sample(input, size);
        assertThat(output, is(notNullValue()));
        assertThat(output.length, is(size));
        assertThat(Arrays.asList(output), everyItem(isIn(input)));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void assertThatSampleSizeCannotExceedInputSize() {
        final int size = input.length + 1;
        sampler.sample(input, size);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void assertThatSampleCanOnlySampleSubsetOfTheInput() {
        final int size = input.length;
        sampler.sample(input, size);
    }
}