import java.awt.Color;
import java.lang.Math;
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
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}