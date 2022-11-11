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
		// int index = 0;
		boolean status = false;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			myWords.add(line);
			// index++;
		}

		// int initial_array_size = myWords.size();
		System.out.println("Size of the dictionary :" + myWords.size());
		System.out.println("BeginWord :" + beginWord);
		System.out.println("EndWord :" + endWord);

		/*
		 * If the target word does not exist - Possible to delete these lines as it is
		 * mentioned in the coursework that beginWord and endWord will be in the
		 * dictionnary.
		 */
		if (!myWords.contains(beginWord))
			throw new RuntimeException(beginWord + " is not in word list");
		if (!myWords.contains(endWord))
			throw new RuntimeException(endWord + " is not in word list");

		// create graph here
		Graph G = new Graph(myWords);

		// G.display_vertex();

		reader.close();

		// do the work here

		/*
		 * One fois que le graphe est crée et qu'il est constitué de tableaux de
		 * tableaux
		 * En realité le tableau principal comporte tous les noeuds (mots)
		 * Tous les sous-tableaux constituent tous les liens que chaque noeud possède
		 *
		 * On initialise une : Queue<String> queue = new LinkedList<String>()
		 * On y ajoute le Beginword : queue.offer(beginWord)
		 * declare un integer correspondant à la distance entre beginword et endword
		 * (qui sera incrémenté à chaque tour d'itération de la queue)
		 * Tant que la queue n'est pas vide :
		 * On itère sur ses éléments :
		 * On enlève le mot en tête de queue
		 * On itère sur ses voisins de graphes --> sur sa ajdList
		 * Recursion jusqu'à que le endword soit présent dans une des linkedList de
		 * suite de voisins ??
		 * (a chaque étape, on visite les voisins des voisins en accédant aux linkedList
		 *
		 *
		 */

		// Initialisation
		Queue<Vertex> queue = new LinkedList<Vertex>();
		queue.offer(new Vertex(beginWord));
		int level = 1;

		// Iterate over the queue as long as it is not empty
		outerloop: while (!queue.isEmpty()) {
			int size = queue.size();
			// for (Vertex s : queue)
			// System.out.println("queue :" + s.word + " of size : " + size);
			for (int i = 0; i < size; i++) {
				Vertex current_vertex = queue.poll();
				// System.out.println("current_vertex:" + current_vertex.word);
				for (Vertex vertex_inList : G.getVertex(myWords.indexOf(current_vertex.word)).getAdjList()) {
					if (vertex_inList.visited)
						continue;
					// System.out.println("vertex_inList : " + vertex_inList.word);
					vertex_inList.setPredecessor(current_vertex);
					if (!vertex_inList.visited && vertex_inList.word.equals(endWord)) {
						level++;
						System.out.println("Minimum path distance : " + level);
						System.out.println("Path with minimum distance:");
						LinkedList<String> path = new LinkedList<String>();
						while (vertex_inList.getPredecessor() != null) {
							path.addFirst(vertex_inList.getPredecessor().word);
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
		// return 0;
	}
}
