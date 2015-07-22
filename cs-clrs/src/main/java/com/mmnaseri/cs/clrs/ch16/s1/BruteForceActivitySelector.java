package com.mmnaseri.cs.clrs.ch16.s1;

import com.mmnaseri.cs.clrs.ch15.s4.SequenceUtils;
import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

import java.util.*;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/22/15)
 */
@Quality(value = Stage.TESTED, explanation = "poorest design and running time possible (O(2^n))")
public class BruteForceActivitySelector implements ActivitySelector {

    @Override
    public Set<Integer> select(Activity... activities) {
        final List<List<IndexedActivity>> sequences = SequenceUtils.subSequences(Arrays.asList(IndexedActivity.index(activities)));
        Collections.sort(sequences, new Comparator<List<IndexedActivity>>() {
            @Override
            public int compare(List<IndexedActivity> first, List<IndexedActivity> second) {
                final int comparison = Integer.compare(second.size(), first.size());
                if (comparison == 0) {
                    return Integer.compare(sum(second), sum(first));
                }
                return comparison;
            }
        });
        final Set<Integer> indices = new HashSet<>();
        for (List<IndexedActivity> sequence : sequences) {
            if (isCompatible(sequence)) {
                for (IndexedActivity indexedActivity : sequence) {
                    indices.add(indexedActivity.getIndex());
                }
                return indices;
            }
        }
        return indices;
    }

    private static boolean isCompatible(List<? extends Activity> activities) {
        for (int i = 0; i < activities.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (!activities.get(i).isCompatible(activities.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int sum(List<? extends Activity> activities) {
        int sum = 0;
        for (Activity activity : activities) {
            sum += activity.getFinish() - activity.getStart();
        }
        return sum;
    }

}
