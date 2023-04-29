# Shortest Path

## Data

This data files contain information about the edges of a graph, with each row representing an edge.

The format of each row is as follows:

- vertex a: the starting vertex of the edge
- vertex b: the ending vertex of the edge
- cost of the edge: the weight or cost of the edge

For example, if the first row is "1 3 10" it represents an edge that starts from vertex 1, ends at vertex 3 and has a cost of 10.

#

## Fundamental principle of shortest path algorithm

<p align="center">
  <img src="https://i.imgur.com/6WDRdKU.png" alt="fundamental-principle" />
</p>

## Label set implementation (that I use)

For selecting a vertex, we use the set *𝜺*, which will contain unprocessed vertices.

To start finding the shortest path in a graph, we begin with the starting vertex and add it to a set called *𝜺*. Then we select a vertex from *𝜺* and look at all the edges coming out of it. Whenever we find a new vertex along the way, we add it to *𝜺* so we can explore it later.

**Label set** - implementation of Dijkstra's algorithm - We first process the vertex from the set 𝜀 with the lowest label t(i)."