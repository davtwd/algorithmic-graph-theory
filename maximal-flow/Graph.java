import java.util.ArrayList;

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
		int[] parent = new int[vertexList.size()];

		while (bfs(source, sink, parent)) {

			int pathFlow = Integer.MAX_VALUE;

			for (int v = sink; v != source; v = parent[v]) {
				int u = parent[v];
				for (Edge e : this.edgeList) {
					if (e.getA() == u && e.getB() == v) {
						pathFlow = Math.min(pathFlow, e.getCapacity() - e.getFlow());
					}
				}
			}

			for (int v = sink; v != source; v = parent[v]) {
				int u = parent[v];
				for (Edge e : this.edgeList) {
					if (e.getA() == u && e.getB() == v) {
						e.addFlow(pathFlow);
					} else if (e.getA() == v && e.getB() == u) {
						e.addFlow(-pathFlow);
					}
				}
			}
			maxFlow += pathFlow;
		}

		System.out.println(String.format("Output [source, sink, max-flow]: %d, %d, %d", source, sink, maxFlow));
	}

	private boolean bfs(int source, int sink, int[] parent) { // breadth-first search 

		boolean[] visited = new boolean[vertexList.size()];
		ArrayList<Integer> epsilon = new ArrayList<>();
		epsilon.add(source);
		visited[source] = true;
		parent[source] = -1;
	
		while (!epsilon.isEmpty()) {
			int root = epsilon.remove(0);
	
			for (Edge e : this.edgeList) {
				if (!visited[e.getB()] && (e.getCapacity() - e.getFlow()) > 0 && e.getA() == root) {
					visited[e.getB()] = true;
					parent[e.getB()] = root;
					epsilon.add(e.getB());
				}
			}

		}
	
		return visited[sink];
	}
}