/**
 * 
 */
package utilities;

/**
 * @author Sophie Krimberg
 *
 */
public enum VehicleType {
	car(9), bus(6), bicycle(4), motorcycle(12), truck(8), tram(5), semitrailer(8.5f);

	private float averageSpeed;

	VehicleType(float speed) {
		averageSpeed = speed;

	}

	public float getAverageSpeed() {
		return averageSpeed;
	}
}
