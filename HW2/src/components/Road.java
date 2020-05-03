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

	public void checkOut(Vehicle vehicle) {
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
		System.out.printf("%s stays in the current route part", vehicle.toString());
	}

	public int[] getAllowedSpeedOptions() {
		return allowedSpeedOptions;
	}

	public boolean isEnable() {
		return enable;
	}

	public Junction getStartJunction() {
		return startJunction;
	}

	public Junction getEndJunction() {
		return endJunction;
	}

	public boolean isGreenlight() {
		return greenlight;
	}

	public double getLength() {
		return length;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public VehicleType[] getVehicleTypes() {
		return vehicleTypes;
	}

	public ArrayList<Vehicle> getWaitingVehicles() {
		return waitingVehicles;
	}

	public void setAllowedSpeedOptions(int[] allowedSpeedOptions) {
		this.allowedSpeedOptions = allowedSpeedOptions;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public void setStartJunction(Junction startJunction) {
		this.startJunction = startJunction;
	}

	public void setEndJunction(Junction endJunction) {
		this.endJunction = endJunction;
	}

	public void setGreenlight(boolean greenlight) {
		this.greenlight = greenlight;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setVehicleTypes(VehicleType[] vehicleTypes) {
		this.vehicleTypes = vehicleTypes;
	}

	public void setWaitingVehicles(ArrayList<Vehicle> waitingVehicles) {
		this.waitingVehicles = waitingVehicles;
	}

}
