import java.util.ArrayList;
import java.util.PriorityQueue;

public class Graph {

	private ArrayList<Vertex> vertexList;
	private ArrayList<Edge> edgeList;

	public Graph(ArrayList<Edge> edgeList, int maxVertex) {

		this.edgeList = edgeList;
		this.vertexList = new ArrayList<>(maxVertex);

		for (int i = 0; i < maxVertex + 1; i++) {
			this.vertexList.add(new Vertex(i));
		}
	}

	public void getMaxFlow(int source, int sink) {

		int maxFlow = 0;
		int minCost = 0;
		int[] parent = new int[vertexList.size()];

		while (bfs(source, sink, parent)) {

			int pathFlow = Integer.MAX_VALUE;

			for (int v = sink; v != source; v = parent[v]) {
				int u = parent[v];
				for (Edge e : this.edgeList) {
					if (e.getA() == u && e.getB() == v) {
						pathFlow = Math.min(pathFlow, e.getCapacity());
					}
				}
			}

			for (int v = sink; v != source; v = parent[v]) {
				int u = parent[v];
				for (Edge e : this.edgeList) {
					if (e.getA() == u && e.getB() == v) {
						e.setCapacity(e.getCapacity() - pathFlow);
						minCost += pathFlow * e.getCost();
					} else if (e.getA() == v && e.getB() == u) {
						e.setCapacity(e.getCapacity() + pathFlow);
						minCost -= pathFlow * e.getCost();
					}
				}
			}
			maxFlow += pathFlow;
		}

		System.out.println("Source: " + source);
		System.out.println("Sink: " + sink);
		System.out.println("Maximal flow: " + maxFlow);
		System.out.println("Minimum-cost flow: " + minCost);
	}

	private boolean bfs(int source, int sink, int[] parent) { // breadth-first search 

		boolean[] visited = new boolean[vertexList.size()];
		PriorityQueue<Integer> epsilon = new PriorityQueue<>();
		epsilon.add(source);
		visited[source] = true;
		parent[source] = -1;
	
		while (!epsilon.isEmpty()) {
			int root = epsilon.poll();
	
			for (Edge e : this.edgeList) {
				if (!visited[e.getB()] && e.getCapacity() > 0 && e.getA() == root) {
					visited[e.getB()] = true;
					parent[e.getB()] = root;
					epsilon.add(e.getB());
				}
			}

		}
	
		return visited[sink];
	}
}