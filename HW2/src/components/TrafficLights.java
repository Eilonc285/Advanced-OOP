package components;

import java.util.ArrayList;
import java.util.Random;

import utilities.Timer;
import utilities.Utilities;

public class TrafficLights implements Timer, Utilities {
	private static int objectsCount;
	private int delay;
	private int greenLightIndex;
	private int id;
	private final int minDelay = 2;
	private final int maxDelay = 6;
	private ArrayList<Road> roads;
	private boolean trafficLightsOn;
	private int workingTime;

	static {
		objectsCount = 0;
	}

	public TrafficLights(ArrayList<Road> roads) {
		Random rand = new Random();
		this.roads = roads;
		this.id = TrafficLights.objectsCount;
		this.workingTime = 0;
		this.delay = rand.nextInt(this.maxDelay - this.minDelay + 1) + this.minDelay;
		this.greenLightIndex = rand.nextInt(this.roads.size());
		TrafficLights.objectsCount++;
	}

	public void changeIndex() {
		this.greenLightIndex++;
		if (this.greenLightIndex >= this.roads.size()) {
			this.greenLightIndex -= this.roads.size();
		}
	}

	public void changeLights() {
		this.changeIndex();
		for (int i = 0; i < this.roads.size(); i++) {
			if (i == this.greenLightIndex) {
				this.roads.get(i).setGreenlight(true);
				System.out.printf("Entering road number %d has a green light\n", i);
			} else {
				this.roads.get(i).setGreenlight(false);
			}
		}
	}

	public void incrementDrivingTime() {
		this.workingTime++;
		if (this.workingTime % this.delay == 0) {
			this.changeLights();
		}
	}

	public boolean isTrafficLightsOn() {
		return trafficLightsOn;
	}

	public void setTrafficLightsOn(boolean trafficLightsOn) {
		this.trafficLightsOn = trafficLightsOn;
	}

}
