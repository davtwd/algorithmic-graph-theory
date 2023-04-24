public class Edge {

	private int a;
	private int b;
	private int cost;

	public Edge (int from, int to, int cost) { 
		this.a = from;
		this.b = to;
		this.cost = cost;
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
}
