package com.mmnaseri.cs.clrs.ch10.s2;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/12/15, 11:21 PM)
 */
public class SinglyLinkedListTest extends BaseLinkedListTest {

    @Override
    protected LinkedList<Integer> getLinkedList() {
        return new SinglyLinkedList<>();
    }

}