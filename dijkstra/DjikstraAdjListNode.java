/**
 * class to represent an entry in the adjacency list of a vertex
 * in a graph
 */
public class DjikstraAdjListNode {

    private String vertexNode;
    private int vertexIndex;
    // could be other fields, for example representing
    // properties of the edge - weight, capacity, . . .

    /* creates a new instance */
    public DjikstraAdjListNode(String v) {
        this.vertexNode = v;
    }

    public int getVertexIndex() {
        return vertexIndex;
    }

    public void setVertexIndex(int n) {
        vertexIndex = n;
    }

}
