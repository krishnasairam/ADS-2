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
		//if (v != w && !hasEdge(v, w)) {
		E++;
		adj[v].add(w);
		adj[w].add(v);
		//}
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
	public String display(String[] data) {
		String s = "";
		s += V + " vertices, " + E + " edges" + '\n';
		for (int v = 0; v < V; v++) {
			s += data[v] + ": ";
			for (int w : adj[v]) {
				s += data[w] + " ";
			}
			s += '\n';
		}
		return s.substring(0, s.length() - 1);
	}

}
class GraphMatrix implements Graph {
	private int V;
	private int E;
	private int[][] matrix;
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
	public Iterable<Integer> adj(int v) {
		return null;
	}
	public int V() {
		return this.V;
	}
	public int E() {
		return this.E;
	}
	public void addEdge(int v, int w) {
		matrix[v][w] = 1;
		matrix[w][v] = 1;
		E++;
	}
	public boolean hasEdge(int v, int w) {
		return false;
	}
	public String toString() {
		String s = "";
		s += V + " vertices, " + E + " edges " + '\n';
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				s += matrix[i][j] + " ";
			}
			s = s.substring(0, s.length() - 1);
			s += ('\n');
		}

		return s.substring(0, s.length() -  1);
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
				String[] fun = scan.nextLine().split(" ");
				list.addEdge(Integer.parseInt(fun[0]), Integer.parseInt(fun[1]));
				temp--;
			}
			System.out.println(list.display(data));
		} else if (s.equals("Matrix")) {
			temp = e;
			GraphMatrix sol = new GraphMatrix(v);
			while (temp > 0) {
				int p = scan.nextInt();
				int q = scan.nextInt();
				scan.nextLine();
				sol.addEdge(p, q);
				temp--;
			}
			System.out.println(sol);
		}
	}
}



