package com.mmnaseri.cs.clrs.common.impl;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixCell;
import com.mmnaseri.cs.clrs.common.MatrixRow;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (2016/03/07, 10:34)
 */
public class DelegatingMatrixTest {

    /**
     *  0,  1,  2,  3
     *  4,  5,  6,  7
     *  8,  9, 10, 11
     * 12, 13, 14, 15
     */
    private Matrix<Integer> original;

    @BeforeMethod
    public void setUp() throws Exception {
        original = new ArrayMatrix<>(4, 4);
        original.set(0, 0, 0);
        original.set(0, 1, 1);
        original.set(0, 2, 2);
        original.set(0, 3, 3);
        original.set(1, 0, 4);
        original.set(1, 1, 5);
        original.set(1, 2, 6);
        original.set(1, 3, 7);
        original.set(2, 0, 8);
        original.set(2, 1, 9);
        original.set(2, 2, 10);
        original.set(2, 3, 11);
        original.set(3, 0, 12);
        original.set(3, 1, 13);
        original.set(3, 2, 14);
        original.set(3, 3, 15);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Row offset -1 is .*? \\[0,3\\]")
    public void testRowOffsetLessThanZero() throws Exception {
        new DelegatingMatrix<>(original, -1, 0, 1, 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Column offset -1 is .*? \\[0,3\\]")
    public void testColumnOffsetLessThanZero() throws Exception {
        new DelegatingMatrix<>(original, 0, -1, 1, 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Delegate .*?row.*? \\[3-4\\] .*? provide \\[3-8\\]")
    public void testRowCountOutOfBounds() throws Exception {
        new DelegatingMatrix<>(original, 3, 0, 5, 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Delegate .*?column.*? \\[3-4\\] .*? provide \\[3-5\\]")
    public void testColumnCountOutOfBounds() throws Exception {
        new DelegatingMatrix<>(original, 3, 3, 1, 2);
    }

    @Test
    public void testMirroringDelegate() throws Exception {
        final Matrix<Integer> mirror = new DelegatingMatrix<>(original, 0, 0, original.getRows(), original.getColumns());
        assertThat(mirror.getColumns(), is(original.getColumns()));
        assertThat(mirror.getRows(), is(original.getRows()));
        for (MatrixRow<Integer> row : original) {
            for (MatrixCell<Integer> cell : row) {
                assertThat(mirror.get(cell.getRowNumber(), cell.getColumnNumber()), is(cell.getValue()));
            }
        }
        for (MatrixRow<Integer> row : mirror) {
            for (MatrixCell<Integer> cell : row) {
                assertThat(original.get(cell.getRowNumber(), cell.getColumnNumber()), is(cell.getValue()));
            }
        }
    }

    @Test
    public void testPartialViewAccess() throws Exception {
        /**
         *  6,  7
         * 10, 11
         */
        final int rowOffset = 1;
        final int columnOffset = 2;
        final int rowCount = 2;
        final int columnCount = 2;
        final Matrix<Integer> partial = new DelegatingMatrix<>(original, rowOffset, columnOffset, rowCount, columnCount);
        assertThat(partial.getRows(), is(rowCount));
        assertThat(partial.getColumns(), is(columnCount));
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                assertThat(partial.get(i, j), is(original.get(i + rowOffset, j + columnOffset)));
            }
        }
    }

    @Test
    public void testPartialViewIterator() throws Exception {
        /**
         *  6,  7
         * 10, 11
         */
        final int rowOffset = 1;
        final int columnOffset = 2;
        final int rowCount = 2;
        final int columnCount = 2;
        final Matrix<Integer> partial = new DelegatingMatrix<>(original, rowOffset, columnOffset, rowCount, columnCount);
        assertThat(partial.getRows(), is(rowCount));
        assertThat(partial.getColumns(), is(columnCount));
        int rowNumber = 0;
        for (MatrixRow<Integer> row : partial) {
            int columnNumber = 0;
            assertThat(row.getRowNumber(), is(rowNumber));
            for (MatrixCell<Integer> cell : row) {
                assertThat(cell.getRowNumber(), is(rowNumber));
                assertThat(cell.getColumnNumber(), is(columnNumber));
                assertThat(cell.getValue(), is(original.get(cell.getRowNumber() + rowOffset, cell.getColumnNumber() + columnOffset)));
                columnNumber ++;
            }
            rowNumber ++;
        }
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp = "Column.*?bounds\\(0,1\\): -1")
    public void testPartialAccessColumnBelowBounds() throws Exception {
        /**
         *  6,  7
         * 10, 11
         */
        new DelegatingMatrix<>(original, 1, 2, 2, 2).get(0, -1);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp = "Row.*?bounds\\(0,1\\): -1")
    public void testPartialAccessRowBelowBounds() throws Exception {
        /**
         *  6,  7
         * 10, 11
         */
        new DelegatingMatrix<>(original, 1, 2, 2, 2).get(-1, 0);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp = "Column.*?bounds\\(0,1\\): 2")
    public void testPartialAccessColumnAboveBounds() throws Exception {
        /**
         *  6,  7
         * 10, 11
         */
        new DelegatingMatrix<>(original, 1, 2, 2, 2).get(0, 2);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp = "Row.*?bounds\\(0,1\\): 2")
    public void testPartialAccessRowAboveBounds() throws Exception {
        /**
         *  6,  7
         * 10, 11
         */
        new DelegatingMatrix<>(original, 1, 2, 2, 2).get(2, 0);
    }

}