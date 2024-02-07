import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Graph {

	private static final int INFINITY = Integer.MAX_VALUE;
	private ArrayList<Vertex> vertexList;
	private ArrayList<Edge> edgeList;

	public Graph(ArrayList<Edge> edgeList, int maxVertex) {

		this.edgeList = edgeList;
		this.vertexList = new ArrayList<>(maxVertex);
		
		for (int i = 0; i < maxVertex + 1; i++) {
			this.vertexList.add(new Vertex(i));
		}

	}

	/** The fundamental principle of algorithm
	 * t(v) - the distance of vertex v from the starting vertex of the path
	 * x(v) - the predecessor of vertex v on the path from the starting vertex
	 * 
	 * if	t(j) > t(i) + c(i, j)
	 * then	t(j) = t(i) + c(i, j)
	 * 		x(j) = i
	 */

	/**
	 * Implements the label-setting algorithm to find the shortest path between two vertices in the graph.
	 * The final shortest path and its distance are printed to the console.
	 * 
	 * @param from	The starting vertex for the shortest path.
	 * @param to	The target vertex for the shortest path.
	 */
	public void findShortestPath(int from, int to) {

		int[] distances = new int[this.vertexList.size()]; // t(v)
		int[] predecessor = new int[this.vertexList.size()]; // x(v)

		int rootVertex = 0;
		Map<Integer, List<Edge>> edgeMap = new HashMap<Integer, List<Edge>>();
		PriorityQueue<Integer> epsilon = new PriorityQueue<Integer>((u, v) -> distances[u] - distances[v]);

		long timer = System.nanoTime();
		
		System.out.println("Recalculating the shortest path...");

		for (int i = 0; i < distances.length; i++) {
			distances[i] = INFINITY;
			predecessor[i] = -1;
		}

		distances[from] = 0;

		for (Edge e : this.edgeList) {
			if (!edgeMap.containsKey(e.getA())) {
				edgeMap.put(e.getA(), new ArrayList<Edge>());
			}
			edgeMap.get(e.getA()).add(e);
		}
		epsilon.add(from);

		while (!epsilon.isEmpty()) {
			rootVertex = epsilon.poll();

			if (edgeMap.get(rootVertex) == null) {
				continue;
			}

			for (Edge e : edgeMap.get(rootVertex)) {
				if (distances[e.getB()] > distances[rootVertex] + e.getCost()) {
					distances[e.getB()] = distances[rootVertex] + e.getCost();
					predecessor[e.getB()] = rootVertex;
					epsilon.add(e.getB());
				}
			}
		}

		double elapsedTime = (double)(System.nanoTime() - timer) / 1_000_000_000;

		int next = to;
		int distance = distances[to];
		ArrayList<Integer> sequence = new ArrayList<Integer>();

		while (next != -1) {
			sequence.add(0, next);
			next = predecessor[next];
		}

		System.out.println(String.format("The shortest path from vertex %d to vertex %d has length %d (%.3fs)", from, to, distance, elapsedTime));

		System.out.print("Path: ");
		for (int i = 0; i < sequence.size(); i++) {
			System.out.print(sequence.get(i));
			if (i < sequence.size() - 1) {
				System.out.print(" -> ");
			}
		}
	}
}
