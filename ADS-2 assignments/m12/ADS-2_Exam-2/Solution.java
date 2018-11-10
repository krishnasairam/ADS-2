import java.util.Scanner;
/**
* Class for solution.
*/
public final class Solution {
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
        EdgeWeightedGraph eg = new EdgeWeightedGraph(vertices);
        while (s > 0) {
            String[] values = scan.nextLine().split(" ");
            int v1 = Integer.parseInt(values[0]);
            int v2 = Integer.parseInt(values[1]);
            int distance = Integer.parseInt(values[2]);
            Edge e = new Edge(v1, v2, distance);
            eg.addEdge(e);
            s--;
        }
        String caseToGo = scan.nextLine();
        switch (caseToGo) {
        case "Graph":
            System.out.println(eg);
            break;

        case "DirectedPaths":
            // Handle the case of DirectedPaths,
            // where two integers are given.
            // First is the source and second is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] places = scan.nextLine().split(" ");
            Shortestpath sp = new Shortestpath(eg, Integer.
                parseInt(places[0]));
            double result = sp.distTo(Integer.parseInt(places[1]));
            if (result == Double.POSITIVE_INFINITY) {
                System.out.println("No Path Found.");
            } else {
                System.out.println(result);
            }
            break;

        case "ViaPaths":
            // Handle the case of ViaPaths,
            // where three integers are given.
            // First is the source and second is the via is the
            // one where path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] place = scan.nextLine().split(" ");
            Shortestpath sp1 = new Shortestpath(eg, Integer.
                parseInt(place[0]));
            Shortestpath sp2 = new Shortestpath(eg, Integer.
                parseInt(place[1]));
            double r1 = sp1.distTo(Integer.parseInt(place[1]));
            double r2 = sp2.distTo(Integer.parseInt(place[2]));
            String str = place[0] + " ";
            if (r1 == Double.POSITIVE_INFINITY || r2 == Double.
                POSITIVE_INFINITY) {
                System.out.println("No Path Found.");
            } else {
                System.out.println(r1 + r2);
                for (Edge e1 : sp1.pathTo(Integer.parseInt(place[1]))) {
                    str += e1.either() + " ";
                }
                int i = 0;
                for (Edge e2 : sp2.pathTo(Integer.parseInt(place[2]))) {
                    int temp = e2.either();
                    if (i % 2 == 0) {
                    str += e2.other(temp) + " ";
                } else {
                    str += temp + " ";
                }
                i++;
                }
                System.out.println(str);
            }
            break;

        default:
            break;
        }

    }
}
