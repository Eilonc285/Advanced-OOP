package components;

import java.util.Random;

public class LightedJunction extends Junction {
	private TrafficLights lights;
	private boolean sequential;

	public LightedJunction() {
		super();
		Random rand = new Random();
		this.lights = new TrafficLights();
		this.sequential = rand.nextBoolean();
	}

	public LightedJunction(String name, double x, double y, boolean sequential, boolean lightsOn) {
		super(name, x, y);
		Random rand = new Random();
		this.lights = new TrafficLights();
		this.sequential = rand.nextBoolean();
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
		return sequential;
	}

}
