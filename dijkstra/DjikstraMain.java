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
        // List<String> djikstraWords = new ArrayList<String>();
        // Set<String> diffWords = new HashSet<String>();

        // int index = 0;
        boolean status = false;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            myWords.add(line);
            // diffWords.add(line);
            // index++;
        }
        // djikstraWords.add(beginWord);

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
        // DjikstraGraph G2 = new DjikstraGraph(djikstraWords);

        // initialise all distance to beginWord to Integer.MAX_VALUE (infinity)
        G1.initialWeights();

        reader.close();

        // do the work here

        List<DjikstraVertex> subset = new ArrayList<DjikstraVertex>(); // Initialisation
        for (DjikstraVertex vert : G1.vertices) {
            if (vert.word.equals(beginWord)) {
                vert.distance = 0;
                subset.add(vert);
            }
        }
        System.out.println("subset size : " + subset.size());

        // initialize distance as infinity for the non-beginWord neighbours
        for (DjikstraVertex vertex : subset.get(0).getAdjList()) {
            vertex.distance = vertex.distanceLetter(subset.get(0));
        }
        int round = 0;
        // Iterate over the queue as long as it is not empty
        outerloop: while (subset.size() != myWords.size()) {
            int size = subset.size();
            System.out.println("size : " + size);
            System.out.println("round : " + round);
            for (DjikstraVertex mot : subset)
                System.out.println(mot.word + " avec une distance vers beginWord de : " + mot.distance);

            // for (Vertex s : queue)
            // System.out.println("queue :" + s.word + " of size : " + size);
            // List<Integer> listMininimumWeight = new ArrayList<Integer>();
            // DjikstraVertex nearest_neighbor = subset.get(0);
            DjikstraVertex nearest_neighbor = new DjikstraVertex(null); // A REVOIR !!!
            int nearest_neighbor_dist = Integer.MAX_VALUE;
            G1.nonVisited(subset); // visited = false if vertex not in subset, otherwise visited = true
            // subset.get(0).visited = true;
            for (DjikstraVertex mot1 : subset)
                System.out.println("mot découvert : " + mot1.word + " sa visibilite : " + mot1.visited);
            for (int i = 0; i < size; i++) {
                DjikstraVertex current_vertex = subset.get(i);
                // System.out.println("current_vertex:" + current_vertex.word);
                // System.out.println(myWords);

                // System.out.println("current_vertex : " + current_vertex.word);
                // System.out.println("index : " + myWords.indexOf(current_vertex.word));
                // System.out.println(G1.getVertex(myWords.indexOf(current_vertex.word)));
                // System.out.println(G1.getVertex(myWords.indexOf(current_vertex.word)).getAdjList());
                // System.out.println();
                for (DjikstraVertex vertex_inList : G1.getVertex(myWords.indexOf(current_vertex.word)).getAdjList()) {
                    vertex_inList.setPredecessor(current_vertex);
                    // if (queue.contains(vertex_inList))
                    // continue;
                    System.out.println("word vertex in list, distance to current word : " + vertex_inList.word
                            + " distance :" + vertex_inList.distanceLetter(current_vertex));
                    System.out.println("visited ? " + vertex_inList.visited);
                    if (!vertex_inList.visited) {
                        if (current_vertex.distance
                                + vertex_inList.distanceLetter(current_vertex) < nearest_neighbor_dist) {
                            nearest_neighbor = vertex_inList;
                            nearest_neighbor_dist = current_vertex.distance
                                    + vertex_inList.distanceLetter(current_vertex);
                        }
                        // listMininimumWeight.add(vertex_inList.distance);
                    }
                    // System.out.println("vertex_inList : " + vertex_inList.word);

                    // if (/* !queue.contains(vertex_inList) &&
                    // */vertex_inList.word.equals(endWord)) {
                    // System.out.println("Minimum path distance : " + current_vertex.getDistance()
                    // + current_vertex.distanceLetter(new DjikstraVertex(endWord)));
                    // System.out.println("Path with minimum distance:");
                    // LinkedList<String> path = new LinkedList<String>();
                    // while (vertex_inList.getPredecessor() != null) {
                    // path.addFirst(vertex_inList.getPredecessor().word);
                    // vertex_inList = vertex_inList.getPredecessor();
                    // }
                    // for (String w : path) {
                    // System.out.println(w);
                    // }
                    // System.out.println(endWord);
                    // status = true;
                    // break outerloop;
                    // }
                    // vertex_inList.setVisited(true);
                    // queue.offer(vertex_inList);
                }
                System.out.println();
            }
            if (nearest_neighbor != null) {
                subset.add(nearest_neighbor);
                nearest_neighbor.distance = nearest_neighbor_dist;
            }
            if (nearest_neighbor == null) {
                break outerloop;
            }
            round++;
        }

        for (DjikstraVertex vertex : subset) {
            if (vertex.word.equals(endWord)) {
                System.out.println("Minimum path distance : " + vertex.distance);
                System.out.println("Path with minimum distance:");
                LinkedList<String> path = new LinkedList<String>();
                while (vertex.getPredecessor() != null) {
                    path.addFirst(vertex.getPredecessor().word);
                    vertex = vertex.getPredecessor();
                }
                for (String w : path) {
                    System.out.println(w);
                }
                System.out.println(endWord);
                status = true;
            }
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
