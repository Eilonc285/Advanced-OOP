package components;

import java.util.ArrayList;
import java.util.Random;

import utilities.Utilities;

/**
 * Map class to create, initialize, and hold all the junctions and the roads.
 * 
 * @author Nir
 *
 */
public class Map implements Utilities {
	private ArrayList<Junction> junctions;
	private ArrayList<Road> roads;
	private ArrayList<TrafficLights> lights;

	/**
	 * Constructor initializes all the junctions in the amount given, and the roads
	 * between them.
	 * 
	 * @param numOfJunctions: The number of junctions to create.
	 */
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

	/**
	 * Helper method for the constructor. Initializes all the roads between all the
	 * junctions.
	 */
	public void SetAllRoads() {
		for (int i = this.junctions.size() - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				this.roads.add(new Road(this.junctions.get(j), this.junctions.get(i)));
				this.roads.add(new Road(this.junctions.get(i), this.junctions.get(j)));
			}
		}
	}

	/**
	 * Turns on the lights at random in all the lighted junctions.
	 */
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

	/**
	 * Returns the ArrayList of all existing junctions in the map.
	 * 
	 * @return: The junctions ArrayList.
	 */
	public ArrayList<Junction> getJunctions() {
		return junctions;
	}

	/**
	 * Returns the ArrayList of all the roads in this map.
	 * 
	 * @return: The roads ArrayList.
	 */
	public ArrayList<Road> getRoads() {
		return roads;
	}

	/**
	 * Returns the ArrayList of all the traffic lights in the map.
	 * 
	 * @return: ArrayList of TrafficLights objects.
	 */
	public ArrayList<TrafficLights> getLights() {
		return lights;
	}

	/**
	 * Used to determine if this object is equal to another.
	 *
	 * @param other: The other object to be compared to.
	 * @return: Boolean of if the objects equal each other.
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Map) {
			return this.junctions.equals(((Map) other).getJunctions()) && this.roads.equals(((Map) other).getRoads())
					&& this.lights.equals(((Map) other).getLights());
		}
		return false;
	}

	/**
	 * Returns a string representation of this object.
	 * 
	 * @return: The String representation.
	 */
	@Override
	public String toString() {
		return String.format("Map with %d Junctions, %d of which are lighted, and %d Roads between them",
				this.junctions.size(), this.lights.size(), this.roads.size());
	}

}
