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
	
		while (dijkstra(source, sink, parent)) {
	
			int pathFlow = Integer.MAX_VALUE;
			int pathCost = 0;
	
			for (int v = sink; v != source; v = parent[v]) {
				int u = parent[v];
				for (Edge e : this.edgeList) {
					if (e.getA() == u && e.getB() == v) {
						pathFlow = Math.min(pathFlow, e.getCapacity() - e.getFlow());
						pathCost += e.getCost();
					}
				}
			}
	
			for (int v = sink; v != source; v = parent[v]) {
				int u = parent[v];
				boolean foundForwardEdge = false;
				boolean foundReverseEdge = false;
				Edge reverseEdge = null;

				for (Edge e : this.edgeList) {
					if (e.getA() == u && e.getB() == v) {
						e.addFlow(pathFlow);
						foundForwardEdge = true;
						reverseEdge = e.getReverseEdge();
					} else if (e.getA() == v && e.getB() == u) {
						e.addFlow(-pathFlow);
						foundReverseEdge = true;
					}
				}
					
				if (foundForwardEdge && !foundReverseEdge) {
					this.edgeList.add(reverseEdge);
				}
			}

			maxFlow += pathFlow;
			minCost += pathFlow * pathCost;
		}

		System.out.println(String.format("Output [source, sink, max-flow, min-cost]: %d, %d, %d, %d", source, sink, maxFlow, minCost));
	}

	private boolean dijkstra(int source, int sink, int[] parent) {

		int[] dist = new int[vertexList.size()];
		
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		dist[source] = 0;

		boolean[] visited = new boolean[vertexList.size()];

		PriorityQueue<Integer> epsilon = new PriorityQueue<>((u, v) -> dist[u] - dist[v]);
		epsilon.add(source);

		while (!epsilon.isEmpty()) {
			int root = epsilon.poll();
			visited[root] = true;

			for (Edge e : this.edgeList) {
				if ((e.getCapacity() - e.getFlow()) > 0 && e.getA() == root) {
					int neighbor = e.getB();
					int alt = dist[root] + e.getCost();
					if (alt < dist[neighbor]) {
						dist[neighbor] = alt;
						parent[neighbor] = root;
						epsilon.add(neighbor);
					}
				}
			}
		}

		return visited[sink];
	}
}