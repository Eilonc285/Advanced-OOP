package components;

import java.util.ArrayList;

import utilities.Point;

/**
 * A class that represents a junction with no traffic lights.
 * 
 * @author Eilon
 *
 */
public class Junction extends Point implements RouteParts {
	private static int objectCounts;
	private ArrayList<Road> enteringRoads;
	private ArrayList<Road> exitingRoads;
	private String junctionName;

	static {
		objectCounts = 0;
	}

	/**
	 * This constructor gives a random value to the point position, and generates a
	 * name from this objects index.
	 */
	public Junction() {
		super();
		this.enteringRoads = new ArrayList();
		this.exitingRoads = new ArrayList();
		this.junctionName = String.format("%s", String.valueOf(Junction.objectCounts));
		Junction.objectCounts++;
	}

	/**
	 * This constructor sets the objects values from the given parameters.
	 * 
	 * @param junctionName: The objects name.
	 * @param x:            The position of the junction on the x axis.
	 * @param y:            The position of the junction on the y axis.
	 */
	public Junction(String junctionName, double x, double y) {
		super(x, y);
		this.enteringRoads = new ArrayList();
		this.exitingRoads = new ArrayList();
		this.junctionName = junctionName;
		Junction.objectCounts++;
	}

	/**
	 * Add a Road object to the entering roads array.
	 * 
	 * @param road: The road to be added.
	 */
	public void addEnteringRoad(Road road) {
		this.enteringRoads.add(road);
	}

	/**
	 * Add a Road object to the exiting roads array.
	 * 
	 * @param road: The road to be added.
	 */
	public void addExitingRoad(Road road) {
		this.exitingRoads.add(road);
	}

	/**
	 * This method receives a vehicle object as an argument and returns the maximum
	 * time that vehicle would need to wait at this junction before continuing
	 * driving. The calculation is the amount of entering roads with a lower index
	 * in the entering roads array, plus one.
	 * 
	 * @param obj: The vehicle to estimate wait time for.
	 */
	public double calcEstimatedTime(Object obj) {
		return objectCounts;
	}

	/**
	 * This method returns a boolean of if the given vehicle can leave this traffic
	 * section (junction).
	 * 
	 * @param vehicle: The vehicle for which to check if it can leave.
	 */
	public boolean canLeave(Vehicle vehicle) {
		return false;
	}

	public String getJunctionName() {
		return junctionName;
	}

	public void setJunctionName(String junctionName) {
		this.junctionName = junctionName;
	}

	/**
	 * This method is a helper method for the canLeave method. This method checks if
	 * there are any exiting roads and if the given vehicle is in the highest
	 * priority entering road that has vehicles waiting.
	 * 
	 * @param vehicle: The vehicle to check availability for.
	 * @return: Boolean of if the vehicle is available or not.
	 */
	public boolean checkAvailability(Vehicle vehicle) {
		return false;
	}

	/**
	 * This method registers the vehicle to the junction by updating the relevant
	 * fields and printing a message.
	 * 
	 * @param vehicle: The vehicle to check in.
	 */
	// TODO: rewrite javadoc after implementation.
	public void checkIn(Vehicle vehicle) {
	}

	/**
	 * This method is called when the vehicle can leave the junction, and it updates
	 * the relevant fields and prints a message.
	 */
	public void checkOut(Vehicle vehicle) {
	}

	/**
	 * This method finds the next route segment that fits the given vehicle. It
	 * checks if there are enabled exiting routes for the given vehicle type and
	 * returns one of them randomly, or null if no roads are available.
	 * 
	 * @param vehicle: The given vehicle for which to find the next road segment.
	 */
	public RouteParts findNextPart(Vehicle vehicle) {
		return null;
	}

	public void stayOnCurrentPart(Vehicle vehicle) {
	}

	public static int getObjectCounts() {
		return objectCounts;
	}

	public static void setObjectCounts(int objectCounts) {
		Junction.objectCounts = objectCounts;
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

}
