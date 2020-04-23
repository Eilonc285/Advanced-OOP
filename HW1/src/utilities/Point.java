package utilities;

public class Point {
	private final int x_min = 0;
	private final int x_max = 1000000;
	private final int y_min = 0;
	private final int y_max = 800;
	private double x;
	private double y;

	public int getX_min() {
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

	public Point(double x, double y) {
		if (x >= this.x_min && x <= this.x_max) {
			this.x = x;
		} else {
			this.x = Math.random() * 1000000;
			System.out.printf("The value %f is illegal forX, therefore has been replaced with %f\n", x, this.x);
		}
		if (y >= this.y_min && y <= this.y_max) {
			this.y = y;
		} else {
			this.y = Math.random() * 800;
			System.out.printf("The value %f is illegal forY, therefore has been replaced with %f\n", y, this.y);
		}
		System.out.printf("Point (%f , %f) has been created\n", this.x, this.y);
	}

	public void setX(double x) {
		if (x >= this.x_min && x <= this.x_max) {
			this.x = x;
		} else {
			System.out.printf("The value %f is illegal for x\n", x);
		}
	}

	public void setY(double y) {
		if (y >= this.y_min && y <= this.y_max) {
			this.y = y;
		} else {
			System.out.printf("The value %f is illegal for y\n", y);
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		return String.format("(%f , %f)\n", this.x, this.y);
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Point) {
			return this.x == ((Point) other).getX() && this.y == ((Point) other).getY();
		}
		return false;
	}

}
