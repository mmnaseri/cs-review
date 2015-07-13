package com.mmnaseri.cs.ds.clrs.ch10.s1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.fail;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/12/15, 9:32 PM)
 */
public class FixedSizeStackTest {

    @Test
    public void testStackIsInitiallyEmpty() throws Exception {
        final FixedSizeStack<Object> stack = new FixedSizeStack<>(10);
        assertThat(stack.isEmpty(), is(true));
        assertThat(stack.getSize(), is(0));
    }

    @Test
    public void testStackExpandsProperly() throws Exception {
        final FixedSizeStack<Integer> stack = new FixedSizeStack<>(10);
        stack.push(5);
        assertThat(stack.getSize(), is(1));
        assertThat(stack.isEmpty(), is(false));
        stack.push(4);
        assertThat(stack.getSize(), is(2));
        assertThat(stack.isEmpty(), is(false));
        stack.push(3);
        assertThat(stack.getSize(), is(3));
        assertThat(stack.isEmpty(), is(false));
        stack.push(2);
        assertThat(stack.getSize(), is(4));
        assertThat(stack.isEmpty(), is(false));
    }

    @Test
    public void testStackShrinksProperly() throws Exception {
        final FixedSizeStack<Integer> stack = new FixedSizeStack<>(10);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(3);
        stack.pop();
        assertThat(stack.getSize(), is(3));
        assertThat(stack.isEmpty(), is(false));
        stack.pop();
        assertThat(stack.getSize(), is(2));
        assertThat(stack.isEmpty(), is(false));
        stack.pop();
        assertThat(stack.getSize(), is(1));
        assertThat(stack.isEmpty(), is(false));
        stack.pop();
        assertThat(stack.getSize(), is(0));
        assertThat(stack.isEmpty(), is(true));
    }

    @Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = "Stack is empty")
    public void testStackUnderflowIsNotInitiallyAllowed() throws Exception {
        final FixedSizeStack<Integer> stack = new FixedSizeStack<>(10);
        stack.pop();
    }

    @Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = "Stack is empty")
    public void testStackUnderflowIsNotAllowedAfterBeingEmptied() throws Exception {
        final FixedSizeStack<Integer> stack = new FixedSizeStack<>(10);
        stack.push(1);
        try {
            stack.pop();
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
        stack.pop();
    }

    @Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = "Stack is full")
    public void testStackCannotBeOverflown() throws Exception {
        final int capacity = 5;
        final FixedSizeStack<Integer> stack = new FixedSizeStack<>(capacity);
        try {
            for (int i = 0; i < capacity; i ++) {
                stack.push(i);
            }
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
        stack.push(capacity);
    }

    @Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = "Stack is full")
    public void testStackCannotBeOverflownAfterBeingEmptied() throws Exception {
        final int capacity = 5;
        final FixedSizeStack<Integer> stack = new FixedSizeStack<>(capacity);
        try {
            for (int i = 0; i < capacity; i ++) {
                stack.push(i);
            }
            for (int i = 0; i < capacity; i ++) {
                stack.pop();
            }
            for (int i = 0; i < capacity; i ++) {
                stack.push(i);
            }
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
        stack.push(capacity);
    }

    @DataProvider
    public Object[][] stackIntegrityDataProvider() {
        return new Object[][]{
            new Object[]{new Integer[]{5, 3, 2, 8, 10}},
            new Object[]{new Integer[]{-1, 6, 7, 3, 2, 1, 1, 1}},
            new Object[]{new Integer[]{10000, 20, 6666, 2}},
            new Object[]{new Integer[]{1, 1, 1, 1, 1, 1, 1, 1}},
            new Object[]{new Integer[]{null, null, null, null}},
        };
    }

    @Test(dataProvider = "stackIntegrityDataProvider")
    public void testDataIsRetrievedLastInFirstOut(Integer[] items) throws Exception {
        final FixedSizeStack<Integer> stack = new FixedSizeStack<>(items.length);
        for (Integer item : items) {
            stack.push(item);
        }
        assertThat(stack.getSize(), is(items.length));
        for (int i = items.length - 1; i >= 0; i --) {
            assertThat(stack.pop(), is(items[i]));
        }
        assertThat(stack.isEmpty(), is(true));
    }

}