import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Graph {

	private ArrayList<Vertex> vertexList;
	private ArrayList<Edge> edgeList;
	private ArrayList<Edge> spanningTree;

	public Graph(ArrayList<Edge> edgeList, int maxVertex) {

		this.edgeList = edgeList;
		this.vertexList = new ArrayList<>(maxVertex);
		this.spanningTree = new ArrayList<>();

		for (int i = 0; i < maxVertex + 1; i++) {
			this.vertexList.add(new Vertex(i));
		}

	}

	public void getSpanningTree(boolean minimum) {
		
		long cost = 0;
		long sortTimer = System.nanoTime();

		Collections.sort(this.edgeList, new Comparator<Edge>() {
			@Override
			public int compare(Edge e1, Edge e2) {
				return Integer.compare(e1.getCost(), e2.getCost());
			}
		});

		if (!minimum) {
			Collections.reverse(this.edgeList);
		}

		System.out.println("Recalculating " + (minimum ? "minimum" : "maximum") + " spanning tree...");
		long timer = System.nanoTime();
		
		for (Edge e : this.edgeList) {
			if (lookup(e.getA()) != lookup(e.getB())) {
				this.spanningTree.add(e);
				cost += e.getCost();
				merge(e.getA(), e.getB());
			}
		}

		double elapsedTime = (double)(System.nanoTime() - timer) / 1_000_000_000;

		System.out.println("Type: " + (minimum ? "minimum" : "maximum"));
		System.out.println("Cost: " + cost);
		System.out.println("Time: " + String.format("%.3fs", elapsedTime));
		System.out.println("Time (with sorting): " + String.format("%.3fs", (double)(System.nanoTime() - sortTimer) / 1_000_000_000));

		// Spanning Tree output
		/*for (int i = 0; i < this.spanningTree.size(); i++) {
			Edge e = this.spanningTree.get(i);
			System.out.print("(" + e.getA() + ", " + e.getB() + ", " + e.getCost() + ")");
			if (i < this.spanningTree.size() - 1) {
				System.out.print(";");
			}
		}*/
	}

	private int lookup(int vertex) {
		if (this.vertexList.get(vertex).getId() == vertex) {
			return vertex;
		}
		this.vertexList.get(vertex).setId(lookup(this.vertexList.get(vertex).getId()));
		return this.vertexList.get(vertex).getId();
	}

	private void merge(int vA, int vB) {
		this.vertexList.get(lookup(vA)).setId(lookup(vB));
	}
}
