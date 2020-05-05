package components;

import java.util.Random;

public class LightedJunction extends Junction {
	private TrafficLights lights;

	public LightedJunction() {
		super();
		Random rand = new Random();
		if (rand.nextBoolean()) {
			this.lights = new SequentialTrafficLights(this.getEnteringRoads());
		} else {
			this.lights = new RandomTrafficLights(this.getEnteringRoads());
		}
	}

	public LightedJunction(String name, double x, double y, boolean sequential, boolean lightsOn) {
		super(name, x, y);
		if (sequential) {
			this.lights = new SequentialTrafficLights(this.getEnteringRoads());
		} else {
			this.lights = new RandomTrafficLights(this.getEnteringRoads());
		}
	}

	public double calcEstimatedTime(Object obj) {
		return 0;
	}

	public boolean canLeave(Vehicle vehicle) {
		return false;
	}

	public TrafficLights getLights() {
		return lights;
	}

	public boolean isSequential() {
		return this.lights instanceof SequentialTrafficLights;
	}

	@Override
	public String toString() {
		return String.format("Junction %s (Lighted)", this.getJunctionName());
	}

	@Override
	public boolean equals(Object other) {
		return super.equals(other);
	}
}
