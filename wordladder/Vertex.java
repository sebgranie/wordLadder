import java.util.LinkedList;

/**
 * class to represent a vertex in a graph
 */
public class Vertex {

    private LinkedList<Vertex> adjList; // the adjacency list
    private int index; // the index of this vertex in the graph
    public String word;

    // possibly other fields, for example representing data
    // stored at the node, whether the vertex has been visited
    // in a traversal, its predecessor in such a traversal, etc.

    boolean visited; // whether vertex has been visited in a traversal
    Vertex predecessor; // index of predecessor vertex in a traversal

    /**
     * creates a new instance of Vertex
     */
    public Vertex(String new_word) {
        adjList = new LinkedList<Vertex>();
        // int index = n;
        word = new_word;
        // System.out.println("word :" + word);
    }

    // /**
    // * copy constructor
    // */
    // public Vertex(Vertex v) {
    // adjList = v.getAdjList();
    // index = v.getIndex();
    // visited = v.getVisited();

    // }

    public LinkedList<Vertex> getAdjList() {
        return adjList;
    }

    public void display_adjList() {
        int count = 0;
        for (Vertex vert : adjList) {
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

    public Vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex name) {
        predecessor = name;
    }

    public void addToAdjList(Vertex newVertex) {
        // adjList.add(new AdjListNode(newVertex));
        adjList.add(newVertex);
    }

    public int vertexDegree() {
        return adjList.size();
    }

    public int distance(Vertex neighbour) {
        int diff = 0;
        // System.out.println(neighbour.word);
        // System.out.println(this.word);
        for (int i = 0; i < neighbour.word.length(); i++) {
            if (neighbour.word.charAt(i) != word.charAt(i))
                diff++;
        }
        return diff;
    }
}
