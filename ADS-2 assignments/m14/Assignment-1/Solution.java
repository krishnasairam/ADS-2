import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {

  /**
   * Constructs the object.
   */

  private Solution() {
    /**
     * Unused.
     */
  }
  /**
   * Main Function.
   * time complexity is O(n)
   * @param      args  The arguments
   */
  public static void main(final String[] args) {
    Scanner sc = new Scanner(System.in);
    String prefix = sc.nextLine();
    In in = new In("/Files/dictionary-algs4.txt");
    String[] words = in.readAllStrings();
    TST symbolTable = new TST();
    for (int i = 0; i < words.length; i++) {
      for (int j = 0; j < words[i].length(); j++) {
        symbolTable.put(words[i].substring(j), i);
      }
    }
    Iterable<String> ss = symbolTable.keysWithPrefix(prefix);
    for (String s:ss) {
      System.out.println(s);
    }
  }
}



