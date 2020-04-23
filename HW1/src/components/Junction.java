package components;

import java.util.ArrayList;

import utilities.Point;

public class Junction {
	private String junctionName;
	private Point location;
	private ArrayList<Road> enteringRoads;
	private ArrayList<Road> exitingRoads;
	private boolean hasLights;
	private int delay;
	private ArrayList<Road> vehicles;

	public Junction(String name, Point loc) {
		this.junctionName = name;
		this.location = loc;
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
	}

	@Override
	public String toString() {
//		return String.format("");
	}

	@Override
	public boolean equals(Object other) {
//		return true 
	}

}
