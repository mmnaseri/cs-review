BTree Implementation
====================

The BTree data structure is a balanced search tree that has a branching factor between `t - 1` to
 `2t - 1` for each node. The constant `t` is called the BTree minimum degree.
 
 The implementation provided here follows the one described by the book very closely. The only difference
 is that here the data is always contained under the leaves, which makes it more like a B+Tree. The difference
 between this implementation and a B+Tree is that here the leaves do not form a doubly linked list and as such
 getting the data is not as easy as it is with a B+Tree.
 
 In some ways, this makes this implementation superior to that of the book, in that it requires a much more
 flat disk storage to manage the data for this BTree. However, this means you cannot place more frequently
 queried nodes closer to the root and get any meaningful benefit. Here, getting the data always requires
 `Omega(h)` which is `Omega(log(n)/log(t))`.
 
 In retrospect, it would have made much more sense to have the disk implementation address data by their
 key rather than by their surrogate nodes' unique identifier, which would have made it far less disk
 intensive to move nodes around, as moving nodes around wouldn't have had anything to do with moving and
 readdressing their relevant data.