Kuudos - a sudoku puzzle solver
===============================

Kuudos is an experiment just for fun. It aims to solve sukoku puzzles
in a way that abstracts away as much of the mechanics of the puzzle
as possible.

General strategy
----------------

Kuudos represents a sudoku board in a hierarchical structure. In a sudoku
board, there are 3 types of structures that must contain each of the
numbers 1 through 9 exactly once: rows, columns and squares. We store
a node for each of these, and for all of their possible intersections,
i.e. 1x3 or 3x1 blocks inside a square, and single cells. Nodes can
have several parents and several children, and this is done so that each
node is effectively partitioned into its children.

Throughout the execution, each node will have a set of numbers that it
will contain (a 'yes-set') and a set of numbers that it will certainly
not contain (a 'no-set'). Any node's yes-set contains the union of its
children's yes-sets, and is the intersection of its parents'. Any node's
no-set will be the intersection of its children's no-sets and will be
contained in the union of its parents'.

Maintaining this structure allows us to make deductions until no further
conclusions can be made, at which point we make a guess (in a single
cell that has the smallest number of possible values).
