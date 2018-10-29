/**
 * List of graphs.
 */
class GraphList implements Graph {
	/**
	 * { var_description }
	 */
	private int V;
	/**
	 * { var_description }
	 */
	private int E;
	/**
	 * { var_description }
	 */
	private Bag<Integer>[] adj;
	/**
	 * Constructs the object.
	 *
	 * @param      V1    The v 1
	 */
	GraphList(int V1) {
		this.V = V1;
		this.E = 0;
		this.adj = (Bag<Integer>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}
	}
	/**
	 * vertices.
	 *
	 * @return     { description_of_the_return_value }
	 */
	public int V() {
		return this.V;
	}
	/**
	 * number of edges.
	 *
	 * @return     { description_of_the_return_value }
	 */
	public int E() {
		return this.E;
	}
	/**
	 * Adds an edge.
	 *
	 * @param      v     { parameter_description }
	 * @param      w     { parameter_description }
	 */
	public void addEdge(int v, int w) {
		if (v == w) {
			return;
		}
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}
	/**
	 * iterable.
	 *
	 * @param      v     { parameter_description }
	 *
	 * @return     { description_of_the_return_value }
	 */
	public Iterable<Integer> adj(int v) {
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
		int count = 0;
		for (int i : adj[v]) {
			if (i == w) {
				count += 1;
				break;
			}
		}
		for (int i : adj[w]) {
			if (i == v) {
				count += 1;
				break;
			}
		}
		if (count == 2) {
			return true;
		}
		return false;
	}
	/**
	 * display.
	 *
	 * @param      data  The data
	 *
	 * @return     { description_of_the_return_value }
	 */
	public String display(String[] data) {
		String s = "";
		s += V + " vertices, " + E + " edges" + '\n';
		if (E == 0) {
			s += "No edges ";
		} else {
			for (int v = 0; v < V; v++) {
				s += data[v] + ": ";
				for (int w : adj[v]) {
					s += data[w] + " ";
				}
				s += '\n';
			}
		}
		return s.substring(0, s.length() - 1);
	}

}
/**
 * Class for graph matrix.
 */
class GraphMatrix implements Graph {
	/**
	 * number of vertices.
	 */
	private int V;
	/**
	 * number of edges.
	 */
	private int E;
	/**
	 * matrix.
	 */
	private int[][] matrix;
	/**
	 * Constructs the object.
	 *
	 * @param      V1    The v 1
	 */
	GraphMatrix(int V1) {
		this.V = V1;
		this.E = 0;
		this.matrix = new int[V][V];
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				matrix[i][j] = 0;
			}
		}
	}
	/**
	 * Iterable.
	 *
	 * @param      v     { parameter_description }
	 *
	 * @return     { description_of_the_return_value }
	 */
	public Iterable<Integer> adj(int v) {
		return null;
	}
	/**
	 * number of vertices.
	 *
	 * @return     { description_of_the_return_value }
	 */
	public int V() {
		return this.V;
	}
	/**
	 * number of edges.
	 *
	 * @return     { description_of_the_return_value }
	 */
	public int E() {
		return this.E;
	}
	/**
	 * Adds an edge.
	 *
	 * @param      v     { parameter_description }
	 * @param      w     { parameter_description }
	 */
	public void addEdge(int v, int w) {
		if (!hasEdge(v, w) && v != w) {
			E++;
		}
		matrix[v][w] = 1;
		matrix[w][v] = 1;
	}
	/**
	 * Determines if it has edge.
	 *
	 * @param      v     { parameter_description }
	 * @param      w     { parameter_description }
	 *
	 * @return     True if has edge, False otherwise.
	 */
	public boolean hasEdge(int v, int w) {
		return (matrix[v][w] == 1);
	}
	/**
	 * Returns a string representation of the object.
	 *
	 * @return     String representation of the object.
	 */
	public String toString() {
		String s = "";
		s += V + " vertices, " + E + " edges" + '\n';
		if (E == 0) {
			s += "No edges ";
		} else {
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					s += matrix[i][j] + " ";
				}
				s = s.substring(0, s.length());
				s += ('\n');
			}
		}

		return s.substring(0, s.length() -  1);
	}
}