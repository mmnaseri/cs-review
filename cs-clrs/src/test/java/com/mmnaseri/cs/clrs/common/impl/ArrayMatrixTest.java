package com.mmnaseri.cs.clrs.common.impl;

import com.mmnaseri.cs.clrs.common.MatrixCell;
import com.mmnaseri.cs.clrs.common.MatrixRow;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ArrayMatrixTest {

    private int[][] input = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    private AbstractMatrix<Integer> matrix;

    @BeforeMethod
    public void setUp() {
        matrix = new ArrayMatrix<>(input.length, input[0].length);

        for (int row = 0; row < input.length; row++) {
            int[] columns = input[row];
            for (int col = 0; col < columns.length; col++) {
                int value = input[row][col];
                matrix.set(row, col, value);
            }
        }
    }

    @Test
    public void testGetRowsAndColumns() {
        assertThat(matrix.getRows(), is(input.length));
        assertThat(matrix.getColumns(), is(input[0].length));
    }
    @Test
    public void testGet() {
        assertThat(matrix.get(0, 0), is(input[0][0]));
        assertThat(matrix.get(0, 1), is(input[0][1]));
        assertThat(matrix.get(0, 2), is(input[0][2]));
        assertThat(matrix.get(1, 0), is(input[1][0]));
        assertThat(matrix.get(1, 1), is(input[1][1]));
        assertThat(matrix.get(1, 2), is(input[1][2]));
        assertThat(matrix.get(2, 0), is(input[2][0]));
        assertThat(matrix.get(2, 1), is(input[2][1]));
        assertThat(matrix.get(2, 2), is(input[2][2]));
    }

    @Test
    public void testSet() {
        for (int row = 0; row < input.length; row++) {
            int[] columns = input[row];
            for (int col = 0; col < columns.length; col++) {
                matrix.set(row, col, input[row][col] * 2);
            }
        }
        assertThat(matrix.get(0, 0), is(2 * input[0][0]));
        assertThat(matrix.get(0, 1), is(2 * input[0][1]));
        assertThat(matrix.get(0, 2), is(2 * input[0][2]));
        assertThat(matrix.get(1, 0), is(2 * input[1][0]));
        assertThat(matrix.get(1, 1), is(2 * input[1][1]));
        assertThat(matrix.get(1, 2), is(2 * input[1][2]));
        assertThat(matrix.get(2, 0), is(2 * input[2][0]));
        assertThat(matrix.get(2, 1), is(2 * input[2][1]));
        assertThat(matrix.get(2, 2), is(2 * input[2][2]));
    }


    @Test
    public void testIterator() {
        for (MatrixRow<Integer> row : matrix) {
            for (MatrixCell<Integer> cell : row) {
                final int columnNumber = cell.getColumnNumber();
                final int rowNumber = cell.getRowNumber();
                assertThat(matrix.read(rowNumber, columnNumber), is(input[rowNumber][columnNumber]));
            }
        }
    }

}