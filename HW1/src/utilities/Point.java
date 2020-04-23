package utilities;

public class Point {
	private final int x_min = 0;

	public static int getX_min() {
		return x_min;
	}

	public int getX_max() {
		return x_max;
	}

	public int getY_min() {
		return y_min;
	}

	public int getY_max() {
		return y_max;
	}

	private final int x_max = 1000000;
	private final int y_min = 0;
	private final int y_max = 800;
	private double x;
	private double y;

	public Point(double x, double y) {
		if (x > this.x_min && x < this.x_max) {
			this.x = x;
		}
		if (y > this.y_min && y < this.y_max) {
			this.y = y;
		}
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		return String.format("X=%.2f Y=%.2f", this.x, this.y);
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Point) {
			return this.x == ((Point) other).getX() && this.y == ((Point) other).getY();
		}
		return false;
	}

}
