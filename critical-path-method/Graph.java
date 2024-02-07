import java.util.ArrayList;

public class Graph {

	private ArrayList<Edge> edgeList;
	private ArrayList<Vertex> vertexList;

	public Graph(ArrayList<Edge> edgeList, ArrayList<Vertex> vertexList) {

		this.edgeList = edgeList;
		this.vertexList = vertexList;

		for (Edge edge : this.edgeList) {
			this.vertexList.get(edge.getB()).addInputEdge(edge);
			this.vertexList.get(edge.getA()).addOutputEdge(edge);
		}

	}

	public void getCPM() { // Critical Path Method

		long timer = System.nanoTime();
	
		ArrayList<Vertex> topologicalOrder = getTopologicalOrdering();
		int[] beginnings = new int[topologicalOrder.size()];
		int[] ends = new int[topologicalOrder.size()];
		int[] deadlines = new int[topologicalOrder.size()];
		int[] reserves = new int[topologicalOrder.size()];
		int overallDuration = 0;

		for (int i = 0; i < topologicalOrder.size(); i++) {
			Vertex vertex = topologicalOrder.get(i);

			for (Edge edge : vertex.getOutputEdges()) {
				if (beginnings[edge.getB()] < beginnings[edge.getA()] + vertex.getDuration()) {
					beginnings[edge.getB()] = beginnings[edge.getA()] + vertex.getDuration();
				}
			}
		}

		for (int i = 0; i < ends.length; i++) {
			overallDuration = Math.max(overallDuration, beginnings[i] + this.vertexList.get(i).getDuration());
		}

		for (int i = 0; i < ends.length; i++) {
			ends[i] = overallDuration;
		}

		for (int i = topologicalOrder.size() - 1; i >= 0; i--) {
			Vertex vertex = topologicalOrder.get(i);

			for (Edge edge : vertex.getInputEdges()) {
				if (ends[edge.getA()] > ends[edge.getB()] - vertex.getDuration()) {
					ends[edge.getA()] = ends[edge.getB()] - vertex.getDuration();
				}
			}
		}

		ArrayList<Integer> criticalActions = new ArrayList<Integer>();

		for (int i = 1; i < deadlines.length; i++) {
			deadlines[i] = ends[i] - beginnings[i];
			reserves[i] = deadlines[i] - this.vertexList.get(i).getDuration();

			if (reserves[i] == 0) {
				criticalActions.add(i);
			}
		}

		double elapsedTime = (double)(System.nanoTime() - timer) / 1_000_000_000;

		System.out.println("The duration of the project: " + overallDuration);

		System.out.print("Critical actions: ");
		for (int i = 0; i < criticalActions.size(); i++) {
			System.out.print(criticalActions.get(i));
			if (i < criticalActions.size() - 1) {
				System.out.print(", ");
			} else System.out.println();
		}

		System.out.print("The earliest action begginings: [");
		for (int i = 0; i < beginnings.length; i++) {
			System.out.print(beginnings[i]);
			if (i < beginnings.length - 1) {
				System.out.print(", ");
			} else System.out.println("]");
		}
		
		System.out.print("The latest action ends: [");
		for (int i = 0; i < ends.length; i++) {
			System.out.print(ends[i]);
			if (i < ends.length - 1) {
				System.out.print(", ");
			} else System.out.println("]");
		}

		System.out.format("Time: %.3fs", elapsedTime);
	}

	public ArrayList<Vertex> getTopologicalOrdering() { // monotone numbering (also known as topological numbering or topological ordering)
		ArrayList<Vertex> order = new ArrayList<Vertex>();
		int[] inputs = new int[this.vertexList.size()];
		
		for (int i = 0; i < inputs.length; i++) {
			inputs[i] = this.vertexList.get(i).getInputEdges().size();
		}
		
		while (true) {

			Vertex root = null;
			
			for (int i = 0; i < inputs.length; i++) {
				if (inputs[i] == 0) {
					inputs[i] = -1;
					root = this.vertexList.get(i);
					break;
				}
			}
			
			if (root == null) {
				break;
			}
			
			order.add(root);
			
			for (Edge e : root.getOutputEdges()) {
				inputs[e.getB()]--;
			}
		}
		
		if (order.size() != this.vertexList.size()) {
			System.out.println("The graph is not acyclic!");
			return order;
		}

		System.out.print("MO: ");
		for (int i = 0; i < order.size(); i++) {
			System.out.print(order.get(i).getId());
			if (i < order.size() - 1) {
				System.out.print(" -> ");
			}
		}
		System.out.println();
		
		return order;
	}
}
