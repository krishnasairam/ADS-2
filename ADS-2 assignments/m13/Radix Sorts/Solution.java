import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //Constructor.
    }
    /**
     * main class.
     *
     * @param args  The arguments.
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = scan.nextLine();
        }
        Quick3way.sort(strings);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int j = 0; j < n - 1; j++) {
            sb.append(strings[j]);
            sb.append(", ");
        }
        sb.append(strings[n - 1]);
        sb.append("]");
        System.out.println(sb.toString());

    }
}
