import java.util.List;

/**
 * class to represent an undirected graph using adjacency lists
 */
public class DjikstraGraph {

    public DjikstraVertex[] vertices; // the (array of) vertices
    private int numVertices = 0; // number of vertices

    // possibly other fields representing properties of the graph

    /**
     * creates a new instance of Graph with n vertices
     */
    public DjikstraGraph(List<String> myWords) {
        int array_size = myWords.size(); // je sauvegarde la taille du set initial
        vertices = new DjikstraVertex[array_size]; // Initialisation de l'Array vertices
        for (int i = 0; i < array_size; i++) { // itère sur la taille du set
            vertices[i] = new DjikstraVertex(myWords.get(i)); // je crée le vertex correspondant à chaque mot du set
            numVertices++;
            for (int j = 0; j < i; j++) { // je parcours ma liste de vertex à chaque ajout de nouveau mot
                if (vertices[i].distance(vertices[j]) == 1) { // verif distance == 1
                    vertices[i].addToAdjList(vertices[j]); // j'ajoute chaque liaison des mots qui verif condition
                    vertices[i].addToIndexList(vertices[i].distanceLetter(vertices[j]));
                    if (!vertices[j].getAdjList().contains(vertices[i])) {
                        vertices[j].addToAdjList(vertices[i]);
                        vertices[j].addToIndexList(vertices[i].distanceLetter(vertices[j]));
                    }
                }
            }
        }
    }

    public int size() {
        return numVertices;
    }

    public DjikstraVertex getVertex(int n) {
        return vertices[n];
    }

    public void setVertex(String word, int i) {
        vertices[i] = new DjikstraVertex(word);
    }

    public void display_vertex() {
        for (DjikstraVertex vertex : vertices) {
            System.out.println("Word : " + vertex.word);
            vertex.display_adjList();
        }
    }
}
