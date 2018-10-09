package com.mmnaseri.cs.clrs.ch05.s3;

import com.mmnaseri.cs.clrs.common.Sampler;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @author Ramin Farhanian (rf.tech@icloud.com)
 * @since 1.0 (5/27/15, 12:34 AM)
 */
@Quality(Stage.TESTED)
public class RandomSampler<E> implements Sampler<E> {

    private Set<E> sample(E[] array, int size, int consider) {
        if (size == 0) {
            return new HashSet<>();
        }
        final Set<E> sample = sample(array, size - 1, consider - 1);
        E chosen = array[new Random().nextInt(consider)];
        final E last = array[consider - 1];
        if (sample.contains(chosen) && !chosen.equals(last)) {
            sample.add(last);
        } else {
            sample.add(chosen);
        }
        return sample;
    }


    @Override
    public E[] sample(E[] array, int size) {
        if (array == null || array.length < 2) {
            throw new IllegalArgumentException("Minimum array size is 2.");
        }
        if (array.length <= size) {
            throw new IllegalArgumentException("sample size should be smaller than the array length.");
        }
        final Set<E> set = sample(array, size, array.length - 1);
        @SuppressWarnings("unchecked")
        final E[] instance = (E[]) Array.newInstance(array.getClass().getComponentType(), size);
        return set.toArray(instance);
    }

}
