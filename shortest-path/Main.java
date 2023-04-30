import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Main {

	private static final int FROM = 0;
	private static final int TO = 1;

	private static int[] vertex = {1, 10}; // path {from, to} - can be more experimental e.g. 28, 128000

	public static void main(String[] args) {
		
		int maxVertex = 0;
		ArrayList<Edge> edgeList = new ArrayList<>();
		String path = "./data/florida.txt";
		
		try (Scanner scan = new Scanner(new File(path))) {
			System.out.println("Loading graph data...");
			while (scan.hasNextInt()) {
				
				int from = scan.nextInt();
				int to = scan.nextInt();
				int cost = scan.nextInt();

				edgeList.add(new Edge(from, to, cost));
				maxVertex = Math.max(maxVertex, ((from >= to) ? from : to));
			}
		} catch (Exception e) { throw new RuntimeException("File " + path + " not found"); }

		Graph graph = new Graph(edgeList, maxVertex);
		graph.findShortestPath(vertex[FROM], vertex[TO]);
	}

}