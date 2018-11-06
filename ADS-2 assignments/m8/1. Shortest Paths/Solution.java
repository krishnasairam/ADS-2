import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
  String[] root;
  /**
   * Constructs the object.
   */
  private Solution() {
    // unused
  }

  private int getIndex(String str) {
    int index = 0;
    for (int i = 0; i < root.length; i++) {
      if (str == root[i]) {
        index = i;
      }
    }
    return index;
  }


  /**
   * Main method.
   *
   * @param      args  The arguments
   */
  public static void main(final String[] args) {
    Solution sol = new Solution();
    Scanner scan = new Scanner(System.in);
    int vertices = scan.nextInt();
    int edges = scan.nextInt();
    scan.nextLine();
    String[] tokens = scan.nextLine().split(" ");
    for(int j = 0; j<tokens.length; j++){
      sol.root[j] = tokens[j];
    }
    EdgeWeightedDigraph eg = new EdgeWeightedDigraph(vertices);
    while (edges > 0) {
      String[] token = scan.nextLine().split(" ");
      int first = sol.getIndex(token[0]);
      int second = sol.getIndex(token[1]);
      int weight = Integer.parseInt(token[2]);
      DirectedEdge e = new DirectedEdge(first, second, weight);
      eg.addEdge(e);
      System.out.println(eg);
      edges--;
    }
    int i = Integer.parseInt(scan.nextLine());
    while (i > 0) {
      String[] tokens1 = scan.nextLine().split(" ");
      int home = sol.getIndex(tokens1[0]);
      DijkstraSP l = new DijkstraSP(eg, home);
      int destination = sol.getIndex(tokens1[1]);
      System.out.printf("%f\n", l.distTo(destination));
      i--;
    }
  }
}
