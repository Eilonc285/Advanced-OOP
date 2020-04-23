package components;

import java.util.ArrayList;
import java.util.Random;

import utilities.Point;

public class Junction {
	private String junctionName;
	private Point location;
	private ArrayList<Road> enteringRoads;
	private ArrayList<Road> exitingRoads;
	private boolean hasLights;
	private int delay;
	private ArrayList<Road> vehicles;
	private int priorityRoadIndex;

	public Junction(String name, Point loc) {
		Random rand = new Random();
		this.junctionName = name;
		this.location = loc;
		this.priorityRoadIndex = rand.nextInt(this.enteringRoads.size());
		System.out.printf("Junction %s has been created\n", this.junctionName);
	}

	public String getJunctionName() {
		return junctionName;
	}

	public void setJunctionName(String junctionName) {
		this.junctionName = junctionName;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public ArrayList<Road> getEnteringRoads() {
		return enteringRoads;
	}

	public void setEnteringRoads(ArrayList<Road> enteringRoads) {
		this.enteringRoads = enteringRoads;
	}

	public ArrayList<Road> getExitingRoads() {
		return exitingRoads;
	}

	public void setExitingRoads(ArrayList<Road> exitingRoads) {
		this.exitingRoads = exitingRoads;
	}

	public boolean isHasLights() {
		return hasLights;
	}

	public void setHasLights(boolean hasLights) {
		this.hasLights = hasLights;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public ArrayList<Road> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<Road> vehicles) {
		this.vehicles = vehicles;
	}

	public void changeLight() {
	}

	public boolean checkAvailability(Road r) {
		return this.enteringRoads.indexOf(r) == this.priorityRoadIndex;
	}

	@Override
	public String toString() {
		return String.format("Junction %s", this.junctionName);
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Junction) {
			return this.location.equals(((Junction) other).getLocation());
		}
		return false;
	}

}
