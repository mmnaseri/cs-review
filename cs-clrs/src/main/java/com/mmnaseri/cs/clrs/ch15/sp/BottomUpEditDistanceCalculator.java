package com.mmnaseri.cs.clrs.ch15.sp;

import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/22/15)
 */
@Quality(value = Stage.UNTESTED, explanation = "the whole spec mess is because we want the actual operations and not just the distances")
public class BottomUpEditDistanceCalculator implements EditDistanceCalculator {

    private final CostFunction function;

    public BottomUpEditDistanceCalculator(CostFunction function) {
        this.function = function;
    }

    @Override
    public List<EditOperation> calculate(String source, String target) {
        final Specification[][] specifications = prepareDistanceMatrix(source, target);
        calculateDistance(source, target, specifications);
        return constructEditOperations(source, target, specifications);
    }

    private void calculateDistance(String source, String target, Specification[][] specifications) {
        for (int j = 1; j < target.length() + 1; j++) {
            for (int i = 1; i < source.length() + 1; i++) {
                Specification minimum = new Specification(null, Integer.MAX_VALUE, Integer.MAX_VALUE);
                if (source.charAt(i - 1) == target.charAt(j - 1)) {
                    final int functionCost = function.getCost(EditOperationType.COPY, source, target, i - 1, j - 1);
                    final int previousCost = specifications[i - 1][j - 1].getCost();
                    minimum = new Specification(EditOperationType.COPY, previousCost + functionCost, functionCost);
                }
                if (i > 2 && j > 2 && source.charAt(i - 1) == target.charAt(j - 2) && source.charAt(i - 2) == target.charAt(j - 1)) {
                    final int functionCost = function.getCost(EditOperationType.TWIDDLE, source, target, i - 2, j - 2);
                    final int previousCost = specifications[i - 2][j - 2].getCost();
                    if (functionCost + previousCost < minimum.getCost()) {
                        minimum = new Specification(EditOperationType.TWIDDLE, previousCost + functionCost, functionCost);
                    }
                }
                for (EditOperationType type : Arrays.asList(EditOperationType.DELETE, EditOperationType.INSERT, EditOperationType.REPLACE)) {
                    final int functionCost = function.getCost(type, source, target, i - type.getSource(), j - type.getTarget());
                    final int previousCost = specifications[i - type.getSource()][j - type.getTarget()].getCost();
                    if (functionCost + previousCost < minimum.getCost()) {
                        minimum = new Specification(type, previousCost + functionCost, functionCost);
                    }
                }
                specifications[i][j] = minimum;
            }
        }
    }

    private List<EditOperation> constructEditOperations(String source, String target, Specification[][] specifications) {
        final List<EditOperation> operations = new ArrayList<>();
        int i = source.length();
        int j = target.length();
        while (i > 0 && j > 0) {
            final EditOperationType type = specifications[i][j].getType();
            String original = source.substring(i - type.getSource(), i);
            String replacement = target.substring(j - type.getTarget(), j);
            if (original.isEmpty()) {
                original = replacement;
            }
            String text = original;
            if (!original.equals(replacement)) {
                text += " for " + replacement;
            }
            operations.add(0, new EditOperation(type, text, specifications[i][j].getFunctionCost()));
            i -= type.getSource();
            j -= type.getTarget();
        }
        return operations;
    }

    private Specification[][] prepareDistanceMatrix(String source, String target) {
        final Specification[][] distances = new Specification[source.length() + 1][];
        for (int i = 0; i < source.length() + 1; i++) {
            distances[i] = new Specification[target.length() + 1];
            for (int j = 0; j < target.length() + 1; j++) {
                distances[i][j] = new Specification(null, 0, 0);
            }
        }
        int cost = 0;
        for (int i = 0; i < source.length(); i++) {
            final int functionCost = function.getCost(EditOperationType.DELETE, source, target, i, 0);
            cost += functionCost;
            distances[i + 1][0] = new Specification(EditOperationType.DELETE, cost, functionCost);
        }
        cost = 0;
        for (int i = 0; i < target.length(); i++) {
            final int functionCost = function.getCost(EditOperationType.INSERT, source, target, 0, i);
            cost += functionCost;
            distances[0][i + 1] = new Specification(EditOperationType.INSERT, cost, functionCost);
        }
        return distances;
    }

    private static class Specification {

        private final int cost;
        private final int functionCost;
        private final EditOperationType type;

        private Specification(EditOperationType type, int cost, int functionCost) {
            this.cost = cost;
            this.type = type;
            this.functionCost = functionCost;
        }

        public int getCost() {
            return cost;
        }

        public EditOperationType getType() {
            return type;
        }

        public int getFunctionCost() {
            return functionCost;
        }
    }

}
