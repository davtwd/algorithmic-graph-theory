import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Main {

	// without format, just a name
	private static String file_name = "midi";

	public static void main(String[] args) {		
		int maxVertex = 0;
		String path = new String();
		ArrayList<Edge> edgeList = new ArrayList<>();
		ArrayList<Vertex> vertexList = new ArrayList<>();
		
		// .txt
		path = "./data/" + file_name + ".txt";

		try (Scanner scan = new Scanner(new File(path))) {
			System.out.println("Loading edge data...");
			while (scan.hasNextInt()) {
				
				int from = scan.nextInt();
				int to = scan.nextInt();
				int cost = scan.nextInt();

				edgeList.add(new Edge(from, to, cost));
				maxVertex = Math.max(maxVertex, ((from >= to) ? from : to));
			}
		} catch (Exception e) { throw new RuntimeException("File " + path + " not found"); }

		// .tim
		path = "./data/" + file_name + ".tim";
		ArrayList<Integer> temp = new ArrayList<>();

		temp.add(0);

		try (Scanner scan = new Scanner(new File(path))) {
			System.out.println("Loading vertex data...");
			while (scan.hasNextInt()) {
				temp.add(scan.nextInt());
			}
		} catch (Exception e) { throw new RuntimeException("File " + path + " not found"); }

		for (int i = 0; i < maxVertex + 1; i++) {
			Vertex v = new Vertex(i);
			if (i >= 0 && i < temp.size()) {
				v.setDuration(temp.get(i));
			}
			vertexList.add(v);
		}

		Graph graph = new Graph(edgeList, vertexList);
		graph.getCPM();
	}
}