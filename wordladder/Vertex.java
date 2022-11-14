import java.util.LinkedList;

// class to represent a vertex in a graph
public class Vertex {

    private LinkedList<Vertex> adjList; // the adjacency list
    private int index; // the index of this vertex in the vertices list
    private String word; // name of the Vertex
    private boolean visited; // whether Vertex has been visited to avoid infinity loops
    private Vertex predecessor; // refers to the optimal predecessor Vertex (shortest path)

    // creates a new instance of Vertex
    public Vertex(String new_word) {
        adjList = new LinkedList<Vertex>();
        word = new_word;
    }

    // return the adjacency list of a Vertex (list of neighbours)
    public LinkedList<Vertex> getAdjList() {
        return adjList;
    }

    // display all the neighbours (Vertex) of a Vertex
    public void display_adjList() {
        int count = 0;
        for (Vertex vert : adjList) {
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
    public Vertex getPredecessor() {
        return predecessor;
    }

    // setter the build iteratively the best path in the main function
    public void setPredecessor(Vertex name) {
        predecessor = name;
    }

    // add a vertex to the adjacency list (add new neighbours)
    public void addToAdjList(Vertex newVertex) {
        adjList.add(newVertex);
    }

    // get the number of neighbours of a vertex
    public int vertexDegree() {
        return adjList.size();
    }

    // computes the distance between two words (number of char that differs)
    public int distance(Vertex neighbour) {
        int diff = 0;
        for (int i = 0; i < neighbour.getWord().length(); i++) {
            if (neighbour.getWord().charAt(i) != word.charAt(i))
                diff++;
        }
        return diff;
    }
}
