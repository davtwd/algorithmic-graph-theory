import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {		
		int maxVertex = 0;
		ArrayList<Edge> edgeList = new ArrayList<>();
		String path = "./data/mini2.txt";
		
		try (Scanner scan = new Scanner(new File(path))) {
			System.out.println("Loading graph data...");
			while (scan.hasNextInt()) {

				int from = scan.nextInt();
				int to = scan.nextInt();
				int cost = scan.nextInt();
				int capacity = scan.nextInt();

				edgeList.add(new Edge(from, to, cost, capacity));
				maxVertex = Math.max(maxVertex, ((from >= to) ? from : to));
			}
		} catch (Exception e) { throw new RuntimeException("File " + path + " not found"); }

		Graph graph = new Graph(edgeList, maxVertex);
		graph.getMaxFlow(1, maxVertex);
	}
}