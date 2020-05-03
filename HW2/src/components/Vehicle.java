package components;

import java.util.Random;

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
	private static int objectsCount;
	private Road lastRoad;
	private String status;

	static {
		objectsCount = 0;
	}

	public Vehicle(Road road) {
		Random rand = new Random();
		this.id = Vehicle.objectsCount++;
		this.currentRoutePart = road;
		this.lastRoad = road;
		this.vehicleType = VehicleType.values()[rand.nextInt(VehicleType.values().length)];
		this.timeFromRouteStart = 0;
		this.timeOnCurrentPart = 0;
		this.status = "waiting";
		this.currentRoute = new Route(road, this);
	}

	public int getId() {
		return id;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public Route getCurrentRoute() {
		return currentRoute;
	}

	public RouteParts getCurrentRoutePart() {
		return currentRoutePart;
	}

	public int getTimeFromRouteStart() {
		return timeFromRouteStart;
	}

	public int getTimeOnCurrentPart() {
		return timeOnCurrentPart;
	}

	public static int getObjectsCount() {
		return objectsCount;
	}

	public Road getLastRoad() {
		return lastRoad;
	}

	public String getStatus() {
		return status;
	}

	public void move() {
		if (this.currentRoutePart instanceof Junction && this.currentRoutePart.canLeave(this)) {
			RouteParts nextPart = this.currentRoute.findNextPart(this);
			this.currentRoutePart.checkOut(this);
			nextPart.checkIn(this);

		}
	}

	public void incrementDrivingTime() {
		this.timeFromRouteStart++;
		this.timeOnCurrentPart++;
		move();

	}

}
