package com.mmnaseri.cs.clrs.ch05.s3;

import com.mmnaseri.cs.clrs.common.Permuter;
import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/27/15, 12:14 AM)
 */
@Quality(Stage.UNTESTED)
public class SortingPermuter<E> implements Permuter<E> {

    private static class PrioritizedElement<E> implements Comparable<PrioritizedElement<E>> {

        private final E value;
        private final int priority;

        private PrioritizedElement(E value, int priority) {
            this.value = value;
            this.priority = priority;
        }

        @SuppressWarnings("NullableProblems")
        @Override
        public int compareTo(PrioritizedElement<E> target) {
            return Integer.compare(priority, target.priority);
        }

        public E getValue() {
            return value;
        }
        
    }

    @Override
    public void permute(E[] array) {
        final int bound = array.length * array.length * array.length; //n^3
        final Random random = new Random();
        final List<PrioritizedElement<E>> elements = new ArrayList<>(array.length);
        for (E e : array) {
            elements.add(new PrioritizedElement<>(e, random.nextInt(bound)));
        }
        Collections.sort(elements);
        for (int i = 0; i < elements.size(); i++) {
            final PrioritizedElement<E> element = elements.get(i);
            array[i] = element.getValue();
        }
    }

}
