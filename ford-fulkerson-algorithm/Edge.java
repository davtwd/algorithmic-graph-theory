public class Edge {

	private int A;
	private int B;
	private int cost;
	private int capacity;

	public Edge (int from, int to, int cost, int capacity) { 
		this.A = from;
		this.B = to;
		this.cost = cost;
		this.capacity = capacity;
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

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
