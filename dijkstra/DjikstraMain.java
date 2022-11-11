import java.io.*;
import java.util.*;

/**
 * program to find word ladder with shortest distance for two words in a
 * dictionary
 * distance between elements of the word ladder is the absolute difference in
 * the
 * positions of the alphabet of the non-matching letter
 */
public class DjikstraMain {

    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        String inputFileName = args[0]; // dictionary
        String beginWord = args[1]; // first word
        String endWord = args[2]; // second word

        FileReader reader = new FileReader(inputFileName);
        Scanner scanner = new Scanner(reader);

        // read in the data here
        List<String> myWords = new ArrayList<String>();
        List<String> djikstraWords = new ArrayList<String>();
        // Set<String> diffWords = new HashSet<String>();

        // int index = 0;
        boolean status = false;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            myWords.add(line);
            // diffWords.add(line);
            // index++;
        }
        djikstraWords.add(beginWord);

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
        DjikstraGraph G1 = new DjikstraGraph(myWords);
        DjikstraGraph G2 = new DjikstraGraph(djikstraWords);

        reader.close();

        // do the work here

        List<DjikstraVertex> subset = new ArrayList<DjikstraVertex>(); // Initialisation
        subset.add(new DjikstraVertex(beginWord));
        // initialise all distance to beginWord to Integer.MAX_VALUE (infinity)
        G1.initialWeights();
        // initialize distance as infinity for the non-beginWord neighbours
        for (DjikstraVertex vertex : subset.get(0).getAdjList()) {
            vertex.distance = vertex.distanceLetter(subset.get(0));
        }

        // Iterate over the queue as long as it is not empty
        outerloop: while (subset.size() != myWords.size()) {
            int size = subset.size();
            // for (Vertex s : queue)
            // System.out.println("queue :" + s.word + " of size : " + size);
            // List<Integer> listMininimumWeight = new ArrayList<Integer>();
            DjikstraVertex nearest_neighbor = new DjikstraVertex(null); // A REVOIR !!!
            // DjikstraVertex nearest_neighbor = subset.get(0);
            int nearest_neighbor_dist = Integer.MAX_VALUE;
            G1.nonVisited();
            for (int i = 0; i < size; i++) {
                DjikstraVertex current_vertex = subset.get(i);
                // System.out.println("current_vertex:" + current_vertex.word);
                for (DjikstraVertex vertex_inList : G1.getVertex(myWords.indexOf(current_vertex.word)).getAdjList()) {
                    // if (queue.contains(vertex_inList))
                    // continue;
                    if (!vertex_inList.visited) {
                        if (vertex_inList.distance < nearest_neighbor_dist) {
                            nearest_neighbor = vertex_inList;
                            nearest_neighbor_dist = vertex_inList.distance;
                        }
                        // listMininimumWeight.add(vertex_inList.distance);
                    }
                    // System.out.println("vertex_inList : " + vertex_inList.word);
                    vertex_inList.setPredecessor(current_vertex);
                    if (/* !queue.contains(vertex_inList) && */vertex_inList.word.equals(endWord)) {
                        System.out.println("Minimum path distance : " + current_vertex.getDistance()
                                + current_vertex.distanceLetter(new DjikstraVertex(endWord)));
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
                    // queue.offer(vertex_inList);
                }
            }
            subset.add(nearest_neighbor);
        }
        if (!status) {
            // level = 0;
            System.out.println("No ladder possible between " + beginWord + " and " + endWord);
        }

        // end timer and print total time
        long end = System.currentTimeMillis();
        System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
    }

}
