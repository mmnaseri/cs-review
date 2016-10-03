Notes
=====

Initially, I thought that modelling a linear program as a set of individual constraints and an objective function
was a good idea.

However, as it turns out, this means that solving the program using the simplex algorithm (or other algorithms for
that matter) becomes exhaustingly difficult.

It would have been better to model the programs using matrix tuples as suggested by the book (duh). As such, I have
not implemented the rest of this chapter, in hopes of someday coming back and remodelling it appropriately so that
the solution is both easy to implement and easy to understand.