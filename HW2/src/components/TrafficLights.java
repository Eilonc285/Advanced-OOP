package components;

import java.util.ArrayList;
import java.util.Random;

import utilities.Timer;
import utilities.Utilities;

public abstract class TrafficLights implements Timer, Utilities {
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
		this.roads = new ArrayList<Road>(roads);
		this.id = TrafficLights.objectsCount;
		this.workingTime = 0;
		this.delay = rand.nextInt(this.maxDelay - this.minDelay + 1) + this.minDelay;
		this.greenLightIndex = 0;
		TrafficLights.objectsCount++;
	}

	public abstract void changeIndex();

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

	public static int getObjectsCount() {
		return objectsCount;
	}

	public static void setObjectsCount(int objectsCount) {
		TrafficLights.objectsCount = objectsCount;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getGreenLightIndex() {
		return greenLightIndex;
	}

	public void setGreenLightIndex(int greenLightIndex) {
		this.greenLightIndex = greenLightIndex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Road> getRoads() {
		return roads;
	}

	public void setRoads(ArrayList<Road> roads) {
		this.roads = roads;
	}

	public int getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(int workingTime) {
		this.workingTime = workingTime;
	}

	public int getMinDelay() {
		return minDelay;
	}

	public int getMaxDelay() {
		return maxDelay;
	}

}
