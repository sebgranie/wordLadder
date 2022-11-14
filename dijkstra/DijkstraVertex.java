import java.util.LinkedList;
import java.lang.Math;

/* class to represent a DijkstraVertex in a graph */
public class DijkstraVertex {

    private LinkedList<DijkstraVertex> adjList; // the adjacency list
    private int index; // index of the vertex in the vertices list
    private String word; // name of the vertex
    private int distance = Integer.MAX_VALUE; // distance to beginWord with shortes path
    private boolean visited = false; // whether DijkstraVertex has been visited to avoid infinity loops
    private DijkstraVertex predecessor; // refers to the optimal predecessor Vertex (shortest path)

    // creates a new instance of DijkstraVertex
    public DijkstraVertex(String new_word) {
        adjList = new LinkedList<DijkstraVertex>();
        word = new_word;
    }

    // getter to retrieve the distance attribute of a Vertex
    public int getDistance() {
        return distance;
    }

    // setter for the distance of a Vertex to the beginWord
    public void setDistance(int n) {
        distance = n;
    }

    // return the adjacency list of a Vertex (list of neighbours)
    public LinkedList<DijkstraVertex> getAdjList() {
        return adjList;
    }

    // display all the neighbours (Vertex) of a Vertex
    public void display_adjList() {
        int count = 0;
        for (DijkstraVertex vert : adjList) {
            System.out.println("--> Node " + count + " : " + vert.getWord());
            count++;
        }
    }

    // getter to access the word of a Vertex
    public String getWord() {
        return word;
    }

    // setter for the name of a Vertex
    public void setWord(String w) {
        word = w;
    }

    // getter to access the index of a vertex in vertices List
    public int getIndex() {
        return index;
    }

    // setter to set this index (during graph construction)
    public void setIndex(int n) {
        index = n;
    }

    // getter to the visited attribute
    public boolean getVisited() {
        return visited;
    }

    // setter to set the visibility of a word
    public void setVisited(boolean b) {
        visited = b;
    }

    // getter to access the predecessor Vertex to retrieve the best path
    public DijkstraVertex getPredecessor() {
        return predecessor;
    }

    // setter the build iteratively the best path in the main function
    public void setPredecessor(DijkstraVertex name) {
        predecessor = name;
    }

    // add a vertex to the adjacency list (add new neighbours)
    public void addToAdjList(DijkstraVertex newVertex) {
        adjList.add(newVertex);
    }

    // get the number of neighbours of a vertex
    public int vertexDegree() {
        return adjList.size();
    }

    // computes the distance between two words (number of char that differs)
    public int distance(DijkstraVertex neighbour) {
        int diff = 0;
        for (int i = 0; i < neighbour.getWord().length(); i++) {
            if (neighbour.getWord().charAt(i) != word.charAt(i))
                diff++;
        }
        return diff;
    }

    // computes the distance between two neighbours (distance in the alphabet)
    public int distanceLetter(DijkstraVertex neigh) {
        int dist = 0;
        for (int j = 0; j < neigh.getWord().length(); j++) {
            if (neigh.getWord().charAt(j) != word.charAt(j)) {
                dist = Math.abs(
                        Character.getNumericValue(neigh.getWord().charAt(j))
                                - Character.getNumericValue(word.charAt(j)));
            }
        }
        return dist;
    }
}
