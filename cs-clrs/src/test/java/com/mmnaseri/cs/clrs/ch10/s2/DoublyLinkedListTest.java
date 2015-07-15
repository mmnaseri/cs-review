package com.mmnaseri.cs.clrs.ch10.s2;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/12/15, 11:57 PM)
 */
public class DoublyLinkedListTest extends BaseLinkedListTest {

    @Override
    protected LinkedList<Integer> getLinkedList() {
        return new DoublyLinkedList<>();
    }

}