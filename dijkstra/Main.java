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

        // Initialisation of the queue with its first element : beginWord
        Queue<DijkstraVertex> queue = new LinkedList<DijkstraVertex>();
        for (DijkstraVertex vert : G1.getVertices()) {
            if (vert.getWord().equals(beginWord)) {
                vert.setDistance(0);
                vert.setVisited(true);
                queue.offer(vert);
            }
        }
        // Initialise weights to the first neighbours of beginWord
        for (DijkstraVertex vertex : queue.element().getAdjList())
            vertex.setDistance(vertex.distanceLetter(queue.element()));
        // Iterate over the queue as long as it is not empty
        outerloop: while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                DijkstraVertex current_vertex = queue.poll();
                // we set visited = true only when the vertex is added into the
                // queue to avoid considering any edges related to it that might
                // be interesting to consider because it has already been processed
                current_vertex.setVisited(true);
                // iteration among current_vertex's neighbours
                for (DijkstraVertex vertex_inList : G1.getVertex(myWords.indexOf(current_vertex.getWord()))
                        .getAdjList()) {
                    // if we found the endWord
                    if (!vertex_inList.getVisited() && vertex_inList.getWord().equals(endWord)) {
                        vertex_inList.setPredecessor(current_vertex);
                        vertex_inList.setDistance(
                                current_vertex.getDistance() + vertex_inList.distanceLetter(current_vertex));
                        System.out.println("Minimum path distance : " + vertex_inList.getDistance());
                        System.out.println("Path with minimum distance:");
                        LinkedList<String> path = new LinkedList<String>();
                        while (vertex_inList.getPredecessor() != null) {
                            path.addFirst(vertex_inList.getPredecessor().getWord());
                            vertex_inList = vertex_inList.getPredecessor();
                        }
                        for (String w : path)
                            System.out.println(w);
                        System.out.print(endWord);
                        status = true; // Optimal wordladder found
                        break outerloop;
                    }
                    // We figure out if we have found a new optimal path, update : distance &
                    // predecessor if yes
                    if (!vertex_inList.getVisited()) {
                        if (current_vertex.getDistance()
                                + vertex_inList.distanceLetter(current_vertex) < vertex_inList.getDistance()) {
                            vertex_inList.setDistance(
                                    current_vertex.getDistance() + vertex_inList.distanceLetter(current_vertex));
                            vertex_inList.setPredecessor(current_vertex);
                        }
                        // add the vertex to the queue to visit its neighbours and
                        // make the algorithm continue its research to find the endWord
                        queue.offer(vertex_inList);
                    }
                }
            }
        }
        // The variable status is only set to true when we find a ladder between
        // beginWord and endWord
        if (!status) {
            System.out.print("No ladder possible between " + beginWord + " and " + endWord);
        }
        // end timer and print total time
        long end = System.currentTimeMillis();
        System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
    }
}
