package utilities;

public abstract class Point implements Utilities {
	private final int minVal = 0;
	private final int maxX = 800;
	private final int maxY = 600;
	private double x;
	private double y;

	public Point() {
		this.x = Math.random() * 800;
		this.y = Math.random() * 600;
	}

	public Point(double x, double y) {
		if (this.checkValue(x, this.minVal, this.maxX)) {
			this.x = x;
		} else {
			this.x = this.getRandomInt(this.minVal, this.maxX);
		}
		if (this.checkValue(y, this.minVal, this.maxY)) {
			this.y = y;
		} else {
			this.y = this.getRandomInt(this.minVal, this.maxY);
		}
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getMinVal() {
		return minVal;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Point) {
			return this.x == ((Point) other).getX() && this.y == ((Point) other).getY();
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("(%f, %f)", this.x, this.y);
	}

	public double calcDistance(Point other) {
		return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
	}

}
