import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;
interface Graph {
	public int V();
	public int E();
	public void addEdge(int v, int w);
	public Iterable<Integer> adj(int v);
	public boolean hasEdge(int v, int w);
}
class GraphList implements Graph {
	private int V;
	private int E;
	private Bag<Integer>[] adj;
	GraphList(int V1) {
		this.V = V1;
		this.E = 0;
		this.adj = (Bag<Integer>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}
	}
	public int V() {
		return this.V;
	}
	public int E() {
		return this.E;
	}
	public void addEdge(int v, int w) {
		if (v != w && !hasEdge(v, w)) {
			E++;
			adj[v].add(w);
			adj[w].add(v);
		}
	}
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	public boolean hasEdge(int v, int w) {
		for (int each : adj[v]) {
			if (each != w) {
				return false;
			}
		}
		return true;
	}
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + '\n');
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj[v]) {
				s.append(w + " ");
			}
			s.append('\n');
		}
		return s.toString();
	}

}
class GraphMatrix implements Graph {
	private int V;
	private int E;
	private boolean[][] matrix;
	GraphMatrix(int V1) {
		this.V = V1;
		this.E = 0;
		this.matrix = new boolean[V][V];
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				matrix[i][j] = false;
			}
		}
	}
	public int V() {
		return this.V;
	}
	public int E() {
		return this.E;
	}
	public void addEdge(int v, int w) {
		matrix[v][w] = true;
		matrix[w][v] = true;
		E++;
	}
	public Iterable<Integer> adj(int v) {
		return new AdjIterator(v);
	}
	private class AdjIterator implements Iterator<Integer>, Iterable<Integer> {
		private int v;
		private int w = 0;

		AdjIterator(int v) {
			this.v = v;
		}

		public Iterator<Integer> iterator() {
			return this;
		}

		public boolean hasNext() {
			while (w < V) {
				if (matrix[v][w]) return true;
				w++;
			}
			return false;
		}

		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return w++;
		}

		public void remove()  {
			throw new UnsupportedOperationException();
		}
	}
	public boolean hasEdge(int v, int w) {
		return false;
	}
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + '\n');
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj(v)) {
				s.append(w + " ");
			}
			s.append('\n');
		}
		return s.toString();
	}
}
/**
 * Class for solution.
 */
public final class Solution {
	public Solution() {
	}
	/**
	 * { function_description }.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();
		int v = Integer.parseInt(scan.nextLine());
		int e = Integer.parseInt(scan.nextLine());
		int temp = e;
		String[] data = scan.nextLine().split(",");
		if (s.equals("List")) {
			GraphList list = new GraphList(v);
			while (temp > 0) {
				int p = scan.nextInt();
				int q = scan.nextInt();
				scan.nextLine();
				list.addEdge(p, q);
				temp--;
				System.out.println(list);
			}
		} else if (s.equals("Matrix")) {
			temp = e;
			GraphMatrix sol = new GraphMatrix(v);
			while (temp < 0) {
				int p = scan.nextInt();
				int q = scan.nextInt();
				scan.nextLine();
				sol.addEdge(p, q);
				temp--;
				System.out.println(sol);
			}
		}
	}
}



