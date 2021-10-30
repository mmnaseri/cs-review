# Computer Science Courses Review

This is an ongoing, personal effort on my part to refresh my CS acumen from my university days.

What's covered
--------------

> As I mentioned earlier, this is an ongoing effort, so this list is subject to changes as frequent
  as I read the material. ;-)

### CLRS

From the esteemed "**Introduction to Algorithms (3rd Edition)**" *(ISBN-13: 978-0262033848)*
book by Cormen, Leiserson, Rivest, and Stein:

Chapters missing implementation:

 * Chapter 1: This is introducing the concept of algorihtms and does not include any codeable text.

 * Chapter 3: Nothing to implement.

 * Chapter 17: This chapter talks about a particular type of algorithm analysis (amortized analysis), rather
 than introducing algorithms or data structures.

 * Chapter 30: The only problem introduced and covered in this chapter is the Fourier transform. I did not get
 to implementing it.

 * Chapter 31: This chapter mostly talks about the arithmatic framework used for data encryption and the RSA.

 * Chapter 33: This chapter includes abstractions for working with geometric concepts and a number of algorithms
 for doing routine operations on geometric shapes. I never got down to implementing them, though they are among
 the most straightforward in this book.

 * Chapter 34: This chapter talks about the concept of NP-Completeness, and while does include many problem
 definitions, the problems are only solvable by brute-force and very inefficient algorithms. I didn't need
 the practice, so I didn't include them.

 * Chapter 35: The algorithms outlined in this chapter are approximation algorithms. I didn't get down to implementing them.
 
> As you might have noticed from the above, I mostly got lazy when it came to the selected topics. I might one day get back to it and implement them properly :-)


### Skiena

From the book "**The Algorithm Design Manual (2nd Edition)**" *(ISBN-13: 978-1848000698)* by Steven S. Skiena.

I will only implement things that are not already covered in the CLRS book (or things I was too lazy
to implement when reading the CLRS book), as I am covering this book as a supplement to my knowledge
from the CLRS book.

I will try to stick to the same level of testing and quality assurance as with the CLRS implementations, and will
continue to annotate all classes accordingly.

The Annotations
---------------

The code is well organized, and mostly well annotated. There are a pair of specific annotations defined in `cs-qa` and
used throughout the codebase:

  * `@Test` which points to specific test cases directed at the annotated piece of code.
  
  * `@Tests` which acts to aggregate all the test cases directed at the annotated piece of code.

  * `@Complexity` which indicates one of the following:
   
    - if it is placed on a method, it is indicative of that piece of code's time complexity in terms of `O`, `Omega`,
    or `Theta` asymptotic notations. Since for indicating time complexity we need to have specific methods doing specific
     things, whenever we have the intent of presenting a thorough time complexity analysis of a problem, we tend to
     extract smaller pieces of functionality that contribute to the time complexity of the code into their own methods
     and then annotate those.
    - if it is placed on a variable or a parameter, it indicates what sort of space complexity that variable follows
    in terms of `O`, `Omega`, or `Theta` asymptotic notations.

  * `@Quality` which indicates the standing quality of the piece of code being annotated. The `@Quality` annotation
  specifies a development `Stage` of one of the following values:
   - `INCOMPLETE`: the annotated piece of code has not been developed fully and that it will require
   more development attention before it can be considered a working model.
   - `BUGGY`: that particular piece of code has known issues that have not been tested and have not
   been patched yet. This stage usually comes with an explanation as to the nature of the bug.
   - `UNTESTED`: the piece of code is believed to be doing what it is supposed to, but that it is not
   yet tested sufficiently.
   - `FAILING`: this piece of code breaks some test somewhere. This should be accompanied by an `@Test` or
   an `@Tests` annotation, since the failing tests are usually either disabled or commented out.
   - `TESTED`: the code being annotated is believed to have been sufficiently tested and works according to the specifications.
   - `DOCUMENTED`: the code is both well tested and well documented. Code marked as documented is expected to have at least been
   given time complexity analysis using the `@Complexity` annotation.

  * `@Monitored` denotes the monitor that has been implemented for the given data structure. Monitors are
  observers that observe your data structure's state before/after each modification to make sure that
  they behave exactly as they should and that they present the same features that were advertised for them.
  
Most of the code in this project is properly annotated. If you find one that is not, please do not hesitate to report it.

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
