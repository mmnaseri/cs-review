package com.mmnaseri.cs.clrs.ch28.s1;

import com.mmnaseri.cs.clrs.ch05.s3.InPlacePermuter;
import com.mmnaseri.cs.clrs.common.MatrixCell;
import com.mmnaseri.cs.clrs.common.MatrixRow;
import com.mmnaseri.cs.clrs.common.Permuter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/23/16, 9:11 PM)
 */
public class PermutationMatrixTest {

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*null.*")
    public void testThatItIsUnhappyAboutNullPermutations() throws Exception {
        new PermutationMatrix(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*empty.*")
    public void testThatItIsUnhappyAboutEmptyPermutations() throws Exception {
        new PermutationMatrix(new ArrayList<Integer>());
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*duplicate.*")
    public void testThatItIsUnhappyAboutPermutationsWithDuplicateValues() throws Exception {
        new PermutationMatrix(Arrays.asList(1, 3, 3, 4));
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*range.*")
    public void testThatItIsUnhappyAboutPermutationsWithValuesOutOfRange() throws Exception {
        new PermutationMatrix(Arrays.asList(1, 5, 3));
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void testThatTheMatrixIsImmutable() throws Exception {
        PermutationMatrix matrix = new PermutationMatrix(Arrays.asList(0, 1, 2));
        matrix.set(0, 0, 1.0);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testThatDoesNotWorkForValuesOutOfBounds() throws Exception {
        PermutationMatrix matrix = new PermutationMatrix(Arrays.asList(0, 1, 2));
        matrix.get(5, 0);
    }

    @Test(dataProvider = "permutations")
    public void testPermutationValues(Integer[] permutation) throws Exception {
        final List<Integer> list = Arrays.asList(permutation);
        PermutationMatrix matrix = new PermutationMatrix(list);
        for (MatrixRow<Double> row : matrix) {
            for (MatrixCell<Double> cell : row) {
                assertThat(cell.getValue(), is(permutation[cell.getRowNumber()] == cell.getColumnNumber() ? 1.0 : 0));
            }
        }
    }

    @DataProvider
    public Object[][] permutations() {
        final List<List<Object>> lists = new ArrayList<>();
        final List<Integer> sample = new ArrayList<>();
        final Permuter<Integer> permuter = new InPlacePermuter<>();
        for (int i = 1; i <= 100; i++) {
            final List<Object> list = new ArrayList<>();
            sample.add(i - 1);
            Integer[] permutation = sample.toArray(new Integer[sample.size()]);
            permuter.permute(permutation);
            list.add(permutation);
            lists.add(list);
        }
        final Object[][] result = new Object[lists.size()][];
        for (int i = 0; i < lists.size(); i++) {
            List<Object> list = lists.get(i);
            result[i] = list.toArray();
        }
        return result;
    }

}