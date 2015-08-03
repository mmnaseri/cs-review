BFS
===

It is possible to exploit the adjacency list representation of a graph and partition the vertex list
into three sections: `0 .. i .. j .. n`, where `0 .. i - 1` is all black, `i .. j - 1` is all grey,
and `j .. n` is all white.
 
This can be achieved by moving the vertices around, and since renaming the vertices in a graph has no
effect on the structure of the graph this will reduce space complexity since we no longer need to keep
track of node colors by mapping nodes to colors manually. This space reduction will be made possible at
the expense of doing `O(n)` swaps:

  1. From white region to grey region when the node is being explored
  2. From grey region to black region when the node is completely explored

Since a node cannot be in any of the three states more than once and must pass each stage exactly once,
we know that there will be `3n` swaps, hence the `O(n)` time complexity.

Since BFS is already `O(n)` this will not reduce the algorithms efficiency by more than a constant factor,
while gaining us much in terms of space requirements.

**This is only true if we are using adjacency lists though**, since otherwise the cost of swapping
vertices would be nonlinear.

This is possible because in our current implementation the vertex object is the single source of truth for
the vertex number, and the adjacency list for vertex `i` contains pointers to these objects, which can be
modified all in a single pass over the vertex list.