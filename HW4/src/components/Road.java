package components;

import java.util.ArrayList;

import utilities.GameDriver;
import utilities.VehicleType;

/**
 * Represents a road
 * 
 * @author Sophie Krimberg, Nir Barel, Eilon Cohen
 *
 */
public class Road implements RouteParts {
	private final static float[] allowedSpeedOptions = { 3, 4, 5, 5.5f, 6, 7, 8, 9 };
	Junction startJunction;
	Junction endJunction;
	private ArrayList<Vehicle> waitingVehicles;
	private boolean greenLight;
	private float maxSpeed;
	VehicleType[] vehicleTypes;
	private double length;
	private boolean enable;
	private static boolean alwaysEnabled = true;

	/**
	 * Constructor
	 * 
	 * @param start
	 * @param end
	 */
	public Road(Junction start, Junction end) {
		startJunction = start;
		endJunction = end;
		waitingVehicles = new ArrayList<Vehicle>();
		greenLight = false;

		int numOfTypes = getRandomInt(3, VehicleType.values().length);

		vehicleTypes = new VehicleType[numOfTypes];
		VehicleType[] types = VehicleType.values();
		ArrayList<Integer> arr = getRandomIntArray(0, 6, numOfTypes);

		for (int i = 0; i < numOfTypes; i++) {
			vehicleTypes[i] = types[arr.get(i)];
		}

		maxSpeed = allowedSpeedOptions[getRandomInt(0, 7)] * vehicleTypes[0].getSpeedMultiplier();

		this.getStartJunction().getExitingRoads().add(this);
		this.getEndJunction().getEnteringRoads().add(this);

		setLength();
		enable = !(getRandomBoolean() && getRandomBoolean() && getRandomBoolean());
		if (alwaysEnabled) {
			enable = true;
		}
		successMessage(this.toString());
	}

	/**
	 * @return the startJunction
	 */
	public Junction getStartJunction() {
		return startJunction;
	}

	/**
	 * @param startJunction the startJunction to set
	 */
	public void setStartJunction(Junction startJunction) {
		this.startJunction = startJunction;
	}

	/**
	 * @return the endJunction
	 */
	public Junction getEndJunction() {
		return endJunction;
	}

	/**
	 * @param endJunction the endJunction to set
	 */
	public void setEndJunction(Junction endJunction) {
		this.endJunction = endJunction;
	}

	/**
	 * @return the waitingVehicles
	 */
	public ArrayList<Vehicle> getWaitingVehicles() {
		return waitingVehicles;
	}

	/**
	 * @param waitingVehicles the waitingVehicles to set
	 */
	public void setWaitingVehicles(ArrayList<Vehicle> waitingVehicles) {
		this.waitingVehicles = waitingVehicles;
	}

	/**
	 * @return the greenLight
	 */
	public boolean getGreenLight() {
		return greenLight;
	}

	/**
	 * @param greenLight the greenLight to set
	 */
	public void setGreenLight(boolean greenLight) {
		this.greenLight = greenLight;
	}

	/**
	 * @return the maxSpeed
	 */
	public float getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * @param maxSpeed the maxSpeed to set
	 */
	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	/**
	 * @return the vehicleTypes
	 */
	public VehicleType[] getVehicleTypes() {
		return vehicleTypes;
	}

	/**
	 * @param vehicleTypes the vehicleTypes to set
	 */
	public void setVehicleTypes(VehicleType[] vehicleTypes) {
		this.vehicleTypes = vehicleTypes;
	}

	public synchronized void addVehicleToWaitingVehicles(Vehicle vehicle) {
		waitingVehicles.add(vehicle);
	}

	public synchronized void removeVehicleFromWaitingVehicles(Vehicle vehicle) {
		waitingVehicles.remove(vehicle);
	}

	/**
	 * @return the length
	 */
	public double getLength() {
		return length;
	}

	/**
	 * set length to the calculated value
	 * 
	 */
	public void setLength() {
		this.length = calcLength();
	}

	/**
	 * @return the enable
	 */
	public boolean getEnabled() {
		return enable;
	}

	/**
	 * @param enable the enable to set
	 */
	public void setEnabled(boolean enable) {
		this.enable = enable;
	}

	/**
	 * @return the allowedspeedoptions
	 */
	public static float[] getAllowedspeedoptions() {
		return allowedSpeedOptions;
	}

	public double calcLength() {
		return startJunction.calcDistance(endJunction);
	}

	@Override
	public double calcEstimatedTime(Object obj) {
		Vehicle v = (Vehicle) obj;
//		float speed = Math.min(maxSpeed, v.getVehicleType().getAverageSpeed());
		float speed = v.getIndependantSpeed();
		return length / speed;
	}

	@Override
	public RouteParts findNextPart(Vehicle vehicle) {
		return endJunction;
	}

	@Override
	public void checkIn(Vehicle vehicle) {
		vehicle.setCurrentRoutePart(this);
		vehicle.setTimeOnCurrentPart(0);
		vehicle.setLastRoad(this);
		vehicle.resetUpdateBool();
		if (GameDriver.isPConsole())
			System.out.println(
					"- is starting to move on " + this + ", time to finish: " + calcEstimatedTime(vehicle) + ".");
	}

	@Override
	public void stayOnCurrentPart(Vehicle vehicle) {
		if (GameDriver.isPConsole())
			System.out.println("- " + vehicle.getStatus() + this + ", time to arrive: "
					+ (calcEstimatedTime(vehicle) - vehicle.getTimeOnCurrentPart()));
	}

	@Override
	public void checkOut(Vehicle vehicle) {
		if (GameDriver.isPConsole())
			System.out.println(
					"- has finished " + this + ", time spent on the road: " + vehicle.getTimeOnCurrentPart() + ".");
		addVehicleToWaitingVehicles(vehicle);

	}

	@Override
	public String toString() {
		return new String("Road from " + getStartJunction() + " to " + getEndJunction() + ", length: " + (int) length
				+ ", max speed " + this.maxSpeed);
	}

	@Override
	public boolean equals(Object other) {

		if (other == null)
			return false;
		if (getClass() != other.getClass())
			return false;
		if (!super.equals(other))
			return false;
		Road otherRoad = (Road) other;
		if (this.enable != otherRoad.enable || !this.endJunction.equals(otherRoad.endJunction)
				|| this.length != otherRoad.length || this.maxSpeed != otherRoad.maxSpeed
				|| !this.startJunction.equals(otherRoad.startJunction) || this.vehicleTypes != otherRoad.vehicleTypes // compare
																														// by
																														// reference
		)
			return false;
		return true;
	}

	@Override
	public boolean canLeave(Vehicle vehicle) {
		if (calcEstimatedTime(vehicle) - vehicle.getTimeOnCurrentPart() > 0) {
			vehicle.setStatus(new String("is still moving on "));
			return false;
		}
		return true;
	}

	public double[] getVehicleLocation(Vehicle vic) {
		if (vic.getCurrentRoutePart().equals(this)) {
			double posRatio = vic.getTimeOnCurrentPart() / calcEstimatedTime(vic);
			if (posRatio > 1) {
				posRatio = 1;
			}
			double xDiff = endJunction.getX() - startJunction.getX();
			double yDiff = endJunction.getY() - startJunction.getY();
			return new double[] { startJunction.getX() + xDiff * posRatio, startJunction.getY() + yDiff * posRatio };
		} else {
			return null;
		}
	}

}
