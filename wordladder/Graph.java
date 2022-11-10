import java.util.LinkedList;
import java.util.List;

/**
 * class to represent an undirected graph using adjacency lists
 */
public class Graph {

	public Vertex[] vertices; // the (array of) vertices
	// public int[] vertices_index;
	private int numVertices = 0; // number of vertices
	// private Set<String> set = new HashSet<String>();

	// possibly other fields representing properties of the graph

	/**
	 * creates a new instance of Graph with n vertices
	 */
	public Graph(List<String> myWords) {
		int array_size = myWords.size(); // je sauvegarde la taille du set initial
		vertices = new Vertex[array_size]; // Initialisation de l'Array vertices
		// vertices_index = new int[array_size];
		for (int i = 0; i < array_size; i++) { // itère sur la taille du set
			vertices[i] = new Vertex(myWords.get(i)); // je crée le vertex correspondant à chaque mot du set
			// vertices_index[i] = i;
			numVertices++;
			for (int j = 0; j < i; j++) { // je parcours ma liste de vertex à chaque ajout de nouveau mot
				if (vertices[i].distance(vertices[j]) == 1) { // verif distance == 1
					vertices[i].addToAdjList(vertices[j]); // j'ajoute chaque liaison des mots qui verif condition
					// for (Vertex v : vertices[j].getAdjList()){
					// if (v.word.equals(vertices[i]))
					// }
					if (!vertices[j].getAdjList().contains(vertices[i]))
						vertices[j].addToAdjList(vertices[i]);
				}
			}
		}
	}

	public int size() {
		return numVertices;
	}

	public Vertex getVertex(int n) {
		return vertices[n];
	}

	public void setVertex(String word, int i) {
		vertices[i] = new Vertex(word);
	}

	public void display_vertex() {
		for (Vertex vertex : vertices) {
			System.out.println("Word : " + vertex.word);
			vertex.display_adjList();
		}
	}

	// public void addVertex(String word) {

	// }

	/**
	 * visit vertex v, with predecessor index p,
	 * during a depth first traversal of the graph
	 */
	// private void Visit(Vertex v, int p) {
	// v.setVisited(true);
	// v.setPredecessor(p);
	// LinkedList<Vertex> L = v.getAdjList();
	// for (Vertex node : L) {
	// int n = node.getVertexIndex();
	// if (!vertices[n].getVisited()) {
	// Visit(vertices[n], v.getIndex());
	// }
	// }
	// }

	/**
	 * carry out a depth first search/traversal of the graph
	 */
	// public void dfs() {
	// for (Vertex v : vertices)
	// v.setVisited(false);
	// for (Vertex v : vertices)
	// if (!v.getVisited())
	// Visit(v, -1);
	// }

	/**
	 * carry out a breadth first search/traversal of the graph
	 */
	// public void bfs() {

	// for (Vertex v : vertices)
	// v.setVisited(false); // initialise (all vertices unvisted)
	// LinkedList<Vertex> queue = new LinkedList<Vertex>(); // queue to process

	// for (Vertex v : vertices) { // go through vertices of the graph
	// if (!v.getVisited()) { // if vertex not visited then visit the vertex
	// v.setVisited(true);
	// v.setPredecessor(v.getIndex()); // v was initial/starting vertex
	// queue.add(v); // add to queue for processing
	// while (!queue.isEmpty()) {
	// Vertex u = queue.remove(); // get next vertex to process
	// LinkedList<Vertex> list = u.getAdjList(); // get adjacency list of the vertex
	// for (Vertex node : list) {
	// Vertex w = vertices[node.getVertexIndex()];
	// if (!w.getVisited()) { // if vertex has not been visited
	// w.setVisited(true);
	// w.setPredecessor(u.getIndex()); // w was found using u so this is the
	// predecessor
	// queue.add(w); // add to queue for processing
	// }
	// }
	// }
	// }
	// }
	// }

}
