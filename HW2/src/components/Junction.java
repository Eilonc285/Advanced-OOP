package components;

import java.util.ArrayList;

import utilities.Point;

public class Junction extends Point implements RouteParts {
	private int objectCounts;
	private ArrayList<Road> enteringRoads; 
	private ArrayList<Road> exitingRoads;
	private String junctionName;
	
	public Junction() {}
	public Junction(String junctionName, double x, double y) {}
	public void addEnteringRoad(Road road) {}
	public void addExitingRoad(Road road) {}
	public double calcEstimatedTime(Object obj) {}
	public boolean canLeave(Vehicle vehicle) {}
	public boolean checkAvailability(Vehicle vehicle) {}
	public void checkIn(Vehicle vehicle) {}
	public void checkOut(Vehicle vehicle) {}
	public RouteParts findNextPart(Vehicle vehicle) {}
	public void stayOnCurrentPart(Vehicle vehicle) {}
	
	
}
