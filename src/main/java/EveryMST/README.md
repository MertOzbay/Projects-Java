#EveryMST
>This was a project for my Data Structures and Algorithms class. I wrote a program that generates every Minimum Spanning Tree (MST). Given a graph with weighted edges, _getMinimumSpanningTrees()_ will return a list of SpanningTrees of the graph where all members of the list are MSTs.

>My approach is to build all possible spanning trees edge by edge, discarding the trees that exceed the total weight of the MST. If the spanning tree is completed and didn't exceed the total weight of an MST, then it is also an MST, and it is added to the list.
