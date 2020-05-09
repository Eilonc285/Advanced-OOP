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

	/**
	 * Constructor initiates the fields and sets the given start and end junctions.
	 * 
	 * @param start: The starting junction of the road.
	 * @param end:   The end junction of the road.
	 */
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

	/**
	 * Adds a vehicle object to the waiting ArrayList.
	 * 
	 * @param vehicle: The object to be added.
	 */
	public void addVehicleToWaitingVehicles(Vehicle vehicle) {
		this.waitingVehicles.add(vehicle);
	}

	/**
	 * Calculated the estimated time it will take the given vehicle to pass this
	 * road (in pulses).
	 * 
	 * @param obj: The vehicle to estimate time for.
	 */
	public double calcEstimatedTime(Object obj) {
		if (obj instanceof Vehicle) {
			double dx = ((Vehicle) obj).getLastRoad().getStartJunction()
					.calcDistance((((Vehicle) obj).getLastRoad().getEndJunction()));
			double dv = Math.min(this.maxSpeed, ((Vehicle) obj).getVehicleType().getAverageSpeed());
			return (double) Math.round(dx / dv);
		}
		return -1;
	}

	/**
	 * Calculates the length of this road.
	 * 
	 * @return: The length of this road.
	 */
	public double calcLength() {
		return Math.sqrt(Math.pow(this.startJunction.getX() - this.endJunction.getX(), 2)
				+ Math.pow(this.startJunction.getY() - this.endJunction.getY(), 2));
	}

	/**
	 * Returns a boolean of if the given vehicle can leave this road or not.
	 * 
	 * @param vehicle: The vehicle to check for leaving possibility.
	 */
	public boolean canLeave(Vehicle vehicle) {
		return this.calcEstimatedTime(vehicle) <= vehicle.getTimeOnCurrentPart();
	}

	/**
	 * Registers a vehicle into this road by setting its current route part time to
	 * 0 and adding it to the waiting list.
	 * 
	 * @param vehicle: The vehicle to register.
	 */
	public void checkIn(Vehicle vehicle) {
		vehicle.setTimeOnCurrentPart(0);
		this.waitingVehicles.add(vehicle);
		System.out.printf("- is starting to move on %s, time to finish: %f\n", this.toString(),
				this.calcEstimatedTime(vehicle));
	}

	/**
	 * Checks out the vehicle from this road when it advances to the next route
	 * part. Removes the vehicle from the waiting list.
	 * 
	 * @param vehicle: The vehicle to check out.
	 */
	public void checkOut(Vehicle vehicle) {
		this.removeVehicleFromWaitingVehicles(vehicle);
		System.out.printf("- has finished %s, time spent on road: %d\n", this.toString(),
				vehicle.getTimeOnCurrentPart());
	}

	/**
	 * Returns the next part in the vehicle's route, which will be the end junction
	 * of this road.
	 * 
	 * @param vehicle: The vehicle for which to find the next part.
	 */
	public RouteParts findNextPart(Vehicle vehicle) {
		return endJunction;
	}

	/**
	 * Removes the vehicle from the waiting list of this road.
	 * 
	 * @param vehicle: The vhicle to remove.
	 */
	public void removeVehicleFromWaitingVehicles(Vehicle vehicle) {
		this.waitingVehicles.remove(vehicle);
	}

	/**
	 * This method is called when the vehicle can't advance to the next route part,
	 * and it prints a message.
	 */
	public void stayOnCurrentPart(Vehicle vehicle) {
		System.out.printf("%s stays in the current route part", vehicle.toString());
	}

	/**
	 * Returns the array of allowed speed options;
	 * 
	 * @return: The speed options array.
	 */
	public int[] getAllowedSpeedOptions() {
		return allowedSpeedOptions;
	}

	/**
	 * Return a boolean of if this road is enabled.
	 * 
	 * @return: The enabled boolean.
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * Returns the junction at the start of this road.
	 * 
	 * @return: The start junction.
	 */
	public Junction getStartJunction() {
		return startJunction;
	}

	/**
	 * Returns the junction at the end of this road.
	 * 
	 * @return: The end junction.
	 */
	public Junction getEndJunction() {
		return endJunction;
	}

	/**
	 * Returns a boolean of if this road has a green light/
	 * 
	 * @return: The green light boolean.
	 */
	public boolean isGreenlight() {
		return greenlight;
	}

	/**
	 * Returns the length of this road;
	 * 
	 * @return: The road length.
	 */
	public double getLength() {
		return length;
	}

	/**
	 * Returns the maximum speed allowed on this road.
	 * 
	 * @return: The maximum speed.
	 */
	public int getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * Return the vehicle types enum.
	 * 
	 * @return: The vehicle types enum.
	 */
	public VehicleType[] getVehicleTypes() {
		return vehicleTypes;
	}

	/**
	 * Returns the ArrayList of vehicles waiting on this road.
	 * 
	 * @return: The waiting vehicles ArrayList.
	 */
	public ArrayList<Vehicle> getWaitingVehicles() {
		return waitingVehicles;
	}

	/**
	 * Sets the allowed speed options array.
	 * 
	 * @param allowedSpeedOptions: The array to be set.
	 */
	public void setAllowedSpeedOptions(int[] allowedSpeedOptions) {
		this.allowedSpeedOptions = allowedSpeedOptions;
	}

	/**
	 * Sets the boolean of if this road is enabled.
	 * 
	 * @param enable: The boolean to be set.
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/**
	 * Sets the junction at the start of this road.
	 * 
	 * @param startJunction: The junction to be set.
	 */
	public void setStartJunction(Junction startJunction) {
		this.startJunction = startJunction;
	}

	/**
	 * Sets the junction at the end of this road.
	 * 
	 * @param endJunction: The junction to be set.
	 */
	public void setEndJunction(Junction endJunction) {
		this.endJunction = endJunction;
	}

	/**
	 * Sets the boolean of if this road has a green light.
	 * 
	 * @param greenlight: The boolean to be set.
	 */
	public void setGreenlight(boolean greenlight) {
		this.greenlight = greenlight;
	}

	/**
	 * Sets the length of this road.
	 * 
	 * @param length: The length to be set.
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * Sets the maximum speed allowed on this road.
	 * 
	 * @param maxSpeed: The speed to be set.
	 */
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	/**
	 * Sets the allowed vehicle types array on this road.
	 * 
	 * @param vehicleTypes: The vehicle types array to be set.
	 */
	public void setVehicleTypes(VehicleType[] vehicleTypes) {
		this.vehicleTypes = vehicleTypes;
	}

	/**
	 * Sets the waiting vehicles ArrayList.
	 * 
	 * @param waitingVehicles: The ArrayList to be set.
	 */
	public void setWaitingVehicles(ArrayList<Vehicle> waitingVehicles) {
		this.waitingVehicles = waitingVehicles;
	}

	/**
	 * Overridden method. Returns a string representation of this object.
	 * 
	 * @return: The string representation.
	 */
	@Override
	public String toString() {
		return String.format("Road form %s to %s, length: %d, max speed %d", this.startJunction.toString(),
				this.endJunction.toString(), (int) this.length, this.maxSpeed);
	}

	/**
	 * Overridden method. Returns a boolean of if this object equals the other given
	 * object.
	 * 
	 * @return: The boolean of if they equal.
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Road) {
			return this.startJunction.getJunctionName().equals(((Road) other).getStartJunction().getJunctionName())
					&& this.endJunction.getJunctionName().equals(((Road) other).getEndJunction().getJunctionName());
		}
		return false;
	}

}
