public class Edge {

	private int a;
	private int b;
	private int cost;
	private int capacity;
	private int flow;

	public Edge(int from, int to, int cost, int capacity) {
		this(from, to, cost, capacity, 0);
	}

	public Edge (int from, int to, int cost, int capacity, int flow) { 
		this.a = from;
		this.b = to;
		this.cost = cost;
		this.capacity = capacity;
		this.flow = flow;
	}

	public int getA() {
		return this.a;
	}

	public int getB() {
		return this.b;
	}

	public int getCost() {
		return this.cost;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getFlow() {
		return this.flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}

	public void addFlow(int flow) {
		this.flow += flow;
	}
		
	public Edge getReverseEdge() {
		return new Edge(this.b, this.a, -this.cost, 0, -this.flow);
	}
}
