import java.io.*;
import java.util.*;

/**
 * program to find word ladder with shortest distance for two words in a
 * dictionary
 * distance between elements of the word ladder is the absolute difference in
 * the
 * positions of the alphabet of the non-matching letter
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
        DijkstraGraph G1 = new DijkstraGraph(myWords);

        // do the work here
        List<DijkstraVertex> subset = new ArrayList<DijkstraVertex>(); // Initialisation
        for (DijkstraVertex vert : G1.getVertices()) {
            if (vert.getWord().equals(beginWord)) {
                vert.setDistance(0);
                subset.add(vert);
            }
        }

        for (DijkstraVertex vertex : subset.get(0).getAdjList())
            vertex.setDistance(vertex.distanceLetter(subset.get(0)));

        Queue<DijkstraVertex> queue = new LinkedList<DijkstraVertex>();
        DijkstraVertex begin = new DijkstraVertex(beginWord);
        begin.setDistance(0);
        begin.setVisited(true);
        queue.offer(begin);
        // Iterate over the queue as long as it is not empty
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                DijkstraVertex current_vertex = queue.poll();
                current_vertex.setVisited(true);
                for (DijkstraVertex vertex_inList : G1.getVertex(myWords.indexOf(current_vertex.getWord()))
                        .getAdjList()) {
                    if (!vertex_inList.getVisited()) {
                        if (current_vertex.getDistance()
                                + vertex_inList.distanceLetter(current_vertex) < vertex_inList.getDistance()) {
                            vertex_inList.setDistance(
                                    current_vertex.getDistance() + vertex_inList.distanceLetter(current_vertex));
                            vertex_inList.setPredecessor(current_vertex);
                        }
                    }
                    if (!vertex_inList.getVisited()) {
                        queue.offer(vertex_inList);
                    }
                    if (!subset.contains(vertex_inList)) {
                        subset.add(vertex_inList);
                    }
                }
            }
        }

        out: for (DijkstraVertex vertex : subset) {
            if (vertex.getWord().equals(endWord)) {
                System.out.println("Minimum path distance : " + vertex.getDistance());
                System.out.println("Path with minimum distance:");
                System.out.println(beginWord);
                LinkedList<String> path = new LinkedList<String>();
                while (vertex.getPredecessor() != null) {
                    path.addFirst(vertex.getPredecessor().getWord());
                    vertex = vertex.getPredecessor();
                }
                for (String w : path) {
                    System.out.println(w);
                }
                System.out.println(endWord);
                status = true;
                break out;
            }
        }
        if (!status) {
            System.out.println("No ladder possible between " + beginWord + " and " + endWord);
        }
        // end timer and print total time
        long end = System.currentTimeMillis();
        System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
    }
}
