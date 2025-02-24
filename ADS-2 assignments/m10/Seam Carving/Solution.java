import java.util.Scanner;
import java.util.Arrays;
/**
* class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     *the method to print the energies in.
     * grid format.
     * time complexity is O(W * H)
     * W is width and H is height
     * @param      fileName  The file name
     */
    public static void printEnergies(final String fileName) {
        Picture picture = new Picture(fileName);
        StdOut.printf("image is %d pixels wide by %d pixels high.\n",
         picture.width(), picture.height());
        try {
        SeamCarver sc = new SeamCarver(picture);

        StdOut.printf("Printing energy calculated for each pixel.\n");
        for (int row = 0; row < sc.height(); row++) {
            for (int col = 0; col < sc.width(); col++) {
                StdOut.printf("%9.0f ", sc.energy(col, row));
            }
            StdOut.println();
        }
         } catch (Exception e) {
        System.out.println();
        }
    }
    /**
     *the method is to print the seamline.
     * time complexity is O(W * H)
     * W is width and H is height
     * @param      carver     The carver
     * @param      seam       The seam
     * @param      direction  The direction
     */
    public static void printSeam(final SeamCarver carver,
     final int[] seam, final boolean direction) {
        double totalSeamEnergy = 0.0;
        for (int row = 0; row < carver.height(); row++) {
            for (int col = 0; col < carver.width(); col++) {
                double energy = carver.energy(col, row);
                String marker = " ";
                if ((direction && row == seam[col])
                    || (!direction && col == seam[row])) {
                    marker = "*";
                    totalSeamEnergy += energy;
                }
                StdOut.printf("%7.2f%s ", energy, marker);
            }
            StdOut.println();
        }
        StdOut.printf("Total energy = %f\n", totalSeamEnergy);
        StdOut.println();
        StdOut.println();
    }
    /**
     *the main method to read the input.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();
        SeamCarver seamCarver = null;
        if (cases.length() == 0) {
            System.out.println("picture is null");
            return;
        }
        try {
            switch (cases) {
            case "width":
                while (scan.hasNextLine()) {
                    String file = scan.nextLine();
                    seamCarver = new SeamCarver(
                        new Picture("/Files/" + file));
                    System.out.println(seamCarver.width());
                }
                break;
            case "height":
                while (scan.hasNextLine()) {
                    String file = scan.nextLine();
                    seamCarver = new SeamCarver(
                        new Picture("/Files/" + file));
                    System.out.println(seamCarver.height());
                }
                break;
            case "energy":
                while (scan.hasNextLine()) {
                    String file = scan.nextLine();
                    printEnergies("/Files/" + file);
                }
                break;
            case "findVerticalSeam":
                while (scan.hasNextLine()) {
                    String file = scan.nextLine();
                    seamCarver = new SeamCarver(
                        new Picture("/Files/" + file));
                    System.out.println(Arrays.toString(
                        seamCarver.findVerticalSeam()));
                }
                break;
            case "findHorizontalSeam":
                while (scan.hasNextLine()) {
                    String file = scan.nextLine();
                    seamCarver = new SeamCarver(
                        new Picture("/Files/" + file));
                    System.out.println(Arrays.toString(
                        seamCarver.findHorizontalSeam()));
                }
                break;
            case "removeVerticalSeam":
                while (scan.hasNextLine()) {
                    String file = scan.nextLine();
                    seamCarver = new SeamCarver(
                        new Picture("/Files/" + file));
                    int[] verticalSeam = seamCarver.findVerticalSeam();
                    seamCarver.removeVerticalSeam(verticalSeam);
                    printSeam(seamCarver, verticalSeam, false);
                }
                break;
            case "removeHorizontalSeam":
                while (scan.hasNextLine()) {
                    String file = scan.nextLine();
                    seamCarver = new SeamCarver(
                        new Picture("/Files/" + file));
                    seamCarver.removeHorizontalSeam(
                        seamCarver.findHorizontalSeam());
                    int[] horizontalSeam = seamCarver.findHorizontalSeam();
                    seamCarver.removeHorizontalSeam(horizontalSeam);
                    printSeam(seamCarver, horizontalSeam, true);
                }
                break;
            case "removeHorizontalSeam removeVerticalSeam":
                while (scan.hasNextLine()) {
                    String file = scan.nextLine();
                    seamCarver = new SeamCarver(
                        new Picture("/Files/" + file));
                    int[] horizontalSeam = seamCarver.findHorizontalSeam();
                    seamCarver.removeHorizontalSeam(horizontalSeam);
                    int[] verticalSeam = seamCarver.findVerticalSeam();
                    seamCarver.removeVerticalSeam(verticalSeam);
                    printEnergies("/Files/" + file);
                }
                break;
            default:
                seamCarver = new SeamCarver(null);
                break;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}



