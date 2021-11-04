package com.mmnaseri.cs.clrs.ch15.sp;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @author Ramin Farhanian (rf.tech@icloud.com)
 * @since 1.0 (7/21/15, 9:53 PM)
 */
@Quality(value = Stage.TESTED, explanation = "The dumbest implementation possible")
public class BruteForceEditDistanceCalculator implements EditDistanceCalculator {

  @Override
  public List<EditOperation> calculate(String source, String target, CostFunction function) {
    return calculate(source, target, 0, 0, function);
  }

  private List<EditOperation> calculate(
      String source, String target, int sourceCursor, int targetCursor, CostFunction function) {
    final List<EditOperation> operations = new ArrayList<>();
    if (sourceCursor >= source.length()) {
      for (int i = targetCursor; i < target.length(); i++) {
        operations.add(
            new EditOperation(
                EditOperationType.INSERT,
                String.valueOf(target.charAt(i)),
                function.getCost(
                    EditOperationType.INSERT, null, target, sourceCursor, targetCursor)));
      }
      return operations;
    }
    if (targetCursor >= target.length()) {
      for (int i = sourceCursor; i < source.length(); i++) {
        operations.add(
            new EditOperation(
                EditOperationType.DELETE,
                String.valueOf(source.charAt(i)),
                function.getCost(
                    EditOperationType.DELETE, source, null, sourceCursor, targetCursor)));
      }
      return operations;
    }
    final Set<Specification> candidates = new HashSet<>();
    if (source.charAt(sourceCursor) == target.charAt(targetCursor)) {
      candidates.add(
          new Specification(
              EditOperationType.COPY,
              sourceCursor,
              targetCursor,
              String.valueOf(source.charAt(sourceCursor))));
    } else if (sourceCursor < source.length() - 1
        && targetCursor < target.length() - 1
        && source.charAt(sourceCursor + 1) == target.charAt(targetCursor)
        && source.charAt(sourceCursor) == target.charAt(targetCursor + 1)) {
      candidates.add(
          new Specification(
              EditOperationType.TWIDDLE,
              sourceCursor,
              targetCursor,
              source.substring(sourceCursor, sourceCursor + 2)));
    }
    candidates.add(
        new Specification(
            EditOperationType.DELETE,
            sourceCursor,
            targetCursor,
            source.charAt(sourceCursor) + " for " + target.charAt(targetCursor)));
    candidates.add(
        new Specification(
            EditOperationType.INSERT,
            sourceCursor,
            targetCursor,
            source.charAt(sourceCursor) + " for " + target.charAt(targetCursor)));
    candidates.add(
        new Specification(
            EditOperationType.REPLACE,
            sourceCursor,
            targetCursor,
            source.charAt(sourceCursor) + " for " + target.charAt(targetCursor)));
    int minimum = Integer.MAX_VALUE;
    for (Specification candidate : candidates) {
      final List<EditOperation> rest =
          calculate(source, target, candidate.getSource(), candidate.getTarget(), function);
      final int current =
          function.getCost(candidate.getType(), source, target, sourceCursor, targetCursor);
      final int localCost = current + cost(rest);
      if (localCost < minimum) {
        operations.clear();
        operations.add(new EditOperation(candidate.getType(), candidate.getSubject(), current));
        operations.addAll(rest);
        minimum = localCost;
      }
    }
    return operations;
  }

  private int cost(List<EditOperation> operations) {
    int cost = 0;
    for (EditOperation operation : operations) {
      cost += operation.getCost();
    }
    return cost;
  }

  private static class Specification {

    private final EditOperationType type;
    private final int source;
    private final int target;
    private final String subject;

    private Specification(EditOperationType type, int source, int target, String subject) {
      this.type = type;
      this.subject = subject;
      this.source = source + type.getSource();
      this.target = target + type.getTarget();
    }

    public EditOperationType getType() {
      return type;
    }

    public int getSource() {
      return source;
    }

    public int getTarget() {
      return target;
    }

    public String getSubject() {
      return subject;
    }
  }
}
