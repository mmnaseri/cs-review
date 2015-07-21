package com.mmnaseri.cs.clrs.ch05.s3;

import com.mmnaseri.cs.clrs.common.Sampler;
import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

import java.util.Collections;
import java.util.Random;
import java.util.Set;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/27/15, 12:34 AM)
 */
@Quality(Stage.UNTESTED)
public class RandomSampler<E> implements Sampler<E> {

    private Set<E> sample(E[] array, int size, int consider) {
        if (size == 0) {
            return Collections.emptySet();
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
        final Set<E> set = sample(array, size, array.length);
        //noinspection unchecked
        return (E[]) set.toArray();
    }

}
