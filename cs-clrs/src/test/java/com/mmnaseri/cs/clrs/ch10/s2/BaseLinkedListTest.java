package com.mmnaseri.cs.clrs.ch10.s2;

import com.mmnaseri.cs.clrs.ch05.s3.InPlacePermuter;
import com.mmnaseri.cs.clrs.common.Permuter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/12/15, 11:40 PM)
 */
public abstract class BaseLinkedListTest {
    protected abstract LinkedList<Integer> getLinkedList();

    @Test
    public void testInitialListSize() throws Exception {
        final LinkedList<Integer> list = getLinkedList();
        assertThat(list.getSize(), is(0));
        assertThat(list.isEmpty(), is(true));
    }

    @DataProvider
    public Object[][] listDataProvider() {
        return new Object[][]{
            new Object[]{new Integer[]{1, 2, 3, 4, 5, 6}},//normal
            new Object[]{new Integer[]{1, -2, 3, -4, 5, -6}},//negative
            new Object[]{new Integer[]{1, null, 3}},//null
            new Object[]{new Integer[]{1, Integer.MAX_VALUE, 3, Integer.MIN_VALUE, 5}},//infinity
            new Object[]{new Integer[]{1, 1, 1, 1, 1}},//duplicates
        };
    }

    @Test(dataProvider = "listDataProvider")
    public void testListExpansion(Integer[] items) throws Exception {
        final LinkedList<Integer> linkedList = getLinkedList();
        for (int i = 0; i < items.length; i++) {
            Integer item = items[i];
            linkedList.add(item);
            assertThat(linkedList.getSize(), is(i + 1));
            assertThat(linkedList.isEmpty(), is(false));
        }
    }

    @Test(dataProvider = "listDataProvider")
    public void testListExpansionByInsertion(Integer[] items) throws Exception {
        final LinkedList<Integer> linkedList = getLinkedList();
        for (int i = 0; i < items.length; i++) {
            Integer item = items[i];
            linkedList.insert(0, item);
            assertThat(linkedList.getSize(), is(i + 1));
            assertThat(linkedList.isEmpty(), is(false));
        }

    }

    @Test(dataProvider = "listDataProvider")
    public void testListDeletionWithIndex(Integer[] items) throws Exception {
        final LinkedList<Integer> linkedList = getLinkedList();
        for (Integer item : items) {
            linkedList.add(item);
        }
        for (int i = items.length - 1; i >= 0; i --) {
            linkedList.delete(i);
            assertThat(linkedList.getSize(), is(i));
        }
        assertThat(linkedList.isEmpty(), is(true));
    }

    @Test(dataProvider = "listDataProvider")
    public void testDeletionByValue(Integer[] items) throws Exception {
        final LinkedList<Integer> linkedList = getLinkedList();
        for (Integer item : items) {
            linkedList.add(item);
        }
        int size = items.length - 1;
        for (Integer item : items) {
            linkedList.delete(item);
            assertThat(linkedList.getSize(), is(size --));
        }
        assertThat(linkedList.isEmpty(), is(true));
    }

    @Test(dataProvider = "listDataProvider", invocationCount = 20)
    public void testLookUpByIndex(Integer[] items) throws Exception {
        final Integer[] target = new Integer[items.length];
        System.arraycopy(items, 0, target, 0, target.length);
        final LinkedList<Integer> linkedList = getLinkedList();
        final Permuter<Integer> permuter = new InPlacePermuter<>();
        permuter.permute(target);
        for (Integer item : target) {
            linkedList.insert(0, item);
        }
        for (int i = 0; i < target.length; i++) {
            final Integer item = target[i];
            assertThat(linkedList.get(target.length - i - 1), is(item));
        }
    }

}
