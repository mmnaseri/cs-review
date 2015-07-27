package com.mmnaseri.cs.clrs.ch15.s4;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/21/15)
 */
@Quality(Stage.TESTED)
public class BottomUpLongestCommonSubSequenceFinder<E> implements LongestCommonSubSequenceFinder<E> {

    @Override
    public List<E> find(List<E> first, List<E> second) {
        if (first == null || second == null) {
            return null;
        }
        final List<List<Integer>> memory = new ArrayList<>();
        for (int i = 0; i < first.size() + 1; i ++) {
            memory.add(new ArrayList<Integer>());
            if (i == 0) {
                for (int j = 0; j < second.size() + 1; j ++) {
                    memory.get(i).add(0);
                }
            } else {
                memory.get(i).add(0);
                for (int j = 1; j < second.size() + 1; j ++) {
                    memory.get(i).add(null);
                }
            }
        }
        for (int i = 1; i < first.size() + 1; i ++) {
            for (int j = 1; j < second.size() + 1; j ++) {
                final E firstItem = first.get(i - 1);
                final E secondItem = second.get(j - 1);
                if (firstItem == null && secondItem == null || firstItem != null && firstItem.equals(secondItem)) {
                    memory.get(i).set(j, memory.get(i - 1).get(j - 1) + 1);
                } else if (memory.get(i - 1).get(j) >= memory.get(i).get(j - 1)) {
                    memory.get(i).set(j, memory.get(i - 1).get(j));
                } else {
                    memory.get(i).set(j, memory.get(i).get(j - 1));
                }
            }
        }
        final List<E> result = new ArrayList<>();
        int i = first.size();
        int j = second.size();
        while (i > 0 && j > 0) {
            int up = memory.get(i - 1).get(j);
            int right = memory.get(i).get(j - 1);
            int across = memory.get(i - 1).get(j - 1);
            if (memory.get(i).get(j) != 0 && right == up && up == across) {
                i --;
                j --;
                result.add(0, first.get(i));
            } else if (right > up) {
                j --;
            } else {
                i --;
            }
        }
        return result;
    }

}
