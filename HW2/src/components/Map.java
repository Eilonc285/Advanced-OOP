package components;

import java.util.ArrayList;
import java.util.Random;

import utilities.Utilities;

public class Map implements Utilities {
	private ArrayList<Junction> junctions;
	private ArrayList<Road> roads;
	private ArrayList<TrafficLights> lights;

	public Map(int numOfJunctions) {
		this.junctions = new ArrayList<Junction>();
		this.roads = new ArrayList<Road>();
		this.lights = new ArrayList<TrafficLights>();
		Random rand = new Random();
		for (int i = 0; i < numOfJunctions; i++) {
			if (rand.nextBoolean()) {
				this.junctions.add(new Junction());
			} else {
				this.junctions.add(new LightedJunction());
			}

		}
		this.SetAllRoads();
		this.turnLightsOn();
	}

	public void SetAllRoads() {
		for (int i = this.junctions.size() - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				this.roads.add(new Road(this.junctions.get(j), this.junctions.get(i)));
				this.roads.add(new Road(this.junctions.get(i), this.junctions.get(j)));
			}
		}
	}

	public void turnLightsOn() {
		for (int i = 0; i < this.junctions.size(); i++) {
			Random rand = new Random();
			if (this.junctions.get(i) instanceof LightedJunction) {
				((LightedJunction) this.junctions.get(i)).getLights().setTrafficLightsOn(rand.nextBoolean());
			}
		}
	}

	public ArrayList<Junction> getJunctions() {
		return junctions;
	}

	public ArrayList<Road> getRoads() {
		return roads;
	}

	public ArrayList<TrafficLights> getLights() {
		return lights;
	}

}
