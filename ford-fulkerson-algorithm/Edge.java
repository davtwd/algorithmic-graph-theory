public class Edge {

	private int a;
	private int b;
	private int cost;
	private int capacity;

	public Edge (int from, int to, int cost, int capacity) { 
		this.a = from;
		this.b = to;
		this.cost = cost;
		this.capacity = capacity;
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
}
