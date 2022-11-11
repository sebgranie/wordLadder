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

        boolean status = false;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            myWords.add(line);
        }

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

        reader.close();

        // do the work here

        List<DjikstraVertex> subset = new ArrayList<DjikstraVertex>(); // Initialisation
        for (DjikstraVertex vert : G1.vertices) {
            if (vert.word.equals(beginWord)) {
                vert.distance = 0;
                subset.add(vert);
            }
        }

        for (DjikstraVertex vertex : subset.get(0).getAdjList()) {
            vertex.distance = vertex.distanceLetter(subset.get(0));
        }
        Queue<DjikstraVertex> queue = new LinkedList<DjikstraVertex>();
        DjikstraVertex begin = new DjikstraVertex(beginWord);
        begin.distance = 0;
        begin.visited = true;
        queue.offer(begin);
        // Iterate over the queue as long as it is not empty
        outerloop: while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                DjikstraVertex current_vertex = queue.poll();
                current_vertex.visited = true;
                for (DjikstraVertex vertex_inList : G1.getVertex(myWords.indexOf(current_vertex.word)).getAdjList()) {
                    if (!vertex_inList.visited) {
                        if (current_vertex.distance
                                + vertex_inList.distanceLetter(current_vertex) < vertex_inList.distance) {
                            vertex_inList.distance = current_vertex.distance
                                    + vertex_inList.distanceLetter(current_vertex);
                            vertex_inList.setPredecessor(current_vertex);
                        }
                    }
                    if (!vertex_inList.visited) {
                        queue.offer(vertex_inList);
                    }
                    if (!subset.contains(vertex_inList)) {
                        subset.add(vertex_inList);
                    }
                }
            }
        }

        out: for (DjikstraVertex vertex : subset) {
            if (vertex.word.equals(endWord)) {
                System.out.println("Minimum path distance : " + vertex.distance);
                System.out.println("Path with minimum distance:");
                System.out.println(beginWord);
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
