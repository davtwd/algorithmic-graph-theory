# algorithmic-graph-theory

 This repository contains my work during a semester, focused on implementing algorithms such as "shortest-path" using the labelset method, "kruskal-algorithm," "critical-path-method," and the "maximal-flow" to calculate maximal flow and minimal cost using Dijkstra's shortest path. 

## Installlation

```
git clone https://github.com/davtwd/algorithmic-graph-theory.git
```

## Running the programs

To run the program, simply execute the `main` file in the root directory, **nothing is shared between the folders**. All necessary dependencies are included in the folder.

## Datasets

### critical-path-method

#### .txt

This dataset contains information about edges. Each line represents a single edge, and the corresponding data provides information about that edge.

| column 1  | column 2  | column 3  |
|:---------:|:---------:|:---------:|
| source  | destination  | cost  |

#### .tim

This file has a simple structure, where each vertex has a single line representing the duration of the activity. Each line corresponds to a vertex, and the line number in the file corresponds to the index of the vertex. The first line is for vertex 1, the second line is for vertex 2, and so on.

### kruskal-algorithm and shortest-path

#### .txt

This dataset contains information about edges. Each line represents a single edge, and the corresponding data provides information about that edge.

| column 1  | column 2  | column 3  |
|:---------:|:---------:|:---------:|
| source  | destination  | cost  |

### maximal-flow

#### .txt

This dataset represents a list of edges, similar to other datasets. Each line in the dataset contains several items separated by spaces, representing the following values: "number of the starting vertex", "number of the ending vertex", "edge cost", and "edge capacity".

| column 1  | column 2  | column 3  | column 4  |
|:---------:|:---------:|:---------:|:---------:|
| source  | destination  | cost  | capacity |

## Contributing

If you find a bug or would like to suggest an improvement, feel free to submit a pull request. I would love to see this project grow and improve over time.


## License

This repository is [WTFPL licensed](./LICENSE).