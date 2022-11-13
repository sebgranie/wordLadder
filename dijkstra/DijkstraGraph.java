import java.util.List;

/**
 * class to represent an undirected graph using adjacency lists
 */
public class DijkstraGraph {

    private DijkstraVertex[] vertices; // the (array of) vertices
    private int numVertices = 0; // number of vertices

    // creates a new instance of Graph with n vertices
    public DijkstraGraph(List<String> myWords) {
        int array_size = myWords.size(); // je sauvegarde la taille du set initial
        vertices = new DijkstraVertex[array_size]; // Initialisation de l'Array vertices
        for (int i = 0; i < array_size; i++) { // itère sur la taille du set
            vertices[i] = new DijkstraVertex(myWords.get(i)); // je crée le vertex correspondant à chaque mot du set
            numVertices++;
            for (int j = 0; j < i; j++) { // je parcours ma liste de vertex à chaque ajout de nouveau mot
                if (vertices[i].distance(vertices[j]) == 1) { // verif distance == 1
                    vertices[i].addToAdjList(vertices[j]); // j'ajoute chaque liaison des mots qui verif condition
                    if (!vertices[j].getAdjList().contains(vertices[i])) {
                        vertices[j].addToAdjList(vertices[i]);
                    }
                }
            }
        }
    }

    public int size() {
        return numVertices;
    }

    public DijkstraVertex[] getVertices() {
        return vertices;
    }

    public DijkstraVertex getVertex(int n) {
        return vertices[n];
    }

    public void setVertex(String word, int i) {
        vertices[i] = new DijkstraVertex(word);
    }

    public void display_vertex() {
        for (DijkstraVertex vertex : vertices) {
            System.out.println("Word : " + vertex.getWord());
            vertex.display_adjList();
        }
    }
}
