import java.util.Scanner;
/**
* Class for solution.
*/
public class Solution {
	/**
	* Constructs the object.
	*/
	private Solution() {
		// unused
	}
	/**
	 * main function.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		int s = edges;
		EdgeWeightedDigraph eg = new EdgeWeightedDigraph(vertices);
		while (s > 0) {
			String[] values = scan.nextLine().split(" ");
			int v1 = Integer.parseInt(values[0]);
			int v2 = Integer.parseInt(values[1]);
			int distance = Integer.parseInt(values[2]);
			DirectedEdge e = new DirectedEdge(v1, v2, distance);
            eg.addEdge(e);
			s--;
		}
		String caseToGo = scan.nextLine();
		switch (caseToGo) {
		case "Graph":
			System.out.println(eg);
			break;

		case "DirectedPaths":
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			String[] places = scan.nextLine().split(" ");
			DijkstraSP sp = new DijkstraSP(eg, Integer.parseInt(places[0]));
			System.out.printf("%d\n", (int) sp.distTo(Integer.parseInt(places[1])));
			break;

		case "ViaPaths":
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		default:
			break;
		}

	}
}