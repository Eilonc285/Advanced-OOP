package components;

import java.util.ArrayList;

import utilities.Utilities;
import utilities.VehicleType;

public class Road implements RouteParts, Utilities {
	private int[] allowedSpeedOptions;
	private boolean enable;
	private Junction startJunction;
	private Junction endJunction;
	private boolean greenlight;
	private double length;
	private int maxSpeed;
	private VehicleType[] vehicleTypes;
	private ArrayList<Vehicle> waitingVehicles;

	public Road(Junction start, Junction end) {
	}

	public void addVehicleToWaitingVehicles(Vehicle vehicle) {
	}

	public double calcEstimatedTime(Object obj) {
		return length;
	}

	public double calcLength() {
		return length;
	}

	public boolean canLeave(Vehicle vehicle) {
		return enable;
	}

	public void checkIn(Vehicle vehicle) {
		this.waitingVehicles.add(vehicle);
	}

	public void checkout(Vehicle vehicle) {
		this.removeVehicleFromWaitingVehicles(vehicle);
		System.out.printf("The vehicle %s has checked out of the road from %s to %s\n", vehicle.toString(),
				this.startJunction.getJunctionName());
	}

	public RouteParts findNextPart(Vehicle vehicle) {
		return endJunction;
	}

	public void removeVehicleFromWaitingVehicles(Vehicle vehicle) {
		this.waitingVehicles.remove(vehicle);
	}

	public void stayOnCurrentPart(Vehicle vehicle) {
	}

}
