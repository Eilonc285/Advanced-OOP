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
			return ((this.lights.getRoads().size() - 1) * this.lights.getDelay()) + 1;
	}

	public boolean canLeave(Vehicle vehicle) {
		return this.lights.isTrafficLightsOn();
	}

	public TrafficLights getLights() {
		return lights;
	}

	public boolean isSequential() {
		return !(this.lights instanceof RandomTrafficLights);
	}

}
