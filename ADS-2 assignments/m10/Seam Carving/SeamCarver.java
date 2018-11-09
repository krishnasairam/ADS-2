import java.awt.Color;
import java.lang.Math;
import java.util.Arrays;
public class SeamCarver {
	/**
	 * picture.
	 */
	private Picture picture;
	/**
	 * width.
	 */
	private int width;
	/**
	 * height.
	 */
	private int height;
	/**
	 * energy 2d array.
	 */
	private double energy[][];
	/**
	 * distTO
	 */
	private double[][] distTo;
	/**
	 * distToSink.
	 */
    private double distToSink;
    private int[][] edgeTo;
    private int edgeToSink;
    private boolean transposed;
	/**
	 * Constructs the object.
	 *
	 * @param      picture  The picture
	 */
	public SeamCarver(Picture picture) {
		this.picture = picture;
		this.width = picture.width();
		this.height = picture.height();
		this.energy = new double[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height ; j++) {
				if (i == 0 || j == 0 || i == width - 1 || j == height - 1) {
					energy[i][j] = 1000.0;
				} else {
					energy[i][j] = gradient(i, j);
				}
			}
		}
	}
	public double gradient(int i, int j) {
		int x_gradient = x_gradient(i, j);
		int y_gradient = y_gradient(i, j);
		double en = Math.sqrt(x_gradient + y_gradient);
		return en;
	}
	public int x_gradient(int i, int j) {
		Color color1 = picture.get(i - 1, j);
		Color color2 = picture.get(i + 1, j);
		int red = color1.getRed() - color2.getRed();
		int green = color1.getGreen() - color2.getGreen();
		int blue = color1.getBlue() - color2.getBlue();
		int x_gradient = (red * red) + (green * green) + (blue * blue);
		return x_gradient;
	}
	public int y_gradient(int i, int j) {
		Color color1 = picture.get(i, j - 1);
		Color color2 = picture.get(i, j + 1);
		int red = color1.getRed() - color2.getRed();
		int green = color1.getGreen() - color2.getGreen();
		int blue = color1.getBlue() - color2.getBlue();
		int y_gradient = (red * red) + (green * green) + (blue * blue);
		return y_gradient;
	}
	// current picture
	public Picture picture() {
		return picture;
	}
	// width of current picture
	public int width() {
		return width;
	}

	// height of current picture
	public int height() {
		return height;
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		return energy[x][y];
	}
	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}
	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		transposed = false;
        distToSink = Double.POSITIVE_INFINITY;
        edgeToSink = Integer.MAX_VALUE;
        distTo = new double[width][height];
        edgeTo = new int[width][height];
        for (double[] r: distTo) {
        	Arrays.fill(r, Double.POSITIVE_INFINITY);
        }
        for (int[] r: edgeTo) {
        	Arrays.fill(r, Integer.MAX_VALUE);
        }

        // Relax the entire top row, since this is our starting row
        Arrays.fill(distTo[0], (double) 1000);
        Arrays.fill(edgeTo[0], -1);

        // Visit all pixels from the top, diagonally to the right,
        // in keeping with topological order.
        // The topological order is the reverse of the DFS post-order,
        // which visits the left-most adjacent pixel first, before it visits
        // pixels to the right.
        for (int top = width() - 1; top >= 0; top--) {
            for (int depth = 0;
                    depth + top < width() && depth < height();
                    depth++) {
                visit(depth, depth + top);
            }
        }
        // Visit all pixels from the left side, diagonally to the right,
        // in keeping widthth the topological order described above.
        for (int depth = 1; depth < height(); depth++) {
            for (int out = 0;
                    out < width() && depth + out < height();
                    out++) {
                visit(depth + out, out);
            }
        }

        // Populate seam[] widthth the shortest path
        int[] seam = new int[width()];
        seam[height() - 1] = edgeToSink;

        for (int i = height() - 1; i > 0; i--) {
            seam[i - 1] = edgeTo[i][seam[i]];
        }

        // null out our shortest-path arrays for garbage collection
        distTo = null;
        edgeTo = null;

        return seam;
    }
	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
	private void visit(int i, int j) {
        if (transposed) {
            // Only relax the sink
            if (j == width() - 1) {
                relax(i, j);
            }

            // Bottom edge; relax to the right and above
            else if (i == height() - 1) {
                relax(i, j, i, j + 1);
                relax(i, j, i - 1, j + 1);
            }

            // Top edge; relax to the right and below
            else if (i == 0) {
                relax(i, j, i, j + 1);
                relax(i, j, i + 1, j + 1);
            }

            // Middle pixel; relax right, below, and above
            else {
                relax(i, j, i - 1, j + 1);
                relax(i, j, i, j + 1);
                relax(i, j, i + 1, j + 1);
            }
        }

        else {
            // Only relax the sink
            if (i == height() - 1) {
                relax(i, j);
            }

            // Right edge; relax below and to the left
            else if (j == width() - 1) {
                relax(i, j, i + 1, j - 1);
                relax(i, j, i + 1, j);
            }

            // Left edge; relax below and to the right
            else if (j == 0) {
                relax(i, j, i + 1, j);
                relax(i, j, i + 1, j + 1);
            }

            // Middle pixel; relax left, below, and right
            else {
                relax(i, j, i + 1, j - 1);
                relax(i, j, i + 1, j);
                relax(i, j, i + 1, j + 1);
            }
        }
    }

    /**
     * Given an index, relax the sink vertex from that index.
     *
     * This method should only be called on the "last" vertices in the image.
     *
     * @param i the vertical index of the pixel
     * @param j the horizontal index of the pixel
     */
    private void relax(int i, int j) {
        //if (validIndex(i, j)) {
            if (distToSink > distTo[i][j]) {
                distToSink = distTo[i][j];
                if (transposed) {
                	edgeToSink = i;
                } else {
                	edgeToSink = j;
                }
            }
        //}
    }
    private void relax(int i1, int j1, int i2, int j2) {
        //if (validIndex(i1, j1) && validIndex(i2, j2)) {
            if (distTo[i2][j2] > distTo[i1][j1] + energy[i2][j2]) {
                distTo[i2][j2] = distTo[i1][j1] + energy[i2][j2];
                if (transposed) {
                	edgeTo[i2][j2] = i1;
                } else {
                	edgeTo[i2][j2] = j1;
                }
            }
        //}
    }
}