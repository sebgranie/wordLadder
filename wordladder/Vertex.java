import java.util.LinkedList;

// class to represent a vertex in a graph
public class Vertex {

    private LinkedList<Vertex> adjList; // the adjacency list
    private int index; // the index of this vertex in the graph
    private String word; // name of the Vertex
    private boolean visited; // whether vertex has been visited in a traversal
    private Vertex predecessor; // index of predecessor vertex in a traversal

    // creates a new instance of Vertex
    public Vertex(String new_word) {
        adjList = new LinkedList<Vertex>();
        word = new_word;
    }

    public LinkedList<Vertex> getAdjList() {
        return adjList;
    }

    public void display_adjList() {
        int count = 0;
        for (Vertex vert : adjList) {
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

    public Vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex name) {
        predecessor = name;
    }

    public void addToAdjList(Vertex newVertex) {
        adjList.add(newVertex);
    }

    public int vertexDegree() {
        return adjList.size();
    }

    public int distance(Vertex neighbour) {
        int diff = 0;
        for (int i = 0; i < neighbour.getWord().length(); i++) {
            if (neighbour.getWord().charAt(i) != word.charAt(i))
                diff++;
        }
        return diff;
    }
}
