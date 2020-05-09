package utilities;

/**
 * Point class to reference a point in the two dimensional space within a fixed
 * range.
 * 
 * @author Eilon
 *
 */
public abstract class Point implements Utilities {
	private final int minVal = 0;
	private final int maxX = 800;
	private final int maxY = 600;
	private double x;
	private double y;

	/**
	 * Constructor creates a point at a random location within the allowed range.
	 */
	public Point() {
		this.x = Math.random() * maxX;
		this.y = Math.random() * maxY;
	}

	/**
	 * Constructor creates a point on the given x and y coordinates.
	 * 
	 * @param x: The x coordinate.
	 * @param y: The y coordinate.
	 */
	public Point(double x, double y) {
		if (this.checkValue(x, this.minVal, this.maxX)) {
			this.x = x;
		} else {
			this.x = Math.random() * maxX;
		}
		if (this.checkValue(y, this.minVal, this.maxY)) {
			this.y = y;
		} else {
			this.y = Math.random() * maxY;
		}
	}

	/**
	 * Returns the x coordinate of this point.
	 * 
	 * @return: Double of the x coordinate.
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the x coordinate of this point.
	 * 
	 * @param x: The double to set as x.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Returns the y coordinate of this point.
	 * 
	 * @return: Double of the y coordinate.
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the y coordinate of this point.
	 * 
	 * @param y: The double to set as y.
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Returns the minimum value allowed for a coordinate.
	 * 
	 * @return: The int of the minimum value.
	 */
	public int getMinVal() {
		return minVal;
	}

	/**
	 * Returns the maximum value the x coordinate can hold.
	 * 
	 * @return: The maximum int possible for the x coordinate.
	 */
	public int getMaxX() {
		return maxX;
	}

	/**
	 * Returns the maximum value the y coordinate can hold.
	 * 
	 * @return: The maximum int possible for the y coordinate.
	 */
	public int getMaxY() {
		return maxY;
	}

	/**
	 * Used to determine if this object is equal to another.
	 *
	 * @param other: The other object to be compared to.
	 * @return: Boolean of if the objects equal each other.
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Point) {
			return this.x == ((Point) other).getX() && this.y == ((Point) other).getY();
		}
		return false;
	}

	/**
	 * Returns a string representation of this object.
	 * 
	 * @return: The String representation.
	 */
	@Override
	public String toString() {
		return String.format("Point (%f, %f)", this.x, this.y);
	}

	/**
	 * Calculates the distance between this point and another point.
	 * 
	 * @param other: The other point to which to calculate the distance.
	 * @return: The distance double between the points.
	 */
	public double calcDistance(Point other) {
		return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
	}

}
