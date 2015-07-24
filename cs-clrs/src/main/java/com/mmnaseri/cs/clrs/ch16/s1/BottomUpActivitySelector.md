Bottom-Up, Dynamic Programming Solution for the Activity Selection Problem
==========================================================================

We define `S(i, j)` as the sub-problem of finding the largest number of activities that contain both `a[i]` and `a[j]`.

We therefore face the following situations:

  - `i == j` in which case `a[i] == a[j]` is chosen and the length is set to `1` (or `length(S(i, j)) = 1`).
  - `i != j` in which case either `a[i]` is incompatible with `a[j]` and such a sequence does not exist and therefore
  we have `length(S(i, j)) = 0`. Otherwise, we look for index `k`, where `i < k < j`, and `a[k]` is compatible with
  both `a[i]` and `a[j]` and for all such `a[k]`s we figure out the answer to `S(i,k)` and `S(k, j)`. Since our sub-problem
  is inclusive, then the answer coming back from that route is `S(i,k) + S(k,j) - 1` since we have counted `a[k]` twice. We
  then take the maximum of `2` (having a sequence only containing `a[i]` and `a[j]`) and all such possible sub-problem
  solutions. If we find a `k` that is better suited than the rest, we should log it somewhere to be able to reconstruct
  the answer.
  
So, the recursive solution to our problem is:

    S(i, j) =
        if i == j: 1
        if i != j and a[i] is incompatible with a[j]: 0
        otherwise: max{2, S(i,k) + S(k,j) - 1} for all i < k < j

which is slightly different from the one outlined in the book, but is much easier to use for a bottom-up, iterative solution.

The assumption here, the same as in the book, is that activities are sorted base on their finish time. So, we can write the
pseudo-code for the algorithm in its iterative form as follows:

     1 Bottom-Up-Activity-Selection(f, s)
     2    n = f.length
     3   create table c[1 .. n, 1 .. n], with c[i,j] = 0
     4   best = 0
     5   if n > 0
     6       best = 1
     7   for l from 1 to d
     8       for i from 1 to n - l
     9           j = i + l
    10           if f[i] <= s[j] then
    11               c[i,j] = 2
    12               for k from j + 1 to i - 1
    13                   if s[k] >= f[i] and f[k] <= s[j]
    14                       v = c[i,k] + c[k,j] - 1
    15                       if v >= c[i,j]
    16                           c[i,j] = v
    17                           if v >= best
    18                               best = v
    19   return best

At this point, we get the answer back as the maximum number of compatible activities that could be selected from the
given input. To be able to return the actual selection, we need to add one more piece of information: the set of indices
`k` such that `k` was chosen as the middle point for solving `S(i,j) = S(i,k) + S(k,j) - 1`:
                    
     1 Bottom-Up-Activity-Selection(f, s)
     2    n = f.length
     3   create table c[1 .. n, 1 .. n], with c[i,j] = 0
     4   create table x[1 .. n, 1 .. n], with c[i,j] = -1
     5   start = 0
     6   end = 0
     7   for l from 1 to d
     8       for i from 1 to n - l
     9           j = i + l
    10           if f[i] <= s[j] then
    11               c[i,j] = 2
    12               for k from j + 1 to i - 1
    13                   if s[k] >= f[i] and f[k] <= s[j]
    14                       v = c[i,k] + c[k,j] - 1
    15                       if v >= c[i,j]
    16                           c[i,j] = v
    17                           x[i,j] = k
    18                           if v >= c[start,end]
    19                               start = i
    20                               end = j
    21   return (start, end, x)

which returns the index of the first and the last activities chosen with the list of indices chosen as middle points.

Whenever `x[i,j]` is `-1` it means no activity `a[k]` exists with `i < k < j` that can be chosen to maximize the answer.

We can then write the following function to print out the selection:

    1 Print-Activities(start, end, x, l)
    2    print start
    3    if x[start,end] != -1
    4        Print-Activities(start, x[start, end], x, false)
    5        Print-Activities(x[start,end], end, x, true)
    6        if l == true
    7            print end

where `l` is a flag that says whether or not we should print the last element in the interval `[i,j]` and is to avoid
printing `k` for `a[k]` twice.

The solution would then be reproducible using:

    Print-Activities(Bottom-Up-Activity-Selection(f[1 .. n], s[1 .. n]), true)

which will print out all the solutions.

It is notable that we only use the top half of both tables `c` and `x` in `Bottom-Up-Activity-Selection` while leaving
the main diagonal alone. Since both tables only store numbers, we can merge them into one, utilizing the upper half for
storing the counts and the lower half for the indices. Thus, our activity selection implementation becomes as follows:

     1 Bottom-Up-Activity-Selection(f, s)
     2    n = f.length
     3   create table c[1 .. n, 1 .. n], with c[i,j] = 0 where i < j and c[i,j] = -1 where i > j
     4   start = 0
     5   end = 0
     6   for l from 1 to d
     7       for i from 1 to n - l
     8           j = i + l
     9           if f[i] <= s[j] then
    10               c[i,j] = 2
    11               for k from j + 1 to i - 1
    12                   if s[k] >= f[i] and f[k] <= s[j]
    13                       v = c[i,k] + c[k,j] - 1
    14                       if v >= c[i,j]
    15                           c[i,j] = v
    16                           c[j,i] = k
    17                           if v >= c[start,end]
    18                               start = i
    19                               end = j
    20   return (start, end, c)

and the print function now needs to read from the lower half as well:

    1 Print-Activities(start, end, x, l)
    2    print start
    3    if x[end,start] != -1
    4        Print-Activities(start, x[end,start], x, false)
    5        Print-Activities(x[end,start], end, x, true)
    6        if l == true
    7            print end

The print operation works in `O(n)` since we do not go over each item more than once (line 3 is executed exactly
`n` times at worst). This is similar to the analysis for binary search.

The actual bottom-up solution operates in `O(n^3)` which is considerably worse than the normal greedy algorithm.
This is because, mainly, the number of uniquely exhibited sub-problems is too much and the overlap frequency of
these sub-problems is too little to allow us to properly utilize their recurrence to minimize time complexity.