/**
 * 
 */
package utilities;

import java.util.Random;

/**
 * @author Sophie Krimberg, Nir Barel, Eilon Cohen
 *
 */
public enum VehicleType {
	car(9), bus(6), bicycle(4), motorcycle(12), truck(8), tram(5), semitrailer(8.5f);

	private float averageSpeed;
	private float speedMultiplier = 0.01f;

	VehicleType(float speed) {
		Random rand = new Random();
		averageSpeed = rand.nextFloat() * (speed * 1.5f) * speedMultiplier;

	}

	public float getAverageSpeed() {
		return averageSpeed;
	}

	public void setSpeedMultiplier(float n) {
		speedMultiplier = n;
	}

	public float getSpeedMultiplier() {
		return speedMultiplier;
	}
}
