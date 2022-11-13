import java.util.LinkedList;
import java.lang.Math;

/* class to represent a DijkstraVertex in a graph */
public class DijkstraVertex {

    private LinkedList<DijkstraVertex> adjList; // the adjacency list
    private int index; // index of the vertex in the vertices list
    private String word; // string name of the vertex
    private int distance = Integer.MAX_VALUE; // distance to beginWord with shortes path
    private boolean visited = false; // whether DijkstraVertex has been visited to avoid infinity loops
    private DijkstraVertex predecessor; // refers to the optimal predecessor Vertex (shortest path)

    // creates a new instance of DijkstraVertex
    public DijkstraVertex(String new_word) {
        adjList = new LinkedList<DijkstraVertex>();
        word = new_word;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int n) {
        distance = n;
    }

    public LinkedList<DijkstraVertex> getAdjList() {
        return adjList;
    }

    public void display_adjList() {
        int count = 0;
        for (DijkstraVertex vert : adjList) {
            System.out.println("--> Node " + count + " : " + vert.getWord());
            count++;
        }
    }

    public String getWord() {
        return word;
    }

    public void setWord(String w) {
        word = w;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int n) {
        index = n;
    }

    public boolean getVisited() {
        return visited;
    }

    public void setVisited(boolean b) {
        visited = b;
    }

    public DijkstraVertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(DijkstraVertex name) {
        predecessor = name;
    }

    public void addToAdjList(DijkstraVertex newVertex) {
        adjList.add(newVertex);
    }

    public int vertexDegree() {
        return adjList.size();
    }

    public int distance(DijkstraVertex neighbour) {
        int diff = 0;
        for (int i = 0; i < neighbour.getWord().length(); i++) {
            if (neighbour.getWord().charAt(i) != word.charAt(i))
                diff++;
        }
        return diff;
    }

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
