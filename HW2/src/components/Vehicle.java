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

	public void setId(int id) {
		this.id = id;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public void setCurrentRoute(Route currentRoute) {
		this.currentRoute = currentRoute;
	}

	public void setCurrentRoutePart(RouteParts currentRoutePart) {
		this.currentRoutePart = currentRoutePart;
	}

	public void setTimeFromRouteStart(int timeFromRouteStart) {
		this.timeFromRouteStart = timeFromRouteStart;
	}

	public void setTimeOnCurrentPart(int timeOnCurrentPart) {
		this.timeOnCurrentPart = timeOnCurrentPart;
	}

	public static void setObjectsCount(int objectsCount) {
		Vehicle.objectsCount = objectsCount;
	}

	public void setLastRoad(Road lastRoad) {
		this.lastRoad = lastRoad;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Vehicle(Road road) {
		Random rand = new Random();
		this.id = ++Vehicle.objectsCount;
		this.currentRoutePart = road;
		this.lastRoad = road;
		this.vehicleType = VehicleType.values()[rand.nextInt(VehicleType.values().length)];
		this.timeFromRouteStart = 0;
		this.timeOnCurrentPart = 0;
		this.status = "waiting";
		this.currentRoute = new Route(road, this);
		System.out.printf("%s has been created\n", this.toString());
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

	@Override
	public String toString() {
		return String.format("Vehicle %d: %s, average speed: %d", this.id, this.vehicleType.toString(),
				this.vehicleType.getAverageSpeed());
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Vehicle) {
			return this.id == ((Vehicle) other).getId() && this.vehicleType.equals(((Vehicle) other).getVehicleType())
					&& this.currentRoutePart.equals(((Vehicle) other).getCurrentRoutePart());
		}
		return false;
	}

}
