package components;

import java.util.ArrayList;
import java.util.Random;

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
		Random rand = new Random();
		this.waitingVehicles = new ArrayList<Vehicle>();
		this.startJunction = start;
		this.startJunction.getExitingRoads().add(this);
		if (this.startJunction instanceof LightedJunction) {
			((LightedJunction) this.startJunction).getLights().getRoads().add(this);
		}
		this.endJunction = end;
		this.endJunction.getEnteringRoads().add(this);
		if (this.endJunction instanceof LightedJunction) {
			((LightedJunction) this.endJunction).getLights().getRoads().add(this);
		}
		this.greenlight = false;
		this.length = this.calcLength();
		this.maxSpeed = VehicleType.values()[rand.nextInt(VehicleType.values().length)].getAverageSpeed();
		System.out.printf("%s has been created.\n", this.toString());
	}

	public void addVehicleToWaitingVehicles(Vehicle vehicle) {
		this.waitingVehicles.add(vehicle);
	}

	public double calcEstimatedTime(Object obj) {
		if(obj instanceof Vehicle) {
			double dx = ((Vehicle)obj).getLastRoad().getStartJunction().calcDistance((((Vehicle)obj).getLastRoad().getEndJunction()));
			double dv = Math.min(this.maxSpeed, ((Vehicle)obj).getVehicleType().getAverageSpeed());
			return (double)Math.round(dx/dv);
		}
		return -1;
	}

	public double calcLength() {
		return Math.sqrt(Math.pow(this.startJunction.getX() - this.endJunction.getX(), 2)
				+ Math.pow(this.startJunction.getY() - this.endJunction.getY(), 2));
	}

	public boolean canLeave(Vehicle vehicle) {
		return this.calcEstimatedTime(this) <= vehicle.getTimeOnCurrentPart();
	}

	public void checkIn(Vehicle vehicle) {
		this.waitingVehicles.add(vehicle);
		System.out.printf("The vehicle %s has checked in to the road from %s to %s", vehicle.toString(),
				this.startJunction.getJunctionName(), this.endJunction.getJunctionName());
	}

	public void checkOut(Vehicle vehicle) {
		this.removeVehicleFromWaitingVehicles(vehicle);
		System.out.printf("The vehicle %s has checked out of the road from %s to %s\n", vehicle.toString(),
				this.startJunction.getJunctionName(), this.endJunction.getJunctionName());
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
		System.out.printf("%s:\n green light.\n", this.toString());
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

	@Override
	public String toString() {
		return String.format("Road form %s to %s, length: %d, max speed %d", this.startJunction.toString(),
				this.endJunction.toString(), (int) this.length, this.maxSpeed);
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Road) {
			return this.startJunction.equals(((Road) other).getStartJunction())
					&& this.endJunction.equals(((Road) other).getEndJunction());
		}
		return false;
	}

}
