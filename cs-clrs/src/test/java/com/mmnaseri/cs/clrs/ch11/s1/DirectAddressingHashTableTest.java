package com.mmnaseri.cs.clrs.ch11.s1;

import com.mmnaseri.cs.clrs.ch11.HashTable;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/13/15)
 */
public class DirectAddressingHashTableTest extends com.mmnaseri.cs.clrs.ch11.BaseHashTableTest {

    @Override
    protected HashTable<Integer>[] getHashTables() {
        //noinspection unchecked
        return new HashTable[]{new DirectAddressingHashTable<>(10)};
    }

}