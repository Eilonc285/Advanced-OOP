package components;

import java.util.Random;

public class LightedJunction extends Junction {
	private TrafficLights lights;

	/**
	 * Constructor initializes a TrafficLights object that is chosen randomly to be
	 * either sequential or random.
	 */
	public LightedJunction() {
		super();
		Random rand = new Random();
		if (rand.nextBoolean()) {
			this.lights = new SequentialTrafficLights(this.getEnteringRoads());
		} else {
			this.lights = new RandomTrafficLights(this.getEnteringRoads());
		}
	}

	/**
	 * Constructor with given arguments for name, x position, and y position which
	 * are given to the parent class constructor(Junction). In addition, a boolean
	 * of if the created lights would be sequential, and a boolean of if the traffic
	 * lights are turned on.
	 * 
	 * @param name:       String of the given name to the junction.
	 * @param x:          The x position of the junction.
	 * @param y:          The y position of the junction.
	 * @param sequential: The boolean of if the created TrafficLights object is
	 *                    sequential.
	 * @param lightsOn:   Boolean of if the traffic lights are turned on.
	 */
	public LightedJunction(String name, double x, double y, boolean sequential, boolean lightsOn) {
		super(name, x, y);
		if (sequential) {
			this.lights = new SequentialTrafficLights(this.getEnteringRoads());
		} else {
			this.lights = new RandomTrafficLights(this.getEnteringRoads());
		}
	}

	/**
	 * Overridden from RouteParts interface. Calculates the estimated time it would
	 * take the vehicle to pass this junction. The calculation is the number of
	 * pulses until the road of the given vehicle will have a green light.
	 * 
	 * @param obj: The vehicle for which to calculate the time.
	 */
	public double calcEstimatedTime(Object obj) {
		return ((this.lights.getRoads().size() - 1) * this.lights.getDelay()) + 1;
	}

	/**
	 * Returns a boolean of if the given vehicle can leave this junction, meaning
	 * the vehicle has green light.
	 * 
	 * @param vehicle: The vehicle for which to check if it can leave.
	 */
	public boolean canLeave(Vehicle vehicle) {
		return vehicle.getLastRoad().isGreenlight();
	}

	/**
	 * Returns the TrafficLights object of this junction.
	 * 
	 * @return: TrafficLights object.
	 */
	public TrafficLights getLights() {
		return lights;
	}

	/**
	 * Returns boolean of if the TrafficLights object of this junction is sequential
	 * or not,
	 * 
	 * @return: The boolean of if the traffic lights are sequential.
	 */
	public boolean isSequential() {
		return this.lights instanceof SequentialTrafficLights;
	}

	/**
	 * Returns a string representation of this object.
	 * 
	 * @return: The String representation.
	 */
	@Override
	public String toString() {
		return String.format("Junction %s (Lighted)", this.getJunctionName());
	}

	/**
	 * Used to determine if this object is equal to another.
	 *
	 * @param other: The other object to be compared to.
	 * @return: Boolean of if the objects equal each other.
	 */
	@Override
	public boolean equals(Object other) {
		return super.equals(other);
	}
}
