import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Graph {
	
	 private int vertices;
	 static private int sumOfPaths;
	 static private int numberOfPaths;
	 static private HashMap<String,ArrayList<GraphEdge>> adjacencyList;

	public Graph(int vertices) {
		this.vertices = vertices;
		adjacencyList = new HashMap<>();
		for(int i = 0; i < vertices; i++) {
			adjacencyList.put(String.valueOf((char)(i+65)), new ArrayList<>());
		}
	}
	
	public void addEgde(String source, String destination, int weight) {
		GraphEdge edge = new GraphEdge(source, destination, weight);
	    adjacencyList.get(source).add(edge); //for directed graph
	}


	public void calculateAverageDistanceBetweenTwoPoints(String source, String destination,int curPathSum,boolean[] visited, List<String> PathList1) {
		if (source.equals(destination)) {
			System.out.println(PathList1 + " " + curPathSum);
			sumOfPaths += curPathSum;
			numberOfPaths++;
			return;
		}

		int sIndex = (int)source.charAt(0)-65;
		visited[sIndex] = true;
		for (GraphEdge e : adjacencyList.get(source)) {
			int dindex = (int)e.destination.charAt(0)-65;
			if (!visited[dindex]) {
				PathList1.add(e.destination);
				curPathSum += e.weight;
				calculateAverageDistanceBetweenTwoPoints(e.destination, destination,curPathSum,visited,PathList1);
				PathList1.remove(PathList1.size()-1);
				curPathSum -= e.weight;
			}
		}
		visited[sIndex] = false;
		
	}

	public void printPaths(String source, String destination)
	{
		boolean[] visited = new boolean[vertices];
		ArrayList<String> pathList = new ArrayList<>();
		pathList.add(source);
		calculateAverageDistanceBetweenTwoPoints(source, destination,0,visited, pathList);
	}

	public static void main(String[] args) {
		 Graph graph = new Graph(5);
		 graph.addEgde("A", "D", 11);
		 graph.addEgde("A", "E", 8);
		 graph.addEgde("A", "C", 13);
		 graph.addEgde("A", "B", 12);
		 graph.addEgde("D", "A", 11);
		 graph.addEgde("D", "E", 7);
		 graph.addEgde("E", "D", 7);
		 graph.addEgde("E", "A", 8);
		 graph.addEgde("E", "C", 4);
		 graph.addEgde("C", "E", 4);
		 graph.addEgde("C", "A", 13);
		 graph.addEgde("C", "B", 3);
		 graph.addEgde("B", "C", 3);
		 graph.addEgde("B", "A", 12);
		 
		 Scanner sc = new Scanner(System.in);
		 System.out.println("Enter source node:(A-E) ");
		 String source = sc.nextLine();
		 System.out.println("Enter destination node:(A:E) ");
		 String destination = sc.nextLine();
		 
		 System.out.println("Following are all possible paths from " + source + " to " + destination);
		 graph.printPaths(source, destination);

		 System.out.println(sumOfPaths + " " + numberOfPaths);
		 double average = (double) sumOfPaths/numberOfPaths;
		 System.out.println("Average Distance Between " + source + " and " + destination + " is: " + average);
		
	}
 
}