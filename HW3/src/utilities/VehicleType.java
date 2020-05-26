/**
 * 
 */
package utilities;

/**
 * @author Sophie Krimberg, Nir Barel, Eilon Cohen
 *
 */
public enum VehicleType {
	car(9), bus(6), bicycle(4), motorcycle(12), truck(8), tram(5), semitrailer(8.5f);

	private float averageSpeed;
	private float speedMultiplier = 0.01f;

	VehicleType(float speed) {
		averageSpeed = speed * speedMultiplier;

	}

	public float getAverageSpeed() {
		return averageSpeed;
	}

	public void setSpeedMultiplier(float n) {
		speedMultiplier = n;
	}
}
