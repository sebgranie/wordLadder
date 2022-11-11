import java.util.LinkedList;
import java.lang.Math;

/**
 * class to represent a DjikstraVertex in a graph
 */
public class DjikstraVertex {

    private LinkedList<DjikstraVertex> adjList; // the adjacency list
    private LinkedList<Integer> indexList; // list of distanceLetter from his neighbour in the adjacency list
    private int index; // index of the vertex in the vertices list
    public String word; // string name of the vertex
    public int distance = Integer.MAX_VALUE; // distance to beginWord with shortes path

    // possibly other fields, for example representing data
    // stored at the node, whether the DjikstraVertex has been visited
    // in a traversal, its predecessor in such a traversal, etc.

    boolean visited = false; // whether DjikstraVertex has been visited in a traversal
    DjikstraVertex predecessor; // index of predecessor DjikstraVertex in a traversal

    /**
     * creates a new instance of DjikstraVertex
     */
    public DjikstraVertex(String new_word) {
        adjList = new LinkedList<DjikstraVertex>();
        indexList = new LinkedList<Integer>();
        word = new_word;
    }

    public int getDistance() {
        return distance;
    }

    public LinkedList<DjikstraVertex> getAdjList() {
        return adjList;
    }

    public void display_adjList() {
        int count = 0;
        for (DjikstraVertex vert : adjList) {
            System.out.println("--> Node " + count + " : " + vert.word);
            count++;
        }
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

    public DjikstraVertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(DjikstraVertex name) {
        predecessor = name;
    }

    public void addToAdjList(DjikstraVertex newVertex) {
        adjList.add(newVertex);
    }

    public void addToIndexList(int index) {
        indexList.add(index);
    }

    public int vertexDegree() {
        return adjList.size();
    }

    public int distance(DjikstraVertex neighbour) {
        int diff = 0;
        for (int i = 0; i < neighbour.word.length(); i++) {
            if (neighbour.word.charAt(i) != word.charAt(i))
                diff++;
        }
        return diff;
    }

    public int distanceLetter(DjikstraVertex neigh) {
        int dist = 0;
        for (int j = 0; j < neigh.word.length(); j++) {
            if (neigh.word.charAt(j) != word.charAt(j)) {
                dist = Math.abs(
                        Character.getNumericValue(neigh.word.charAt(j)) - Character.getNumericValue(word.charAt(j)));
            }
        }
        return dist;
    }

}
