package com.mmnaseri.cs.clrs.ch13.s3;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/27/15, 1:34 AM)
 */
public class MonitoredRedBlackTreeTest extends RedBlackTreeTest {

    protected RedBlackTree<Integer> tree() {
        return new MonitoredRedBlackTree<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
    }

    @Test
    public void testBulkInsertion() throws Exception {
        final RedBlackTree<Integer> tree = tree();
        tree.insert(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        tree.insert(-14, -13, -12, -11, -10, -9, -8, -7, -8, -6, -5, -4, -3, -2, -1);
    }

    @Test
    public void testBulkDeletion() throws Exception {
        final RedBlackTree<Integer> tree = tree();
        tree.insert(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        tree.delete(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        tree.insert(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        tree.delete(14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    }

    @DataProvider
    public Object[][] operationProvider() {
        final List<Object[]> result = new ArrayList<>();
        final Random random = new Random();
        for (int i = 0; i < 100; i++) {
            final int operationCount = 5 + random.nextInt(15);
            final List<Operation> operations = new ArrayList<>();
            final int bound = 20 + random.nextInt(81);
            for (int j = 0; j < operationCount; j++) {
                final Action action = Action.values()[random.nextInt(Action.values().length)];
                final int numberCount = 10 + random.nextInt(90);
                final List<Integer> numbers = new ArrayList<>();
                for (int k = 0; k < numberCount; k++) {
                    numbers.add(random.nextInt(2 * bound) - bound);
                }
                operations.add(new Operation(action, numbers));
            }
            result.add(new Object[]{operations});
        }
        return result.toArray(new Object[result.size()][]);
    }

    //todo: the red-black tree currently shows signs of improper implementation as right now it fails ~90% of the time
    @Test(dataProvider = "operationProvider", enabled = false)
    public void stressTest(List<Operation> operations) {
        final RedBlackTree<Integer> tree = tree();
        for (Operation operation : operations) {
            operation.perform(tree);
        }
    }

    private enum Action {
        INSERT, DELETE
    }

    private static class Operation {

        private final Action action;
        private final List<Integer> numbers;

        private Operation(Action action, List<Integer> numbers) {
            this.action = action;
            this.numbers = numbers;
        }

        private void perform(RedBlackTree<Integer> tree) {
            if (action.equals(Action.INSERT)) {
                for (Integer number : numbers) {
                    tree.insert(number);
                }
            } else {
                for (Integer number : numbers) {
                    tree.delete(number);
                }
            }
        }

    }

}