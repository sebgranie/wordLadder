import java.util.List;

/**
 * class to represent an undirected graph using adjacency lists
 */
public class DijkstraGraph {

    private DijkstraVertex[] vertices; // the (array of) vertices
    private int numVertices = 0; // number of vertices

    // creates a new instance of Graph with n vertices
    public DijkstraGraph(List<String> myWords) {
        int array_size = myWords.size();
        vertices = new DijkstraVertex[array_size];
        for (int i = 0; i < array_size; i++) {
            vertices[i] = new DijkstraVertex(myWords.get(i));
            vertices[i].setIndex(i);
            numVertices++;
            for (int j = 0; j < i; j++) {
                // if distance == 1 ; add as neighbour
                if (vertices[i].distance(vertices[j]) == 1) {
                    vertices[i].addToAdjList(vertices[j]);
                    // Make sure the neighbourhood is in both directions
                    if (!vertices[j].getAdjList().contains(vertices[i])) {
                        vertices[j].addToAdjList(vertices[i]);
                    }
                }
            }
        }
    }

    // getter to the numvertices attribute
    public int size() {
        return numVertices;
    }

    // getter to the vertices attribute
    public DijkstraVertex[] getVertices() {
        return vertices;
    }

    // getter to a specific vertex list of neighbours using index
    public DijkstraVertex getVertex(int n) {
        return vertices[n];
    }

    // setter to add a vertex and its index in vertices
    public void setVertex(String word, int i) {
        vertices[i] = new DijkstraVertex(word);
    }

    // display all the vertex of a graph
    public void display_vertex() {
        for (DijkstraVertex vertex : vertices) {
            System.out.println("Word : " + vertex.getWord());
            vertex.display_adjList();
        }
    }
}
