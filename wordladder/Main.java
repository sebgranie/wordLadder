import java.io.*;
import java.util.*;

/**
 * program to find word ladder with shortest path (i.e. minimum number edges
 */
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();

		String inputFileName = args[0]; // dictionary
		String beginWord = args[1]; // first word
		String endWord = args[2]; // second word

		FileReader reader = new FileReader(inputFileName);
		Scanner scanner = new Scanner(reader);

		// read in the data here
		List<String> myWords = new ArrayList<String>();
		boolean status = false;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			myWords.add(line);
		}
		reader.close();

		System.out.println("Size of the dictionary :" + myWords.size());
		System.out.println("BeginWord :" + beginWord);
		System.out.println("EndWord :" + endWord);

		// If the target word does not exist - Lines to build a robust program.
		if (!myWords.contains(beginWord))
			throw new RuntimeException(beginWord + " is not in word list");
		if (!myWords.contains(endWord))
			throw new RuntimeException(endWord + " is not in word list");

		// create graph here
		Graph G = new Graph(myWords);

		// Initialisation of the queue to perform BFS
		Queue<Vertex> queue = new LinkedList<Vertex>();
		queue.offer(new Vertex(beginWord));
		int level = 1;

		// Iterate over the queue as long as it is not empty
		outerloop: while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Vertex current_vertex = queue.poll();
				for (Vertex vertex_inList : G.getVertex(myWords.indexOf(current_vertex.getWord())).getAdjList()) {
					if (vertex_inList.getVisited())
						continue;
					vertex_inList.setPredecessor(current_vertex);
					if (!vertex_inList.getVisited() && vertex_inList.getWord().equals(endWord)) {
						level++;
						System.out.println("Minimum path distance : " + level);
						System.out.println("Path with minimum distance:");
						LinkedList<String> path = new LinkedList<String>();
						while (vertex_inList.getPredecessor() != null) {
							path.addFirst(vertex_inList.getPredecessor().getWord());
							vertex_inList = vertex_inList.getPredecessor();
						}
						for (String w : path) {
							System.out.println(w);
						}
						System.out.println(endWord);
						status = true;
						break outerloop;
					}
					vertex_inList.setVisited(true);
					queue.offer(vertex_inList);
				}
			}
			level++;
		}
		if (!status) {
			level = 0;
			System.out.println("No ladder possible between " + beginWord + " and " + endWord);
		}
		// end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}
}
