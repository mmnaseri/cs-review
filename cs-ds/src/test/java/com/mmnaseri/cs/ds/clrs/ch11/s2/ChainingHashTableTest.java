package com.mmnaseri.cs.ds.clrs.ch11.s2;

import com.mmnaseri.cs.ds.clrs.ch11.HashTable;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/13/15)
 */
public class ChainingHashTableTest extends BaseLargeKeysHashTableTest {

    @Override
    protected HashTable<Integer>[] getHashTables() {
        //noinspection unchecked
        return new HashTable[]{new ChainingHashTable<>(10)};
    }

}