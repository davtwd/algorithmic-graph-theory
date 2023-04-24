public class Edge {

	private int A;
	private int B;
	private int cost;

	public Edge (int from, int to, int cost) { 
		this.A = from;
		this.B = to;
		this.cost = cost;
	}

	public int getA() {
		return this.A;
	}

	public int getB() {
		return this.B;
	}

	public int getCost() {
		return this.cost;
	}
}
