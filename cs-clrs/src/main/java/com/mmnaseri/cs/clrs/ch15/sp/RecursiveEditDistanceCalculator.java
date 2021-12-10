package com.mmnaseri.cs.clrs.ch15.sp;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @author Ramin Farhanian (rf.tech@icloud.com)
 * @since 1.0 (7/22/15)
 */
@Quality(
    value = Stage.TESTED,
    explanation = "bad time complexity, but better than the brute force version")
public class RecursiveEditDistanceCalculator implements EditDistanceCalculator {

  @Override
  public List<EditOperation> calculate(String source, String target, CostFunction costFunction) {
    return calculate(source, target, source.length() - 1, target.length() - 1, costFunction);
  }

  private List<EditOperation> calculate(
      String source, String target, int sourceIndex, int targetIndex, CostFunction function) {
    final List<EditOperation> operations = new ArrayList<>();
    if (sourceIndex < 0) {
      for (int i = 0; i <= targetIndex; i++) {
        operations.add(
            new EditOperation(
                EditOperationType.INSERT,
                String.valueOf(target.charAt(i)),
                function.getCost(EditOperationType.INSERT, source, target, 0, i)));
      }
      return operations;
    }
    if (targetIndex < 0) {
      for (int i = 0; i <= sourceIndex; i++) {
        operations.add(
            new EditOperation(
                EditOperationType.DELETE,
                String.valueOf(source.charAt(i)),
                function.getCost(EditOperationType.DELETE, source, target, i, 0)));
      }
      return operations;
    }
    int best = Integer.MAX_VALUE;
    if (sourceIndex >= 1
        && targetIndex >= 1
        && source.charAt(sourceIndex) == target.charAt(targetIndex - 1)
        && source.charAt(sourceIndex - 1) == target.charAt(targetIndex)) {
      final List<EditOperation> prefix =
          calculate(source, target, sourceIndex - 2, targetIndex - 2, function);
      int localCost = EditOperation.getTotalCost(prefix);
      int functionCost =
          function.getCost(
              EditOperationType.TWIDDLE, source, target, sourceIndex - 2, targetIndex - 2);
      best = localCost + functionCost;
      operations.addAll(prefix);
      operations.add(
          new EditOperation(
              EditOperationType.TWIDDLE,
              source.substring(sourceIndex - 1, sourceIndex + 1),
              functionCost));
    }
    if (source.charAt(sourceIndex) == target.charAt(targetIndex)) {
      final List<EditOperation> prefix =
          calculate(source, target, sourceIndex - 1, targetIndex - 1, function);
      int localCost = EditOperation.getTotalCost(prefix);
      int functionCost =
          function.getCost(
              EditOperationType.COPY, source, target, sourceIndex - 1, targetIndex - 1);
      if (localCost + functionCost < best) {
        best = localCost + functionCost;
        operations.clear();
        operations.addAll(prefix);
        operations.add(
            new EditOperation(
                EditOperationType.COPY, String.valueOf(source.charAt(sourceIndex)), functionCost));
      }
    }
    for (EditOperationType type :
        Arrays.asList(
            EditOperationType.DELETE, EditOperationType.INSERT, EditOperationType.REPLACE)) {
      if (sourceIndex < type.getSource() - 1 || targetIndex < type.getTarget() - 1) {
        continue;
      }
      final List<EditOperation> prefix =
          calculate(
              source,
              target,
              sourceIndex - type.getSource(),
              targetIndex - type.getTarget(),
              function);
      int localCost = EditOperation.getTotalCost(prefix);
      int functionCost =
          function.getCost(
              type, source, target, sourceIndex - type.getSource(), targetIndex - type.getTarget());
      if (localCost + functionCost < best) {
        best = localCost + functionCost;
        operations.clear();
        operations.addAll(prefix);
        operations.add(
            new EditOperation(
                type,
                source.substring(sourceIndex - type.getSource() + 1, sourceIndex + 1)
                    + " for "
                    + target.substring(targetIndex - type.getTarget() + 1, targetIndex + 1),
                functionCost));
      }
    }
    return operations;
  }
}
