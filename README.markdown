Kuudos - a sudoku puzzle solver
===============================

Kuudos is an experiment just for fun. It aims to solve sukoku puzzles
in a way that abstracts away as much of the mechanics of the puzzle
as possible.

General strategy
----------------

A sudoku puzzle can be thought of as follows. We have a set S of cells
(81 in total) and we have a set T of subsets of S all of the same size n
(i.e. comprising 9 cells, with 9 subsets representing rows, 9 subsets
representing columns, and 9 subsets representing squares). The objective
is to label each cell with one of the n symbols (typically 1, 2, ...,
9) such that each of the given subsets of S contains each of the symbols
exactly once.

Kuudos represents a sudoku board in a hierarchical structure (but not
a tree!). To help us make deductions, we have a node corresponding to
each possible intersecion of sets in T (the rows, columns and squares),
i.e. the rows, columns and squares themselves, 1x3 or 3x1 blocks inside
squares, and single cells. Each node corresponds to a subset of S,
and we can give them a partial order by set inclusion. The structure we
then store them in is like a Hasse diagram, where every node x is joined
to every node y that covers it (i.e. x < y and there is no node z such
that x < z < y). The difference to a true Hasse diagram is that there
isn't a minimal or maximal node. Each node may have multiple parents
(representing sets that contain its set) and multiple children.

Throughout the execution, each node will have a set of numbers that it
will contain (a 'yes-set') and a set of numbers that it will certainly
not contain (a 'no-set'). Any node's yes-set contains the union of its
children's yes-sets, and is the intersection of its parents'. Any node's
no-set will be the intersection of its children's no-sets and will be
contained in the union of its parents'.

Maintaining this structure allows us to make deductions until no further
conclusions can be made, at which point we make a guess (in a single
cell that has the smallest number of possible values).

Using this abstract approach means that it takes almost zero effort to
adjust to solving 'Samurai sudoku' puzzles and the like.
