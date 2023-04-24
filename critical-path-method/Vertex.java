import java.util.ArrayList;

public class Vertex {
	
	private int id, duration;
	private ArrayList<Edge> inputEdges;
	private ArrayList<Edge> outputEdges;

	public Vertex(int id) {
		this.id = id;
		this.inputEdges = new ArrayList<>();
		this.outputEdges = new ArrayList<>();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public ArrayList<Edge> getInputEdges() {
		return this.inputEdges;
	}

	public void addInputEdge(Edge e) {
		this.inputEdges.add(e);
	}

	public ArrayList<Edge> getOutputEdges() {
		return this.outputEdges;
	}

	public void addOutputEdge(Edge e) {
		this.outputEdges.add(e);
	}
}
