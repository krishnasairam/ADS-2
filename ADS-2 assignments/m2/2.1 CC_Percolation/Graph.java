/**
 * List of graphs.
 */
class Graph {
    /**
     * vertices.
     */
    private int vertices;
    /**
     * edges.
     */
    private int edge;
    /**
     * bag.
     */
    private Bag<Integer>[] adj;
    /**
     * Constructs the object.
     *
     * @param      v1    The v 1
     */
    public Graph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException(
                "Number of vertices must be nonnegative");
        }
        this.vertices = V;
        this.edge = 0;
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int v = 0; v < vertices; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
    /**
     * vertices.
     *
     * @return     { description_of_the_return_value }
     */
    public int vertices() {
        return this.vertices;
    }
    /**
     * number of edges.
     *
     * @return     { description_of_the_return_value }
     */
    public int edges() {
        return this.edge;
    }
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        edge++;
        adj[v].add(w);
        adj[w].add(v);
    }
    private void validateVertex(int v) {
        if (v < 0 || v >= vertices)
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (vertices - 1));
    }
    /**
     * iterable.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        for (int each : adj[w]) {
            if (each == v) {
                return true;
            }
        }
        return false;
    }
    /**
     * degree.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int degree(int v) {
        return adj[v].size();
    }
}
