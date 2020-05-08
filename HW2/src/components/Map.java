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
			Junction newJunc;
			if (rand.nextBoolean()) {
				newJunc = new Junction();
				this.junctions.add(newJunc);
			} else {
				newJunc = new LightedJunction();
				this.junctions.add(newJunc);
			}

		}
		this.SetAllRoads();
		this.turnLightsOn();
		System.out.println("================= GAME MAP HAS BEEN CREATED =================");
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
		System.out.println("================= TRAFFIC LIGHTS TURN ON =================");
		for (int i = 0; i < this.junctions.size(); i++) {
			Random rand = new Random();
			if (this.junctions.get(i) instanceof LightedJunction) {
				((LightedJunction) this.junctions.get(i)).getLights().setTrafficLightsOn(rand.nextBoolean());
				if (((LightedJunction) this.junctions.get(i)).getLights().isTrafficLightsOn()) {
					System.out.printf("%s turned ON, delay time: %d\n",
							((LightedJunction) this.junctions.get(i)).getLights().toString(),
							((LightedJunction) this.junctions.get(i)).getLights().getDelay());
					int entRoad = rand.nextInt(this.junctions.get(i).getEnteringRoads().size());
					((LightedJunction) this.junctions.get(i)).getLights().setGreenLightIndex(entRoad);
					this.junctions.get(i).getEnteringRoads().get(entRoad).setGreenlight(true);
				}
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
