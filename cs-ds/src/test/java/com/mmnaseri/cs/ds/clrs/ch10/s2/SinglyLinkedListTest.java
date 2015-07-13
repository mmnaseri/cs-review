package com.mmnaseri.cs.ds.clrs.ch10.s2;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/12/15, 11:21 PM)
 */
public class SinglyLinkedListTest extends BaseLinkedListTest {

    @Override
    protected LinkedList<Integer> getLinkedList() {
        return new SinglyLinkedList<>();
    }

}