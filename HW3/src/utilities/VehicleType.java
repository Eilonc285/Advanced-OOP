package utilities;

/**
 * An enum for all the available vehicle types and their average speed.
 * 
 * @author Eilon
 *
 */
public enum VehicleType {
	car(90), bus(60), bicycle(40), motorcycle(120), truck(80), tram(50), semitrailer(85);
	private int averageSpeed;

	/**
	 * Sets the given integer as the vehicle's average speed.
	 * 
	 * @param speed: The average speed of the vehicle.
	 */
	VehicleType(int speed) {
		averageSpeed = speed;
	}

	/**
	 * Gets the average speed integer.
	 * 
	 * @return: The average speed integer.
	 */
	public int getAverageSpeed() {
		return averageSpeed;
	}

}
