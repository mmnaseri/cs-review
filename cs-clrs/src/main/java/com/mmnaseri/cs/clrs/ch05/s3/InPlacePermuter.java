package com.mmnaseri.cs.clrs.ch05.s3;

import com.mmnaseri.cs.clrs.common.ArrayUtils;
import com.mmnaseri.cs.clrs.common.Permuter;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Random;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (5/27/15, 12:25 AM)
 */
@Quality(Stage.UNTESTED)
public class InPlacePermuter<E> implements Permuter<E> {

    @Override
    public void permute(E[] array) {
        final Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            ArrayUtils.swap(array, i, i + random.nextInt(array.length - i));
        }
    }

}
