import java.util.List;

// class to represent an undirected graph using adjacency lists
public class Graph {

	private Vertex[] vertices; // the (array of) vertices
	private int numVertices = 0; // number of vertices

	// creates a new instance of Graph with n vertices
	public Graph(List<String> myWords) {
		int array_size = myWords.size();
		vertices = new Vertex[array_size];
		for (int i = 0; i < array_size; i++) {
			vertices[i] = new Vertex(myWords.get(i));
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

	public int size() {
		return numVertices;
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public Vertex getVertex(int n) {
		return vertices[n];
	}

	public void setVertex(String word, int i) {
		vertices[i] = new Vertex(word);
	}

	public void display_vertex() {
		for (Vertex vertex : vertices) {
			System.out.println("Word : " + vertex.getWord());
			vertex.display_adjList();
		}
	}
}
