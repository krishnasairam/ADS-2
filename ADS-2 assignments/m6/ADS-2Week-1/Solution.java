import java.util.Scanner;
/**
 * Class for page rank.
 */
class PageRank {
	/**
	 * Digraph.
	 */
	private Digraph dg;
	/**
	 * integer array of ranks.
	 */
	private double[] rank;
	/**
	 * number of vertices.
	 */
	private int v;
	/**
	 * Constructs the object.
	 *
	 * @param      g     { parameter_description }
	 */
	PageRank(Digraph g) {
		this.dg = g;
		this.v = dg.V();
		rank = new double[v];
		double f = (double) v;
		for (int i = 0; i < v; i++) {
			rank[i] = 1 / f;
		}
		int temp = 1000;
		while (temp >= 0) {
			for (int k = 0; k < v; k++) {
				Queue c = dg.Connected(k);
				int t = c.size();
				if (t == 0) {
					rank[k] = 0;
				}
				double t1 = 0;
				while (t > 0) {
					int a = (int) c.dequeue();
						t1 += rank[a] / (double) dg.outdegree(a);
					t--;
				}
				rank[k] = t1;
			}
			temp--;
		}
	}
	/**
	 * Returns a string representation of the object.
	 *
	 * @return     String representation of the object.
	 */
	public String toString() {
		String s = "";
		for (int b = 0; b < v; b++) {
			s += b + " - " + rank[b] + "\n";
		}
		return s;
	}
}
/**
 * Class for web search.
 */
class WebSearch {
	WebSearch(PageRank p, String f) {

	}
}
/**
 * Class for solution.
 */
public class Solution {
	private Solution() {

	}
	/**
	 * { function_description }
	 *
	 * @param      args  The arguments
	 */
	public static void main(String[] args) {
		// read the first line of the input to get the number of vertices
		Scanner scan = new Scanner(System.in);
		int v = Integer.parseInt(scan.nextLine());
		// iterate count of vertices times
		Digraph g = new Digraph(v);
		while (v > 0) {
			String[] s = scan.nextLine().split(" ");
			for (int i = 1; i < s.length; i++) {
				g.addEdge(Integer.parseInt(s[0]), Integer.parseInt(s[i]));
			}
			v--;
		}
		// to read the adjacency list from std input
		// and build the graph
		PageRank p = new PageRank(g);

		// Create page rank object and pass the graph object to the constructor
		System.out.println(g);
		System.out.println(p);
		// print the page rank object

		// This part is only for the final test case

		// File path to the web content
		String file = "WebContent.txt";
		WebSearch w = new WebSearch(p, file);
		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}
