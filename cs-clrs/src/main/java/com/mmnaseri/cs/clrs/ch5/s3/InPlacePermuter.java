package com.mmnaseri.cs.clrs.ch5.s3;

import com.mmnaseri.cs.clrs.common.Permuter;
import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

import java.util.Random;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/27/15, 12:25 AM)
 */
@Quality(Stage.UNTESTED)
public class InPlacePermuter<E> implements Permuter<E> {

    private void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public void permute(E[] array) {
        final Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            swap(array, i, i + random.nextInt(array.length - i));
        }
    }

}
