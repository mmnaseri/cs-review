package com.mmnaseri.cs.clrs.ch15.s1;

import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/20/15, 5:06 AM)
 */
@Quality(Stage.UNTESTED)
public class SimpleRodCutter implements RodCutter {

    private final PriceFunction function;

    public SimpleRodCutter(PriceFunction function) {
        this.function = function;
    }

    @Override
    public Integer cut(Integer rodSize) {
        if (rodSize == 0) {
            return 0;
        }
        Integer revenue = Integer.MIN_VALUE;
        for (int i = 0; i < rodSize; i ++) {
            revenue = Math.max(revenue, function.getPrice(i) + cut(rodSize - i));
        }
        return revenue;
    }

}
