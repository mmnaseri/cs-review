package com.mmnaseri.cs.clrs.ch15.sp;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @author Ramin Farhanian (rf.tech@icloud.com)
 * @since 1.0 (7/21/15, 9:46 PM)
 */
public interface EditDistanceCalculator {

  List<EditOperation> calculate(String source, String target, CostFunction costFunction);
}
