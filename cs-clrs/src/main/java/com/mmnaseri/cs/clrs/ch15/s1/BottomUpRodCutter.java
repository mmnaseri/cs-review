package com.mmnaseri.cs.clrs.ch15.s1;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/20/15, 5:11 AM)
 */
@Quality(Stage.UNTESTED)
public class BottomUpRodCutter implements RodCutter {

    private final PriceFunction function;

    public BottomUpRodCutter(PriceFunction function) {
        this.function = function;
    }

    @Override
    public Integer cut(Integer rodSize) {
        final Map<Integer, Integer> memory = new HashMap<>();
        memory.put(0, 0);
        for (int i = 1; i < rodSize; i ++) {
            int revenue = Integer.MIN_VALUE;
            for (int j = 1; j < i; j ++) {
                revenue = Math.max(revenue, function.getPrice(j) + memory.get(i - j));
            }
            memory.put(i, revenue);
        }
        return memory.get(rodSize);
    }

}
