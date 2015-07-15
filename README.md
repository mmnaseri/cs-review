# Computer Science Courses Review

This is an ongoing, personal effort on my part to refresh my CS acumen from my university days.

What's covered
--------------

  > As I mentioned earlier, this is an ongoing effort, so this list is subject to changes as frequent
  as I read the material. ;-)

### CLRS

From the esteemed Introduction to Algorithms book by Cormen, Leiserson, Rivest, and Stein:

  * Chapters 1-12: these are implemented and (hopefully) well tested. Chapter 3 is missing, since
  it didn't have anything directly algorithm- or data structure-related.

The Tests
---------

Each module comes with its own suite of tests. Since many of the classes are implementing answers
to the same problems, only in different ways (e.g. quick sort, merge sort, and bubble sort all answer
the problem of sorting), it makes sense to have base classes that set a shared expectation and have
specific test classes run these expectations against their own implementation.

Tests are written with TestNG as their runner and Hamcrest as the BDD interface, since it is just a
lot nicer to read (and write).

Test coverage for most of classes is above 90%. This is not a guaranteed number, and is usually violated
when I've been feeling particularly sleepy when writing the tests.

They do, however, provide a solid base line for pressure-testing the algorithms and data structures and
covering the edge cases.

Whenever a random factor is at play (e.g. randomized quick sort) test suites are designed to run a larger
number of times.

Also, since most of the time testing involves doing the same chores with different sets of data or a
different initial configuration, I have gone to town with TestNG's data provider feature :)


-----------------------

keywords: algorithms, data structures, clrs, cormen, leiserson, rivest, stein, computer science, cs review
