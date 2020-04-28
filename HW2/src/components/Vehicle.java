package components;

import utilities.Timer;
import utilities.Utilities;
import utilities.VehicleType;

public class Vehicle implements Timer, Utilities {
	private int id;
	private VehicleType vehicleType;
	private Route currentRoute;
	private RouteParts currentRoutePart;
	private int timeFromRouteStart;
	private int timeOnCurrentPart;
	private int objectsCount;
	private Road lastRoad;
	private String status;
	
	public Vehicle(Road road) {}
	public void move() {}
	public void incrementDrivingTime() {}
	
}
